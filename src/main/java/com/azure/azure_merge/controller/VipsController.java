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
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("/vips")
@Api(tags = "Vips登录/退出")
@Slf4j
public class VipsController {
    @Autowired
    private VipsService vipsService;

    @Autowired
    private VipsMapper vipsMapper;

    /**
     * 登录
     */
    @PostMapping("/login")
    public CommonResult<Object> login(HttpServletRequest request, @RequestBody Vips vips) {
        // 1. 将页面提交的密码password进行md5加密处理
        String password = vips.getUserPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        log.info(password);

        // 2. 根据页面提交的用户名username查询数据库
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Vips::getUserName, vips.getUserName());
        Vips vips1 = vipsService.getOne(queryWrapper);

        // 3. 如果没有查询到则返回登录失败结果
        if (vips1 == null) {
            return CommonResult.failed("没有此账户，请联系管理员");
        }

        // 4. 密码比对，如果不一致则返回登录失败结果
        if (!vips1.getUserPassword().equals(password)) {
            return CommonResult.failed("密码不正确");
        }

        // 5. 查看员工状态，如果为已禁用状态，则返回员工已禁用结果
        if (vips1.getVipsStatus() == 0) {
            return CommonResult.failed("该账号已被禁用");
        }

        // 6. 登录成功，将员工id存入Session并返回登录成功结果
        request.getSession().setAttribute("vips", vips1.getUserId());
        return CommonResult.success("登录成功");
    }

    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public CommonResult<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("vips");
        return CommonResult.success("退出成功");
    }


    /**
     * 孙雨佳
     */


    @ApiOperation(value = "列表显示所有VIP用户", notes = "vips返回所有用户信息")
    @GetMapping("/page/all-vip-info")
    public CommonResult<Page> page(int page, int pageSize, String user_name, String company_name, String phone_number, Date reg_date) {
        log.info("page = {},pageSize = {},user_name = {},company_name = {},phone_number = {},reg_date = {}",
                page, pageSize, user_name, company_name, phone_number, reg_date);
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
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
//    //表格初始显示所有用户：vips返回所有用户信息
//    @GetMapping("/vips-list")
//    public List<Vips> VipsList(){
//        return vipsMapper.selectAllVips();
//    }


    @ApiOperation(value = "搜索用户名查询用户", notes = "vips根据用户名user_name返回对应用户信息")
    @GetMapping("/page/search-by-username")
    public CommonResult<Page> pageUName(int page, int pageSize, String user_name) {
        log.info("page = {},pageSize = {},user_name = {}", page, pageSize, user_name);
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        if (user_name != null) {
            queryWrapper.like(StringUtils.isNotEmpty(user_name), Vips::getUserName, user_name);//where user_name=#{user_name}
        }
        queryWrapper.eq(Vips::getVipsStatus, 0);//and vips_status=0
        //执行查询
        vipsService.page(pageInfo, queryWrapper);
        //返回状态码
        return CommonResult.success(pageInfo);
    }
//    //搜索用户名查找用户：vips根据用户名user_name返回对应用户信息
//    @GetMapping("/vip-search-by-username/{user_name}")
//    public List<Vips> searchByUName(@PathVariable("user_name") String user_name){
//        return vipsMapper.selectVipsByUName(user_name);
//    }


    @ApiOperation(value = "搜索单位名查询用户", notes = "vips根据单位名company_name返回对应用户信息")
    @GetMapping("/page/search-by-companyname")
    public CommonResult<Page> pageCName(int page, int pageSize, String company_name) {
        log.info("page = {},pageSize = {},company_name = {}", page, pageSize, company_name);
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        if (company_name != null) {
            queryWrapper.like(StringUtils.isNotEmpty(company_name), Vips::getUserName, company_name);//where company_name=#{company_name}
        }
        queryWrapper.eq(Vips::getVipsStatus, 0);//and vips_status=0
        //执行查询
        vipsService.page(pageInfo, queryWrapper);
        //返回状态码
        return CommonResult.success(pageInfo);
    }
//    //搜索单位名查找用户：vips根据单位名company_name返回对应用户信息
//    @GetMapping("/vip-search-by-companyname/{company_name}")
//    public List<Vips> searchByCompanyName(@PathVariable("company_name") String company_name){
//        return vipsMapper.selectVipsByCompanyName(company_name);
//    }


    @ApiOperation(value = "联合搜索用户名和单位查找用户", notes = "vips根据用户名user_name和单位名company_name返回对应用户信息")
    @GetMapping("/page/search-by-uname-cname")
    public CommonResult<Page> pageUNameCName(int page, int pageSize, String user_name, String company_name) {
        log.info("page = {},pageSize = {},user_name = {},user_name = {},company_name = {}", page, pageSize, user_name, company_name);
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
        //条件构造器
        LambdaQueryWrapper<Vips> queryWrapper = new LambdaQueryWrapper<>();
        //过滤条件
        if (user_name != null && company_name != null) {
            queryWrapper.like(StringUtils.isNotEmpty(user_name), Vips::getUserName, user_name);//where user_name=#{user_name}
            queryWrapper.like(StringUtils.isNotEmpty(company_name), Vips::getCompanyName, company_name);//and company_name=#{company_name}
        }
        queryWrapper.eq(Vips::getVipsStatus, 0);//and vips_status=0
        //执行查询
        vipsService.page(pageInfo, queryWrapper);
        //返回状态码
        return CommonResult.success(pageInfo);
    }
//    //联合搜索用户名和单位查找用户：vips根据用户名user_name和单位名company_name返回对应用户信息
//    @GetMapping("/vip-search-by-uname-cname/{user_name}/{company_name}")
//    public List<Vips> searchByUNameCName(@PathVariable("user_name") String user_name,
//                                         @PathVariable("company_name") String company_name){
//        return vipsMapper.selectVipsByUNameCName(user_name,company_name);
//    }


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
