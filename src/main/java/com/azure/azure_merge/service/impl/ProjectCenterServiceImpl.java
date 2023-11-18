package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.ProjectCenter;
import com.azure.azure_merge.mapper.ProjectCenterMapper;
import com.azure.azure_merge.service.ProjectCenterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【project_center(项目中心表，用于存储项目信息)】的数据库操作Service实现
* @createDate 2023-11-02 10:40:31
*/
@Service
public class ProjectCenterServiceImpl extends ServiceImpl<ProjectCenterMapper, ProjectCenter>
    implements ProjectCenterService{

}




