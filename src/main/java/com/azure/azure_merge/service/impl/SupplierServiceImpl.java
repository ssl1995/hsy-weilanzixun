package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.dto.SupplierDTO;
import com.azure.azure_merge.entity.Supplier;
import com.azure.azure_merge.mapper.SupplierMapper;
import com.azure.azure_merge.service.SupplierService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuwolianxi
 * @description 针对表【supplier(供应商)】的数据库操作Service实现
 * @createDate 2023-11-02 10:40:59
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier>
        implements SupplierService {
    @Override
    public void upAndDown(Integer supplierId, Integer isDeleted) {
        lambdaUpdate()
                .set(Supplier::getIsDeleted, isDeleted)
                .eq(Supplier::getSupplierId, supplierId)
                .update();
    }

    @Override
    public void deleteSupplier(Integer supplierId) {
        this.removeById(supplierId);
    }

//
//    /**
//     * 删除供应商资讯信息
//     *
//     * @param id
//     */
//    @Override
//    public void delete(Long id) {
//        //1.判断当前供应商是否还有没删除的相关咨询
//        //TODO
//
//        //2.删除供应商信息
//        providerMapper.delete(id);
//    }

    /**
     * 批量导入excel中的供应商
     *
     * @param path
     */
    @Override
    public void importByExcel(String path) throws IOException {
        List<SupplierDTO> supplierList = new ArrayList<>();

        //1.获取工作簿
        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);

        //2.获取工作表
        XSSFSheet sheet = xssfWorkbook.getSheetAt(0);//(注：excel表中没有id，import_time,import_userid字段)

        //3.获取行（注：第一行的数据不要）
        int lastRowNum = sheet.getLastRowNum();//获取最后一行的行号
        for (int i = 1; i <= lastRowNum; i++) {
            XSSFRow row = sheet.getRow(i);//取到每一行
            if (row != null) {//行数据不为空
                List<String> list = new ArrayList<>();
                for (Cell cell : row) {//获取行中的单元格
                    if (cell != null) {
                        cell.setCellType(CellType.STRING);
                        String value = cell.getStringCellValue();//读取数据
                        if (value != null && !"".equals(value)) {//当读取到的数据不为空时才存入list
                            list.add(value);
                        }
                    }
                }
                if (list.size() > 0) {//list不空才开始封装
                    //将一行数据封装到一个对象中
                    SupplierDTO supplierDTO = new SupplierDTO(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), Integer.parseInt(list.get(5)), list.get(6), list.get(7), list.get(8), Integer.parseInt(list.get(9)));
                    Supplier supplier = new Supplier();
                    BeanUtils.copyProperties(supplierDTO, supplier);//属性拷贝
                    supplier.setImportTime(LocalDateTime.now());
                    supplier.setImportUserid(1);//TODO importUserid需要和登录逻辑配合获取

                    Supplier one = lambdaQuery()//查询这条数据在数据库中是否已存在
                            .eq(supplier.getSupplierName() != null, Supplier::getSupplierName, supplier.getSupplierName())
                            .one();
                    if (one != null) {//数据库中不存在这个供应商才插入
                        save(supplier);//插入数据库
                    }
                }
            }
        }
    }

    @Override
    public String supplierDetail(String supplierName) {
        String path = lambdaQuery()
                .eq(supplierName != null, Supplier::getSupplierName, supplierName)
                .one()
                .getFilePath();
        return path;
    }

    @Override
    public CommonResult<Page<Supplier>> pageQuery(int page, int pageSize, String name) {
        Page<Supplier> supplierPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Supplier> query = new LambdaQueryWrapper<>();
        query.like(Supplier::getSupplierName, name);
        query.ne(Supplier::getIsDeleted, 1);
        this.page(supplierPage, query);
        return CommonResult.success(supplierPage);
    }

    @Override
    public void inserSupplier(Supplier supplier) {
        Supplier one = lambdaQuery()//根据名字查询供应商在数据库中是否已存在
                .eq(supplier.getSupplierName() != null, Supplier::getSupplierName, supplier.getSupplierName())
                .one();
        supplier.setImportTime(LocalDateTime.now());
        supplier.setImportUserid(1);//TODO 补全创建人和创建时间
        if (one != null) {//之前不存在同名供应商才可以插入
            save(supplier);
        }
    }

}




