package com.azure.azure_merge.mapper;

import com.azure.azure_merge.entity.ProjectCenter;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yuwolianxi
* @description 针对表【project_center(项目中心表，用于存储项目信息)】的数据库操作Mapper
* @createDate 2023-11-02 10:40:31
* @Entity com.azure.azure_merge.entity.ProjectCenter
*/
@Mapper
public interface ProjectCenterMapper extends BaseMapper<ProjectCenter> {

}




