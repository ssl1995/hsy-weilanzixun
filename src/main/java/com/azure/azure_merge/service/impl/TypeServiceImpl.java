package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.Type;
import com.azure.azure_merge.mapper.TypeMapper;
import com.azure.azure_merge.service.TypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【type(新闻类型表)】的数据库操作Service实现
* @createDate 2023-11-02 15:55:55
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

}




