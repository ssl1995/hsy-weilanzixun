package com.azure.azure_merge.controller;


import com.azure.azure_merge.common.BaseContext;
import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.dto.PageQueryDto;
import com.azure.azure_merge.dto.RecordDTO;
import com.azure.azure_merge.dto.VipSearchDTO;
import com.azure.azure_merge.entity.*;
import com.azure.azure_merge.entity.Record;
import com.azure.azure_merge.service.*;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/news")
@Slf4j
@Api(tags = "资讯中心")
@CrossOrigin(origins = {"http://localhost"}, allowCredentials = "true")
public class NewsController {
    @Autowired
    private NewsService newsService;
    @Autowired
    private RecordService recordService;

    @Autowired
    private ViplogsService viplogsService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private IndustryService industryService;
    @Autowired
    private TypeService typeService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 资讯列表--普通用户展示  / VIP搜索框搜索
     *
     * @param page
     * @param pageSize
     * @param newsTitle
     * @return
     */
    @GetMapping("/page")
    public CommonResult<Page> page(int page,int pageSize, String newsTitle) {

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        //显示的字段
        queryWrapper.select(News::getNewsId, News::getNewsTitle, News::getNewsContent, News::getNewsThumbnail, News::getNewsPublishedTime,News::getNewsLink);
        //添加过滤条件
        queryWrapper.like(StringUtils.isNotEmpty(newsTitle), News::getNewsTitle, newsTitle);
        //添加排序条件
        queryWrapper.orderByAsc(News::getNewsPublishedTime);
        // is_deleted = 0 和 is_published = 0 的项排除
        queryWrapper.eq(News::getIsDeleted, 0);
        queryWrapper.eq(News::getIsPublished, 1);

        //执行查询
        newsService.page(pageInfo, queryWrapper);

        return CommonResult.success(pageInfo);
    }

    /**
     * 收藏夹内容  我的关注列表
     * 根据收藏的title去模糊查询进行返回。
     *
     * @param page
     * @param pageSize
     * @param newsTitle
     * @return
     */
    @ApiOperation(value = "在redis中查询关注资讯")
    @GetMapping("/collection")
    public CommonResult<Page<RecordDTO>> collection(int page,int pageSize, String newsTitle) {
        log.info("page = {},pageSize = {},name = {}", page, pageSize, newsTitle);

        Page<Record> records = new Page<>(page, pageSize);
        // 创建DTO分页对象
        Page<RecordDTO> recordDTOPage = new Page<>(page, pageSize);
        Long userId = BaseContext.getCurrentId();
        LambdaQueryWrapper<Record> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Record::getUserId, 1001);
        LambdaQueryWrapper<News> queryWrapper1 = new LambdaQueryWrapper<>();
        recordService.page(records, queryWrapper);

        // 对象拷贝
        BeanUtils.copyProperties(records, recordDTOPage);
        List<Record> records1 = records.getRecords();
        List<RecordDTO> list = new ArrayList<>();
        for (Record item : records1) {
            RecordDTO recordDTO = new RecordDTO();
            BeanUtils.copyProperties(item, recordDTO);
            int recordId = item.getRecordId();
            int resourceType = recordDTO.getResourceType();
            if (resourceType == 1) {
                News news = newsService.getById(recordId);
                if (news.getIsDeleted() == 0 && news.getIsPublished() == 1) {
                    List<String> newsIds = redisTemplate.opsForList().range("news_" + String.valueOf(news.getNewsId()), 0, -1);
                    for (int i = 0; i < newsIds.size(); i++) {
                        News newsNow = newsService.getById(Integer.parseInt(newsIds.get(i)));
                        String newsTitleNow = newsNow.getNewsTitle();
                        String newsContentNow = newsNow.getNewsContent();
                        String newsLinkNow = newsNow.getNewsLink();
                        String newsThumbnailNow = newsNow.getNewsThumbnail();
                        LocalDateTime newsPublishedTimeNow = newsNow.getNewsPublishedTime();
                        RecordDTO recordDTONow = new RecordDTO();
                        BeanUtils.copyProperties(recordDTO, recordDTONow);
                        recordDTONow.setNewsTitle(newsTitleNow);
                        recordDTONow.setNewsContent(newsContentNow);
                        recordDTONow.setNewsLink(newsLinkNow);
                        recordDTONow.setNewsPublishedTime(newsPublishedTimeNow);
                        recordDTONow.setNewsThumbnail(newsThumbnailNow);
                        list.add(recordDTONow);
                    }
                }
            }
        }
        recordDTOPage.setRecords(list);
        return CommonResult.success(recordDTOPage);
    }

    @GetMapping("/popularRecommendations")
    @ApiOperation(value = "热门推荐")
    public CommonResult<Page<News>> getrecord(int page,int pageSize) {
        log.info("page = {},pageSize = {}", page, pageSize);
        // 构造分页构造器
        Page<News> newsPage = new Page<>(page, pageSize);

        LambdaQueryWrapper<Viplogs> viplogsLambdaQueryWrapper = new LambdaQueryWrapper<>();
        viplogsLambdaQueryWrapper.eq(Viplogs::getResourceType, 1);
        // 根据resource_id去重
        // 遍历viplogsLambdaQueryWrapper
        List<Viplogs> viplogsList = viplogsService.list(viplogsLambdaQueryWrapper);
        List<Integer> resourceIds = new ArrayList<>();
        for (Viplogs viplogs : viplogsList) {
            resourceIds.add(viplogs.getResourceId());
        }

        // 条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(News::getNewsTitle, News::getNewsContent, News::getNewsPublishedTime, News::getNewsLink,News::getNewsThumbnail);
        queryWrapper.in(News::getNewsId, resourceIds);

        // 高级检索
        queryWrapper.orderByDesc(News::getNewsPublishedTime);
        newsService.page(newsPage, queryWrapper);
        return CommonResult.success(newsPage);
    }

    /**
     * VIP资讯高级检索
     *
     * @param keywords
     * @return
     */
    @PostMapping("/advancedSearch")
    @ApiOperation(value = "VIP资讯高级检索")
    public CommonResult<Page<News>> advancedSearch(@RequestBody VipSearchDTO keywords) {
        int page = keywords.getPage();
        int pageSize = keywords.getPageSize();

        log.info("page = {},pageSize = {},keywords = {}", page, pageSize, keywords.toString());
        // 构造分页构造器
        Page<News> newsPage = new Page<>(page, pageSize);
        // 条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(News::getNewsId,News::getWebsiteTitle,News::getNewsLink,News::getNewsThumbnail,News::getNewsContent,News::getNewsPublishedTime);
        // 添加过滤条件
        queryWrapper.eq(News::getIsDeleted, 0);
        queryWrapper.eq(News::getIsPublished, 1);


        // 高级检索
        for (int i = 0; i < keywords.getRegionName().size(); i++) {
            LambdaQueryWrapper<Region> queryWrapper1 = new LambdaQueryWrapper<>();
            if (keywords.getRegionName().get(i) != null) {
                queryWrapper.or();
                queryWrapper1.eq(Region::getRegionName, keywords.getRegionName().get(i));
                int regionId = regionService.getOne(queryWrapper1).getRegionId();
                queryWrapper.eq(News::getNewsRegionId, regionId);
            }
        }
        for (int i = 0; i < keywords.getIndustryName().size(); i++) {
            LambdaQueryWrapper<Industry> queryWrapper1 = new LambdaQueryWrapper<>();
            if (keywords.getIndustryName().get(i) != null) {
                queryWrapper.or();
                queryWrapper1.eq(Industry::getIndustryName, keywords.getIndustryName().get(i));
                int industryId = industryService.getOne(queryWrapper1).getIndustryId();
                queryWrapper.eq(News::getNewsIndustryId, industryId);
            }
        }
        for (int i = 0; i < keywords.getTypeName().size(); i++) {
            LambdaQueryWrapper<Type> queryWrapper1 = new LambdaQueryWrapper<>();
            if (keywords.getTypeName().get(i) != null) {
                queryWrapper.or();
                queryWrapper1.eq(Type::getTypeName, keywords.getTypeName().get(i));
                int typeId = typeService.getOne(queryWrapper1).getTypeId();
                queryWrapper.eq(News::getNewsTypeId, typeId);
            }
        }
        queryWrapper.orderByDesc(News::getNewsPublishedTime);
        // 执行查询
        newsService.page(newsPage, queryWrapper);

        return CommonResult.success(newsPage);
    }
}
