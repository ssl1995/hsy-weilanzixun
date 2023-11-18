package com.azure.azure_merge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDTO {

    private String projectName;
    //所属国家

    private String projectLocation;
    //招标业主

    private String biddingOwner;
    //业主地址及联系方式

    private String addressNumber;
    //项目简介

    private String projectIntroduction;
    //发布时间

    private LocalDateTime projectPublishTime;

    private LocalDateTime projectDeadline;

    private String filePath;

    private List<String> projectRegion;

    private BigDecimal projectPrice;

    private List<String> projectPlate;

    private String city;

    private String state;

    private String emailId;

    private String biddingType;

    private String tendersDetails;

    private String currency;

}
