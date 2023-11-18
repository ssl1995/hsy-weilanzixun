package com.azure.azure_merge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO implements Serializable {
    private String supplierName;

    private String logoPath;

    private String supplierIntroduction;

    private String supplierType;

    private String supplierAddress;

    private Integer country;

    private String contactPerson;

    private String contactNumber;

    private String filePath;

    private int isDeleted;
}
