package com.azure.azure_merge.common;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
public class GlobalExceptionHandler {
    /**
     * HSY异常处理
     * @param ex
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public CommonResult<String> exceptionHandler(SQLIntegrityConstraintViolationException ex)
    {
        if (ex.getMessage().contains("Duplicate entry"))
        {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2]+"已存在";
            return CommonResult.failed(msg);
        }
        return CommonResult.failed("未知错误");
    }
    // 如果供应商已经存在，则返回失败


}
