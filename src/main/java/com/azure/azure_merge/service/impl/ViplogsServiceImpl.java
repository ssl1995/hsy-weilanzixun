package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Viplogs;
import com.azure.azure_merge.mapper.ViplogsMapper;
import com.azure.azure_merge.service.ViplogsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【viplogs(点击记录表 - 用于存储用户的点击记录信息)】的数据库操作Service实现
* @createDate 2023-11-11 13:04:02
*/
@Service
public class ViplogsServiceImpl extends ServiceImpl<ViplogsMapper, Viplogs>
    implements ViplogsService{

}




