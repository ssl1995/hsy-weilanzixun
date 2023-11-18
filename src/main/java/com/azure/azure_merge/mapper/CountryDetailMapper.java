package com.azure.azure_merge.mapper;

import com.azure.azure_merge.entity.CountryDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author yuwolianxi
* @description 针对表【country_detail(国家概况)】的数据库操作Mapper
* @createDate 2023-11-02 15:54:29
* @Entity com.azure.azure_merge.entity.CountryDetail
*/
@Mapper
public interface CountryDetailMapper extends BaseMapper<CountryDetail> {

}




