package com.azure.azure_merge.controller;


import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.Supplier;
import com.azure.azure_merge.service.SupplierService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/supplier")
@Slf4j
@Api(tags = "供应商管理相关接口")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    //分页查询
    @GetMapping("/page")
    public CommonResult<Page<Supplier>> select(int page, int pageSize, String name) {

        Page<Supplier> pageinfo = new Page<>(page, pageSize);
        LambdaQueryWrapper<Supplier> supplierLambdaQueryWrapper = new LambdaQueryWrapper<>();
        supplierLambdaQueryWrapper.like(Supplier::getSupplierName, name);
        supplierService.page(pageinfo, supplierLambdaQueryWrapper);
        return CommonResult.success(pageinfo);
    }


    @PutMapping("/update")
    @ApiOperation("供应商下架/上架")
    public CommonResult<String> upAndDown(@RequestParam Integer supplierId, @RequestParam Integer isDeleted) {

        supplierService.upAndDown(supplierId, isDeleted);
        return CommonResult.success("操作成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除供应商信息")
    public CommonResult<String> delete(Integer supplierId) {

        supplierService.deleteSupplier(supplierId);
        return CommonResult.success("删除成功");
    }

    @PostMapping("/insertBatch")
    @ApiOperation("批量导入供应商信息")
    public CommonResult<String> importByExcel(String path) throws IOException {

        supplierService.importByExcel(path);
        return CommonResult.success("批量导入成功");
    }

    @GetMapping("/query/supplier")
    @ApiOperation("查询供应商具体页面")
    public CommonResult<String> supplierDetail(String supplierName) {

        String path = supplierService.supplierDetail(supplierName);
        return CommonResult.success(path);
    }

    @PostMapping("/insert")
    @ApiOperation("页面单个新增供应商")
    public CommonResult<String> inserSupplier(Supplier supplier) {

        supplierService.inserSupplier(supplier);
        return CommonResult.success("新增成功");
    }

}
