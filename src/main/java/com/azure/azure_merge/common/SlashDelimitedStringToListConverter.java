package com.azure.azure_merge.common;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.util.Arrays;
import java.util.List;

public class SlashDelimitedStringToListConverter implements Converter<List<String>> {

    public List<String> convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String[] parts = cellData.getStringValue().split("/");
        return Arrays.asList(parts);
    }


}
