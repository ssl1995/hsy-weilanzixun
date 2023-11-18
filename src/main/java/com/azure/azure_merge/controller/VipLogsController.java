package com.azure.azure_merge.controller;

import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.Viplogs;
import com.azure.azure_merge.service.ViplogsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/viplogs")
@Api(tags = "用户点击记录")
@Slf4j
public class VipLogsController {
    @Autowired
    private ViplogsService viplogsService;

    @PostMapping("/checkRecord")
    @ApiOperation(value = "用户点击记录")
    public CommonResult<String> checkrecord(@RequestBody Viplogs viplogs) {
        log.info("viplogs = {}", viplogs.toString());
        viplogs.setLogTime(LocalDateTime.now());
        viplogsService.save(viplogs);
        return CommonResult.success("存储成功");
    }
}
