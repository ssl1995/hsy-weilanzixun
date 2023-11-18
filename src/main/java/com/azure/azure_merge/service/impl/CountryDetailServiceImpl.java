package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.CountryDetail;
import com.azure.azure_merge.mapper.CountryDetailMapper;
import com.azure.azure_merge.service.CountryDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【country_detail(国家概况)】的数据库操作Service实现
* @createDate 2023-11-02 15:54:29
*/
@Service
public class CountryDetailServiceImpl extends ServiceImpl<CountryDetailMapper, CountryDetail>
    implements CountryDetailService{

}




