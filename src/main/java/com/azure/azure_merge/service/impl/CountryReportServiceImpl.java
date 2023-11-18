package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.CountryReport;
import com.azure.azure_merge.mapper.CountryReportMapper;
import com.azure.azure_merge.service.CountryReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【country_report(国家报告表)】的数据库操作Service实现
* @createDate 2023-11-02 10:40:45
*/
@Service
public class CountryReportServiceImpl extends ServiceImpl<CountryReportMapper, CountryReport>
    implements CountryReportService{

}




