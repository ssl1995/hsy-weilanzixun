package com.azure.azure_merge.common;

public enum PriceTypeEnum {
    /**
    100万以下
    * */
    LESS_HUNDRED(1),
    /**
    * 100-1000万
    * */
    ONEHUNDRED_ONETHOUSAND(2),
    /**
     * 1000-5000万
     * **/
    ONETHOUSAND_FIVETHOUSAND(3),
    /**
     * 5000万-1亿
     * **/
    FIVETHOUSAND_ONEHUNDREDMILLION(4),
    /**
     * 1亿-10亿
     * **/
    ONEHUNDREDMILLION_ONEBILLION(5),
    /**
     * 10亿以上
     * **/
    MORE_ONEBILLION(6);


    private final int code;

    PriceTypeEnum(int code) {
        this.code = code;

    }


    public int getCode() {
        return code;
    }
}
