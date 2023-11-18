package com.azure.azure_merge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class CountryReport implements Serializable {
    private static final long serialVersionUID = 1L;
    //报告id
    @TableId(type = IdType.AUTO)
    private int reportId;
    //报告关键词
    private String reportKeywords;
    //所属国家
    private String reportCountry;
    //报告题目
    private String reportTitle;
    //报告路径
    private String reportPath;
    //导入时间
    private LocalDateTime importTime;
    //导入管理员id
    private int importUserid;
    //是否删除
    private int isDeleted;


}
