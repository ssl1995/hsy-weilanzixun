package com.azure.azure_merge.service.impl;

import com.azure.azure_merge.entity.News;
import com.azure.azure_merge.mapper.NewsMapper;
import com.azure.azure_merge.service.NewsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
* @author yuwolianxi
* @description 针对表【news(资讯表)】的数据库操作Service实现
* @createDate 2023-10-21 12:53:38
*/
@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News>
    implements NewsService{

}




