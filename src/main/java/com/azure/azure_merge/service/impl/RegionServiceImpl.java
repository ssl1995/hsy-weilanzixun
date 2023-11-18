package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Region;
import com.azure.azure_merge.mapper.RegionMapper;
import com.azure.azure_merge.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【region(地区表)】的数据库操作Service实现
* @createDate 2023-11-02 15:54:25
*/
@Service
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region>
    implements RegionService{

}




