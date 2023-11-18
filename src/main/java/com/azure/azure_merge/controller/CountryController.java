package com.azure.azure_merge.controller;

import com.azure.azure_merge.common.CommonResult;

import com.azure.azure_merge.entity.CountryReport;
import com.azure.azure_merge.entity.ProjectCenter;
import com.azure.azure_merge.service.CountryReportService;
import com.azure.azure_merge.service.ProjectCenterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.UUID;

@Api(tags = "国家中心")
@Slf4j
@RestController
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private ProjectCenterService projectService;
    @Autowired
    private CountryReportService countryService;
//    @Autowired
//    private DocumentConverter converter;  //用于转换



    //国家名称关键词搜索
    @GetMapping("/keywords")
    @ApiOperation("关键词搜索")
    public CommonResult<Page> countryKeywordsSerch(int page, int pageSize, @RequestParam(value = "country",required = false) String country, @RequestParam(value = "keywords",required = false) String keywords){
        Page<CountryReport>pageinfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<CountryReport> countryReportLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryReportLambdaQueryWrapper.like(keywords!=null, CountryReport::getReportTitle,keywords)
                .eq(country!=null,CountryReport::getReportCountry,country);
        countryReportLambdaQueryWrapper.orderByDesc(CountryReport::getImportTime);
        countryService.page(pageinfo,countryReportLambdaQueryWrapper);
        return CommonResult.success(pageinfo,"查询成功");
    }

    @GetMapping("/list")
    public CommonResult<Page> countryList(int page,int pageSize){
        Page<CountryReport>pageinfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<CountryReport> countryReportLambdaQueryWrapper = new LambdaQueryWrapper<>();
        countryReportLambdaQueryWrapper.ne(CountryReport::getIsDeleted,"1");
        countryReportLambdaQueryWrapper.orderByDesc(CountryReport::getImportTime);
        countryService.page(pageinfo,countryReportLambdaQueryWrapper);
         return CommonResult.success(pageinfo,"查询成功");
    }
    //项目发布
    @PostMapping("/publish")
    public CommonResult<String> projectPublish(@RequestBody CountryReport countryReport){
        log.info("project:{}",countryReport);
        LambdaUpdateWrapper<CountryReport> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CountryReport::getReportCountry,countryReport.getReportId())
                .set(countryReport.getIsDeleted()!=1,CountryReport::getIsDeleted,0);
        countryService.update(updateWrapper);
        return CommonResult.success("发布成功");
    }
    //报告下架
    @PostMapping("/expired")
    public CommonResult<String> projectExpired(@RequestBody CountryReport countryReport){
        log.info("country:{}",countryReport);
        LambdaUpdateWrapper<CountryReport> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(CountryReport::getReportCountry,countryReport.getReportId())
                .set(countryReport.getIsDeleted()!=1,CountryReport::getIsDeleted,1);
        countryService.update(updateWrapper);
        return CommonResult.success("下架成功");
    }
    //报告删除
    @DeleteMapping("/delete")
    public CommonResult<String> projectDelete(Long id)
    {
        log.info("删除项目，id为{}",id);
        countryService.removeById(id);
        return CommonResult.success("删除项目成功");
    }
    @GetMapping("/previewCheck")
    public void projectPreviewCheck(String path, HttpServletResponse response ) {
        try  {
            String filePath = "D:/test/组会报告.doc";
            File inputWord = new File(filePath);

            response.setContentType("application/pdf;charset=UTF-8");
            response.setHeader("X-Frame-Options", "SAMEORIGIN");
            IConverter converter = LocalConverter.builder().build();
            UUID uuid = UUID.randomUUID();
            File outputFile = new File("D:/test/"+uuid+".pdf");
            if (StringUtils.endsWith(filePath, ".doc"))
            {
                converter.convert(inputWord).as(DocumentType.DOC).to(outputFile).as(DocumentType.PDF).execute();
            }
            if (StringUtils.endsWith(filePath, ".docx")){
                converter.convert(inputWord).as(DocumentType.DOCX).to(outputFile).as(DocumentType.PDF).execute();
            }
            FileInputStream is = new FileInputStream(outputFile);
            // 清空response
            response.reset();
            // 2、设置文件下载方式
            OutputStream outputStream = response.getOutputStream();
            int count = 0;
            byte[] buffer = new byte[1024 * 1024];
            while ((count = is.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/historyproject")
    public CommonResult<Page> historyProjectSerch(int page, int pageSize){
        Page<ProjectCenter>pageinfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        projectLambdaQueryWrapper.le(ProjectCenter::getProjectDeadline,localDateTime);
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectDeadline);
        projectService.page(pageinfo,projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo);
    }
    @GetMapping("/activeproject")
    public CommonResult<Page> activeProjectSerch(int page, int pageSize){
        Page<ProjectCenter>pageinfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        projectLambdaQueryWrapper.ge(ProjectCenter::getProjectDeadline,localDateTime);
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectDeadline);
        projectService.page(pageinfo,projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo);
    }
}
