package com.azure.azure_merge.dto;

import lombok.Data;

import java.util.List;

@Data
public class VipSearchDTO {
    private List<String> regionName;
    private List<String> typeName;
    private List<String> industryName;
    private int page;
    private int pageSize;
}
