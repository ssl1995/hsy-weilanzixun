package com.azure.azure_merge.mapper;

import com.azure.azure_merge.entity.CountryReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yuwolianxi
* @description 针对表【country_report(国家报告表)】的数据库操作Mapper
* @createDate 2023-11-02 10:40:45
* @Entity com.azure.azure_merge.entity.CountryReport
*/
@Mapper
public interface CountryReportMapper extends BaseMapper<CountryReport> {

}




