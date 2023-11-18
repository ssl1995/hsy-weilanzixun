package com.azure.azure_merge.common.convert;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

public class JsonConverter implements Converter<String> {

    public String convertToJavaData(ReadCellData cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String stringValue = cellData.getStringValue();
        String jsonString = JSONUtils.toJSONString(stringValue);
        return jsonString;
    }
}
