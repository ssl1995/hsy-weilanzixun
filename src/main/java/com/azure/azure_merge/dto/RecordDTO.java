package com.azure.azure_merge.dto;


import com.azure.azure_merge.entity.Record;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecordDTO extends Record {
    private String newsTitle;  // 新闻标题
    private String newsContent; // 新闻内容
    private LocalDateTime newsPublishedTime; // 新闻发布时间
    private String newsLink; // 新闻链接
    private String newsThumbnail; // 新闻缩略图
}
