package com.azure.azure_merge.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.azure.azure_merge.common.SlashDelimitedStringToListConverter;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProjectCenter implements Serializable {

    private static final long serialVersionUID = 1L;
    @ExcelProperty("项目id")
    @TableId(type = IdType.AUTO)
    private Long projectId;
    @ExcelProperty("项目名称")
    private String projectName;
    //所属国家
    @ExcelProperty("项目所在国家")
    private String projectLocation;
    //招标业主
    @ExcelProperty("招标业主")
    private String biddingOwner;
    //业主地址及联系方式
    @ExcelProperty("业主地址及联系方式")
    private String addressNumber;
    //项目简介
    @ExcelProperty("招标简介")
    private String projectIntroduction;
    //发布时间
    @ExcelProperty("发布时间")
    private LocalDateTime projectPublishTime;
    @ExcelProperty("截止招标时间")
    private LocalDateTime projectDeadline;
    @ExcelProperty("标书路径")
    private String filePath;
    //咨询管理员ID
    @ExcelProperty("咨询管理员ID")
    private int adminId;
    //项目入库时间
    @ExcelProperty("项目入库时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime projectEditTime;
    //是否删除
    @ExcelProperty("是否删除")
    private int isDeleted;

    @ExcelProperty(converter = SlashDelimitedStringToListConverter.class, value = "区域")
    private String projectRegion;
    @ExcelProperty("招标预算")
    private BigDecimal projectPrice;
    @ExcelProperty(converter = SlashDelimitedStringToListConverter.class, value = "项目所属板块")
    private String projectPlate;
    @ExcelProperty("城市")
    private String city;
    @ExcelProperty("州")
    private String state;
    @ExcelProperty("电子邮件")
    private String emailId;
    @ExcelProperty("招标号")
    private String tenderNoticeNo;
    @ExcelProperty("国内招标或国际招标")
    private String biddingType;
    @ExcelProperty("招标详情")
    private String tendersDetails;
    @ExcelProperty("招标预算币种")
    private String currency;

}
