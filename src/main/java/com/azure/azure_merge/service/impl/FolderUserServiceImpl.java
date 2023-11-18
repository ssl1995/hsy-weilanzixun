package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.FolderUser;
import com.azure.azure_merge.mapper.FolderUserMapper;
import com.azure.azure_merge.service.FolderUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【folder_user(收藏标签-用户关联表)】的数据库操作Service实现
* @createDate 2023-11-02 09:53:57
*/
@Service
public class FolderUserServiceImpl extends ServiceImpl<FolderUserMapper, FolderUser>
    implements FolderUserService{

}




