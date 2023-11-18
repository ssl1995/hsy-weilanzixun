package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Role;
import com.azure.azure_merge.mapper.RoleMapper;
import com.azure.azure_merge.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【role(角色表)】的数据库操作Service实现
* @createDate 2023-10-20 16:39:17
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




