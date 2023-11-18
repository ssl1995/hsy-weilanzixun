package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Record;
import com.azure.azure_merge.mapper.RecordMapper;
import com.azure.azure_merge.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【record(收藏记录表 - 用于存储用户的收藏记录信息)】的数据库操作Service实现
* @createDate 2023-11-02 09:53:48
*/
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record>
    implements RecordService{

}




