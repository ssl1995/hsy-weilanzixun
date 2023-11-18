package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.RolePermission;
import com.azure.azure_merge.mapper.RolePermissionMapper;
import com.azure.azure_merge.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【role_permission(角色-权限关联表)】的数据库操作Service实现
* @createDate 2023-10-20 16:39:20
*/
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission>
    implements RolePermissionService{

}




