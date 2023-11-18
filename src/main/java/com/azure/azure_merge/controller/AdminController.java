package com.azure.azure_merge.controller;


import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.News;
import com.azure.azure_merge.service.AdminService;
import com.azure.azure_merge.service.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yuwolianxi
 */
@RestController
@Api(tags = "资讯管理员接口")
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private NewsService newsService;

    @ApiOperation(value = "资讯列表条件查询", notes = "高级多维检索")
    @GetMapping("/page/search")
    public CommonResult<Page> page(int page, int pageSize, String newsTitle, int isPublished, int newsType, String newsLink, String websiteTitle, String newsPublishedTime) {
        log.info("page = {},pageSize = {},newsTitle = {},isPublished = {},newsType ={}", page, pageSize, newsTitle, isPublished, newsType);

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        if (newsTitle != null) {
            queryWrapper.like(StringUtils.isNotEmpty(newsTitle), News::getNewsTitle, newsTitle);
        }
        queryWrapper.eq(News::getIsPublished, isPublished);
        queryWrapper.eq(News::getNewsTypeId, newsType);
        if (StringUtils.isNotEmpty(newsLink)) {
            queryWrapper.like(StringUtils.isNotEmpty(newsLink), News::getNewsLink, newsLink);
        }
        if (StringUtils.isNotEmpty(websiteTitle)) {
            queryWrapper.like(StringUtils.isNotEmpty(websiteTitle), News::getWebsiteTitle, websiteTitle);
        }
        if (StringUtils.isNotEmpty(newsPublishedTime)) {
            queryWrapper.like(StringUtils.isNotEmpty(newsPublishedTime), News::getNewsPublishedTime, newsPublishedTime);
        }
        //添加排序条件
        queryWrapper.orderByDesc(News::getNewsUpdatedTime);
        //执行查询
        newsService.page(pageInfo, queryWrapper);

        return CommonResult.success(pageInfo);
    }

    @ApiOperation(value = "资讯列表分页查询", notes = "展示所有资讯")
    @GetMapping("/page")
    public CommonResult<Page> page(int page, int pageSize, String newsTitle) {
        log.info("page = {},pageSize = {},newsTitle = {}", page, pageSize, newsTitle);
        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);

        //构造条件构造器
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        //添加过滤条件
        if (StringUtils.isNotEmpty(newsTitle)) {
            queryWrapper.like(StringUtils.isNotEmpty(newsTitle), News::getNewsTitle, newsTitle);
        }
        //添加排序条件
        queryWrapper.orderByDesc(News::getNewsUpdatedTime);
        //执行查询
        newsService.page(pageInfo, queryWrapper);

        return CommonResult.success(pageInfo);
    }

    @ApiOperation(value = "新建资讯")
    @PostMapping
    public CommonResult<String> add(News news) {
        log.info("news = {}", news);
        Date date = new Date();
        // 将日期格式化为 yyyy-MM-dd HH:mm
        news.setNewsUpdatedTime(String.valueOf(date));
        news.setNewsGetTime(String.valueOf(date));

        newsService.save(news);
        return CommonResult.success("新建成功");
    }

    @ApiOperation(value = "修改资讯")
    @PostMapping("/update")
    public CommonResult<String> update(News news) {
        log.info("news = {}", news);
        Date date = new Date();
        // 将日期格式化为 yyyy-MM-dd HH:mm
        news.setNewsUpdatedTime(String.valueOf(date));
        newsService.updateById(news);
        return CommonResult.success("修改成功");
    }

    @ApiOperation(value = "删除资讯")
    @PostMapping("/delete")
    public CommonResult<String> delete(News news) {
        log.info("news = {}", news);
        Date date = new Date();
        // 将日期格式化为 yyyy-MM-dd HH:mm
        news.setNewsUpdatedTime(String.valueOf(date));
        news.setIsDeleted(1);
        newsService.updateById(news);
        return CommonResult.success("删除成功");
    }

    @ApiOperation(value = "资讯详情查询")
    @GetMapping("/detail")
    public CommonResult<News> detail(int id) {
        log.info("id = {}", id);
        News news = newsService.getById(id);
        return CommonResult.success(news);
    }

    @ApiOperation(value = "发布资讯")
    @PostMapping("/publish")
    public CommonResult<String> publish(News news) {
        log.info("news = {}", news);
        Date date = new Date();
        // 将日期格式化为 yyyy-MM-dd HH:mm
        news.setNewsUpdatedTime(String.valueOf(date));
        news.setIsPublished(1);
        newsService.updateById(news);
        return CommonResult.success("发布成功");
    }
}
