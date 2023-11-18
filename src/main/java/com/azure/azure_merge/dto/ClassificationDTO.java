package com.azure.azure_merge.dto;


import com.azure.azure_merge.entity.ProjectCenter;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ClassificationDTO extends ProjectCenter {
    //int page, int pageSize, String region, String plate, double minPrice, double maxPrice, int timeType
    private int page;
    private int pageSize;
    private String priceType;
    private String timeType;


}
