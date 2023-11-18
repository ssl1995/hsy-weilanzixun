package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Admin;
import com.azure.azure_merge.mapper.AdminMapper;
import com.azure.azure_merge.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【admin(存储管理员信息的表)】的数据库操作Service实现
* @createDate 2023-10-20 16:39:03
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

}




