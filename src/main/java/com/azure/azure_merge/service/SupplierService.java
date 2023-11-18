package com.azure.azure_merge.service;

import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.Supplier;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
 * @author yuwolianxi
 * @description 针对表【supplier(供应商)】的数据库操作Service
 * @createDate 2023-11-02 10:40:59
 */
public interface SupplierService extends IService<Supplier> {
    /**
     * 上架或下架
     *
     * @param supplierId
     * @param isDeleted
     */
    void upAndDown(Integer supplierId, Integer isDeleted);

    /**
     * 删除供应商信息
     *
     * @param supplierId
     */
    void deleteSupplier(Integer supplierId);

//    /**
//     *供应商查询
//     * @param providerPageQueryDTO
//     * @return
//     */
//    PageResult page(ProviderPageQueryDTO providerPageQueryDTO);
//
//    /**
//     * 新增供应商
//     * @param supplier
//     */
//    void add(Supplier supplier);
//
//    /**
//     * 更新（下架）
//     * @param supplierId
//     */
//    void update(Long supplierId, Integer isDeleted);
//
//    /**
//     * 删除供应商信息
//     * @param id
//     */
//    void delete(Long id);

    /**
     * 批量导入excel中的供应商
     *
     * @param path
     */
    void importByExcel(String path) throws IOException;

    /**
     * 查询供应商具体页面
     *
     * @param supplierName
     * @return
     */
    String supplierDetail(String supplierName);

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    CommonResult<Page<Supplier>> pageQuery(int page, int pageSize, String name);

    /**
     * 从页面单个新增供应商
     *
     * @param supplier
     */
    void inserSupplier(Supplier supplier);
}
