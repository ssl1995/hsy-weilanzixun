package com.azure.azure_merge.common;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.azure.azure_merge.entity.ProjectCenter;
import com.azure.azure_merge.mapper.ProjectCenterMapper;

public class ProjectReadListener implements ReadListener<ProjectCenter> {
    private ProjectCenterMapper projectMapper;
    public ProjectReadListener(ProjectCenterMapper projectMapper){
        this.projectMapper = projectMapper;
    }

    @Override
    public void invoke(ProjectCenter project, AnalysisContext analysisContext) {
        System.out.println("读取到："+project);
        projectMapper.insert(project);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完毕！");
    }
}
