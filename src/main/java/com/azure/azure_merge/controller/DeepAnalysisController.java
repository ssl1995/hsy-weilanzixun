package com.azure.azure_merge.controller;

import com.azure.azure_merge.dto.DeepAnalysisDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.DeepAnalysis;
import com.azure.azure_merge.service.DeepAnalysisService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
@RequestMapping("/deepAnalysis")
@Api(tags = "深度分析")
public class DeepAnalysisController {
    @Autowired
    private DeepAnalysisService deepAnalysisService;
    @Value(value = "${file.upload_path}")
    private String upload_path;

    /**
     * 深度报告
     *
     * @param page
     * @param pageSize
     * @param deepAnalysisCategory
     * @return
     */
    @GetMapping("/page")
    public CommonResult<Page<DeepAnalysisDTO>> page(int page, int pageSize, String deepAnalysisCategory) {
        log.info("deepAnalysisCategory:{}", deepAnalysisCategory);

        Page<DeepAnalysisDTO> pageData = new Page<>(page, pageSize);

        // 构造查询条件
        LambdaQueryWrapper<DeepAnalysis> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(DeepAnalysis::getDeepAnalysisCategory, DeepAnalysis::getDeepAnalysisTitle, DeepAnalysis::getDeepAnalysisId, DeepAnalysis::getDeepAnalysisPrice, DeepAnalysis::getDeepAnalysisEditTime);
        queryWrapper.eq(DeepAnalysis::getDeepAnalysisCategory, deepAnalysisCategory);
        queryWrapper.eq(DeepAnalysis::getIsDeleted, 0);
        List<DeepAnalysis> results = deepAnalysisService.list(queryWrapper);
        List<DeepAnalysisDTO> results1 = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            int newsId = results.get(i).getDeepAnalysisId();
            String newsTitle = results.get(i).getDeepAnalysisTitle();
            LocalDateTime newspublishedTime = results.get(i).getDeepAnalysisEditTime();
            DeepAnalysisDTO deepAnalysisDTO = new DeepAnalysisDTO();
            deepAnalysisDTO.newsId = newsId;
            deepAnalysisDTO.newsTitle = newsTitle;
            deepAnalysisDTO.newsPublishedTime = newspublishedTime;
            results1.add(deepAnalysisDTO);
        }
        pageData.setRecords(results1);
        return CommonResult.success(pageData);

    }

    @ApiOperation(value = "下载PDF文件")
    @GetMapping(value = "/downloadFile")
    public void downloadFile(int newsId, HttpServletResponse response) {
        LambdaQueryWrapper<DeepAnalysis> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeepAnalysis::getDeepAnalysisId, newsId);
        List<DeepAnalysis> results = deepAnalysisService.list(queryWrapper);

        String downloadFilePath = results.get(0).getAnalysisPath();
        String fileName = results.get(0).getDeepAnalysisTitle(); // 文件名

        try {
            File file = new File(downloadFilePath);
            BufferedInputStream bis = null;
            OutputStream os = null;
            FileInputStream fileInputStream = null;
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"");
            try {
                fileInputStream = new FileInputStream(file);
                byte[] buff = new byte[1024];
                bis = new BufferedInputStream(fileInputStream);
                os = response.getOutputStream();

                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    i = bis.read(buff);
                    os.flush();
                }
                os.flush();
                os.close();
//                return SimpleResult.ok("导出成功",os);
            } catch (IOException e) {
                e.printStackTrace();
//                return SimpleResult.fail("导出失败",null);
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                        fileInputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
//                        return SimpleResult.fail("导出失败",null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @GetMapping("/previewCheck")
    @ApiOperation(value = "预览PDF")
    public void previewCheck(int newsId, HttpServletResponse response) throws IOException {
        LambdaQueryWrapper<DeepAnalysis> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DeepAnalysis::getDeepAnalysisId, newsId);
        List<DeepAnalysis> results = deepAnalysisService.list(queryWrapper);

        String downloadFilePath = results.get(0).getAnalysisPath();
        String fileName = results.get(0).getDeepAnalysisTitle(); // 文件名
        FileInputStream is = new FileInputStream(new File(downloadFilePath));
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

    @PostMapping("/add")

    public CommonResult<String> add(MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = upload_path + fileName;
        String deepAnalysisCategory = request.getParameter("deepAnalysisCategory");
        int adminId = (int) request.getSession().getAttribute("adminId");
        File dest = new File(filePath);
        Files.copy(file.getInputStream(), dest.toPath());

        DeepAnalysis deepAnalysis = new DeepAnalysis();
        deepAnalysis.setDeepAnalysisCategory(deepAnalysisCategory);
        deepAnalysis.setDeepAnalysisTitle(fileName);
        deepAnalysis.setDeepAnalysisEditTime(LocalDateTime.now());
        deepAnalysis.setAdminId(adminId);
        if (Objects.equals(deepAnalysisCategory, "观点评论") || Objects.equals(deepAnalysisCategory, "专题分析") || Objects.equals(deepAnalysisCategory, "免费报告")) {
            deepAnalysis.setIsCharging(0);
        } else {
            deepAnalysis.setIsCharging(1);
        }
        deepAnalysisService.save(deepAnalysis);
        return CommonResult.success("上传成功");
    }
}

