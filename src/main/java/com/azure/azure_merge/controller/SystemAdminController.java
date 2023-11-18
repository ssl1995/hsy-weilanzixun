package com.azure.azure_merge.controller;

import com.azure.azure_merge.common.CommonResult;
import com.azure.azure_merge.entity.Vips;
import com.azure.azure_merge.mapper.VipsMapper;
import com.azure.azure_merge.service.VipsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@ResponseBody
@RequestMapping("/systemAdmin")
@Slf4j
@Api(tags = "系统管理员接口")
public class SystemAdminController {

    @Autowired
    private VipsService vipsService;
    @Autowired
    private VipsMapper vipsMapper;

    @ApiOperation(value = "列表显示所有VIP用户", notes = "vips返回所有用户信息")
    @GetMapping("/page/all-vip-info")
    public CommonResult<Page<Vips>> page(int page, int pageSize, String userName, String companyName, String phoneNumber, Date regDate) {
        log.info("page = {},pageSize = {},user_name = {},company_name = {},phone_number = {},reg_date = {}",
                page, pageSize, userName, companyName, phoneNumber, regDate);
        //分页构造器
        Page<Vips> pageInfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        queryWrapper.eq(Vips::getVipsStatus, 0);//where vips_status=0
        //排序条件
        queryWrapper.orderByDesc(Vips::getUserName);//按姓名排序
        //执行查询
        vipsService.page(pageInfo, queryWrapper);
        //返回状态码
        return CommonResult.success(pageInfo);
    }

    /**
     * 根据用户名和单位名查询用户
     * @param page
     * @param pageSize
     * @param companyName
     * @param userName
     * @return
     */

    @ApiOperation(value = "搜索单位名查询用户", notes = "vips根据单位名company_name返回对应用户信息")
    @GetMapping("/page/search")
    public CommonResult<Page<Vips>> pageCName(int page, int pageSize, String companyName, String userName) {
        log.info("page = {},pageSize = {},company_name = {}", page, pageSize, companyName);
        //分页构造器
        Page<Vips> pageInfo = new Page<>(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        queryWrapper.like(Vips::getCompanyName, companyName).like(Vips::getUserName, userName);
        queryWrapper.eq(Vips::getVipsStatus, 0);//and vips_status=0
        //执行查询
        vipsService.page(pageInfo, queryWrapper);
        //返回状态码
        return CommonResult.success(pageInfo);
    }


    @ApiOperation(value = "管理员新建VIP用户")
    @PostMapping("/vip-insert")
    public CommonResult<String> VipInsert(@RequestBody Vips vips) {
        log.info("vips={}", vips);
        Date new_date = new Date();
        vips.setRegDate(new_date);
        vipsService.save(vips);
        return CommonResult.success("新建成功");
    }
//    //系统管理员新建单个VIP用户：vips插入用户信息
//    @PostMapping("/vip-insert1")
//    public Integer VipInsert1(Vips vips){
//        return vipsMapper.insertVip(vips);
//    }


    @ApiOperation(value = "系统管理员修改用户信息", notes = "vips根据user_id更新用户信息")
    @PutMapping("/vip-update")
    public CommonResult<String> VipUpdate(Vips vips) {
        log.info("vips = {}", vips);
        //构造更新
        UpdateWrapper<Vips> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", vips.getUserId())//where user_id=#{user_id}
                //.eq("vips_status",0)//and vips_status=0
                .set("user_name", vips.getUserName())
                .set("user_password", vips.getUserPassword())
                .set("phone_number", vips.getPhoneNumber())
                .set("company_number", vips.getCompanyName())
                .set("email", vips.getEmail());//？？11.7新问题：如果需要把用户从删除状态恢复，另行修改
        //执行修改
        int up_result = vipsMapper.update(vips, updateWrapper);
        if (up_result == 1) {//成功找到并修改数据，根据user_id只会更新一条数据
            return CommonResult.success("修改成功");
        } else {//修改失败
            return CommonResult.failed("修改失败");
        }
    }
//    //系统管理员修改用户信息：vips根据user_id更新用户信息
//    @PutMapping("/vip-update1")
//    public Integer VipUpdate1(Vips vips){
//        return vipsMapper.updateVip(vips);
//    }


    @ApiOperation(value = "管理员删除VIP用户信息", notes = "删除用户")
    @PutMapping("/vip-delete")
    public CommonResult<String> VipDelete(Vips vips) {
        log.info("vips = {}", vips);
        //构造更新
        UpdateWrapper<Vips> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id", vips.getUserId())//where user_id=#{user_id}
                //.eq("vips_status",0)//and vips_status=0
                .set("vips_status", 1);//用户状态置1，表示已移除
        boolean del_result = vipsService.updateById(vips);
        if (del_result) {
            return CommonResult.success("删除成功");
        } else {
            return CommonResult.failed("删除失败");
        }
    }
//    //系统管理员删除用户信息：vips根据user_id删除用户信息
//    //假删除
//    @PutMapping("/vip-delete1/{user_id}")
//    public Integer VipDelete1(@PathVariable("user_id") Integer user_id){
//        return vipsMapper.deleteVip(user_id);
//    }

}
