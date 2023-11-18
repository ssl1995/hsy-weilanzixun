package com.azure.azure_merge.controller;

import com.alibaba.excel.EasyExcel;
import com.azure.azure_merge.common.*;
import com.azure.azure_merge.entity.ProjectCenter;
import com.azure.azure_merge.mapper.ProjectCenterMapper;
import com.azure.azure_merge.service.ProjectCenterService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.azure.azure_merge.dto.ClassificationDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import com.azure.azure_merge.dto.ProjectDTO;

@Api(tags = "项目中心")
@Slf4j
@RestController
@RequestMapping("/project")
@Transactional
@CrossOrigin(origins = {"http://localhost"}, allowCredentials = "true")
public class ProjectController {
    @Autowired
    ProjectCenterService projectCenterService;
    @Autowired
    ProjectCenterMapper projectCenterMapper;



    @GetMapping("/projectcenter")
    @ApiOperation("分页展示所有数据")
    public CommonResult<Page> projectSelectAll(int page, int pageSize) {
        Page<ProjectCenter> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectPublishTime);
        projectCenterService.page(pageinfo, projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo, "查询成功");
    }
    //项目名称关键词搜索
    @GetMapping("/keywords")
    @ApiOperation("关键词搜索")
    public CommonResult<Page> projectKeywordsSerch(int page, int pageSize, @RequestParam(value = "country", required = false) String country, @RequestParam(value = "country", required = false) String keywords) {
        Page<ProjectCenter> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        projectLambdaQueryWrapper.like(keywords != null, ProjectCenter::getProjectName, keywords)
                .eq(country != null, ProjectCenter::getProjectLocation, country);
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectPublishTime);
        projectCenterService.page(pageinfo, projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo, "查询成功");
    }

    //类别搜索
    @PostMapping("/filter")
    public CommonResult<Page<ProjectCenter>> projectClassificationFilter(@RequestBody ClassificationDTO classificationDTO) {

        Page<ProjectCenter> pageinfo = new Page<>(classificationDTO.getPage(), classificationDTO.getPageSize());
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //全查
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectPublishTime);
        //区域搜索

//        for (String region : classificationDTO.getProjectRegion()) {
//            projectLambdaQueryWrapper.eq(region != null, ProjectCenter::getProjectRegion, region);
//        }
//        //类别搜索
//        for (String plate : classificationDTO.getProjectPlate()) {
//            projectLambdaQueryWrapper.eq(plate != null, ProjectCenter::getProjectPlate, plate);
//        }


        //金额搜索
        if (classificationDTO.getPriceType() != null) {
            projectLambdaQueryWrapper.le(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.LESS_HUNDRED.getCode(), ProjectCenter::getProjectPrice, 1000000)
                    .between(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.ONEHUNDRED_ONETHOUSAND.getCode(), ProjectCenter::getProjectPrice, 1000000, 10000000)
                    .between(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.ONETHOUSAND_FIVETHOUSAND.getCode(), ProjectCenter::getProjectPrice, 10000000, 50000000)
                    .between(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.FIVETHOUSAND_ONEHUNDREDMILLION.getCode(), ProjectCenter::getProjectPrice, 50000000, 100000000)
                    .between(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.FIVETHOUSAND_ONEHUNDREDMILLION.getCode(), ProjectCenter::getProjectPrice, 50000000, 100000000)
                    .between(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.ONEHUNDREDMILLION_ONEBILLION.getCode(), ProjectCenter::getProjectPrice, 100000000, 1000000000)
                    .ge(Integer.parseInt(classificationDTO.getPriceType()) == PriceTypeEnum.MORE_ONEBILLION.getCode(), ProjectCenter::getProjectPrice, 1000000000);
        }

        //时间搜索
        if (classificationDTO.getTimeType() != null) {
            System.out.println(classificationDTO.getTimeType());
            System.out.println(Integer.parseInt(classificationDTO.getTimeType()));
            projectLambdaQueryWrapper
                    .ge(Integer.parseInt(classificationDTO.getTimeType()) == DataTimeTypeEnum.ONE_WEEK.getCode(), ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateOneWeekAgo());
//            .ge(Integer.parseInt(classificationDTO.getTimeType())==DataTimeTypeEnum.ONE_DAY.getCode(),ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateOneDayAgo())
//                    .ge(Integer.parseInt(classificationDTO.getTimeType())==DataTimeTypeEnum.ONE_MONTH.getCode(),ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateOneMonthAgo());
//                    .ge(Integer.parseInt(classificationDTO.getTimeType())==DataTimeTypeEnum.THREE_MONTH.getCode(),ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateThreeMonthsAgo())
//                    .ge(Integer.parseInt(classificationDTO.getTimeType())==DataTimeTypeEnum.SIX_MONTH.getCode(),ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateSixMonthsAgo())
//                    .le(Integer.parseInt(classificationDTO.getTimeType())==DataTimeTypeEnum.SIX_MONTH2.getCode(),ProjectCenter::getProjectPublishTime, DataCalculate.calculateDateSixMonthsAgo());
            List<ProjectCenter> projectCenters = projectCenterService.list(projectLambdaQueryWrapper);
            for (ProjectCenter projectCenter : projectCenters) {
                System.out.println(projectCenter.getProjectName());
            }
        }
        projectCenterService.page(pageinfo, projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo, "查询成功");
    }

    //项目列表
    @GetMapping("/list")
    public CommonResult<Page> projectList(int page, int pageSize) {
        Page<ProjectCenter> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<ProjectCenter> projectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        projectLambdaQueryWrapper.ne(ProjectCenter::getIsDeleted, "1");
        projectLambdaQueryWrapper.orderByDesc(ProjectCenter::getProjectPublishTime);
        projectCenterService.page(pageinfo, projectLambdaQueryWrapper);
        return CommonResult.success(pageinfo);
    }

    //项目新建
    @PostMapping()
    public CommonResult<String> projectSave(@RequestBody ProjectCenter project) {
        log.info("project:{}", project);
        projectCenterService.save(project);
        return CommonResult.success("新增项目成功");
    }

    //项目发布
    @PostMapping("/publish")
    public CommonResult<String> projectPublish(@RequestBody ProjectCenter project) {
        log.info("project:{}", project);
        LambdaUpdateWrapper<ProjectCenter> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ProjectCenter::getProjectId, project.getProjectId())
                .set(project.getIsDeleted() != 0, ProjectCenter::getIsDeleted, 0);
        projectCenterService.update(updateWrapper);
        return CommonResult.success("发布成功");
    }

    //项目下架
    @PostMapping("/expired")
    public CommonResult<String> projectExpired(@RequestBody ProjectCenter project) {
        log.info("project:{}", project);
        LambdaUpdateWrapper<ProjectCenter> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(ProjectCenter::getProjectId, project.getProjectId())
                .set(project.getIsDeleted() != 1, ProjectCenter::getIsDeleted, 1);
        projectCenterService.update(updateWrapper);
        return CommonResult.success("下架成功");
    }

    //项目详情
//    @GetMapping("/details")
//    public CommonResult<ProjectDTO> projectDetails(Integer id) {
//        LambdaQueryWrapper<ProjectCenter> projectCenterLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        projectCenterLambdaQueryWrapper.eq(ProjectCenter::getProjectId, id);
//        ProjectCenter projectCenter = projectCenterService.getOne(projectCenterLambdaQueryWrapper);
//        ProjectDTO projectDTO = new ProjectDTO(projectCenter.getProjectName(), projectCenter.getProjectLocation(),
//                projectCenter.getBiddingOwner(), projectCenter.getAddressNumber(), projectCenter.getProjectIntroduction(),
//                projectCenter.getProjectPublishTime(), projectCenter.getProjectDeadline(), projectCenter.getFilePath(),
//                projectCenter.getProjectRegion(), projectCenter.getProjectPrice(), projectCenter.getProjectPlate(),
//                projectCenter.getCity(), projectCenter.getState(), projectCenter.getEmailId(), projectCenter.getBiddingType(),
//                projectCenter.getTendersDetails(), projectCenter.getCurrency());
////        projectDTO.setAddressNumber(projectCenter.getAddressNumber());
//        return CommonResult.success(projectDTO);
  //  }

    //项目删除
    @DeleteMapping("/delete")
    public CommonResult<String> projectDelete(Long id) {
        log.info("删除项目，id为{}", id);
        projectCenterService.removeById(id);
        return CommonResult.success("删除项目成功");
    }

    /**
     * 导入的excel文件对象是MultipartFile类型的
     *
     * @param file
     * @throws IOException
     */
    @PostMapping(value = "/import",consumes ="multipart/form-data")
    public void projectExcelImport(@RequestPart("file") MultipartFile file) throws IOException {
        InputStream is = file.getInputStream();
        ProjectReadListener projectReadListener = new ProjectReadListener(projectCenterMapper);
        EasyExcel.read(is, ProjectCenter.class, projectReadListener)
                .sheet(0)
                .headRowNumber(1)
                .doRead();

    }

    //pdf下载
    @GetMapping("/downloadFile")
    public void projectDownloadPDF(HttpServletResponse response) {
        String downloadFilePath = "D:/test/";    //被下载的文件在服务器中的路径,
        String fileName = "aaa.pdf";            //被下载文件的名称

        File file = new File(downloadFilePath + fileName);
        if (file.exists()) {
            response.setContentType("application/force-download");// 设置强制下载不打开
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream outputStream = response.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    outputStream.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    //pdf预览
    @GetMapping("/previewCheck")
    public void projectPreviewCheck(HttpServletRequest request, HttpServletResponse response) throws
            IOException {
        FileInputStream is = new FileInputStream(new File("D:\\test\\aaa.pdf"));
        // 清空response
        response.reset();
        //2、设置文件下载方式
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/pdf");
        OutputStream outputStream = response.getOutputStream();
        int count = 0;
        byte[] buffer = new byte[1024 * 1024];
        while ((count = is.read(buffer)) != -1) {
            outputStream.write(buffer, 0, count);
        }
        outputStream.flush();

    }
//pdf上传
    @PostMapping(value = "/upload",consumes ="multipart/form-data")
    public CommonResult<String> projectUploadPDF(@RequestPart("file")MultipartFile file) throws IOException {
        log.info(file.toString());
        String fileName = file.getOriginalFilename();
        File dir = new File("D:\\test");//bathPath可指定一个固定路径
        if(!dir.exists())
        {
            dir.mkdir();
        }
        file.transferTo(new File("D:\\test" + fileName));
        return CommonResult.success(fileName);
    }
}
