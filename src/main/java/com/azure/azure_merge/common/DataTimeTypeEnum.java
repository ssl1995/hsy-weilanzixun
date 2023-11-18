package com.azure.azure_merge.common;


public enum DataTimeTypeEnum {
    /**
     * 一天
     */
    ONE_DAY(1),
    /**
     * 一周
     */
    ONE_WEEK(2),
    /**
     * 近一个月
     */
    ONE_MONTH(3),
    /**
     * 近三个月
     */
    THREE_MONTH(4),
    /**
     * 近六个月
     */
    SIX_MONTH(5),
    /**
     * 大于六个月
     */
    SIX_MONTH2(6),

    /**
     * 自定义
     */
    CUSTOMIZE(7)
    ;
    private final int code;


    DataTimeTypeEnum(int code) {
        this.code = code;

    }


    public int getCode() {
        return code;
    }



}
