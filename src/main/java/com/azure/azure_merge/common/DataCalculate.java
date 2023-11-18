package com.azure.azure_merge.common;

import java.time.LocalDateTime;
import java.time.Period;

public class DataCalculate {
    public static LocalDateTime calculateDateOneWeekAgo(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算前一周的时间
        LocalDateTime oneWeekAgo = currentDateTime.minus(Period.ofWeeks(1));
        System.out.println(oneWeekAgo);
        return oneWeekAgo;
    }

    public static LocalDateTime calculateDateOneDayAgo(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算前一天的时间
        LocalDateTime oneDayAgo = currentDateTime.minus(Period.ofDays(1));
        return oneDayAgo;
    }

    public static LocalDateTime calculateDateOneMonthAgo(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算前一周的时间
        LocalDateTime oneMonthAgo = currentDateTime.minus(Period.ofMonths(1));
        return oneMonthAgo;
    }

    public static LocalDateTime calculateDateThreeMonthsAgo(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算前一周的时间
        LocalDateTime ThreeMonthsAgo = currentDateTime.minus(Period.ofMonths(3));
        return ThreeMonthsAgo;
    }

    public static LocalDateTime calculateDateSixMonthsAgo(){
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 计算前一周的时间
        LocalDateTime SixMonthsAgo = currentDateTime.minus(Period.ofMonths(6));
        return SixMonthsAgo;
    }
}
