package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Industry;
import com.azure.azure_merge.mapper.IndustryMapper;
import com.azure.azure_merge.service.IndustryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【industry(行业表)】的数据库操作Service实现
* @createDate 2023-11-02 15:54:21
*/
@Service
public class IndustryServiceImpl extends ServiceImpl<IndustryMapper, Industry>
    implements IndustryService{

}




