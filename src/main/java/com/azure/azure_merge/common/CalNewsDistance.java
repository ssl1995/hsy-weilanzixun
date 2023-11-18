package com.azure.azure_merge.common;

import com.azure.azure_merge.entity.News;
import com.azure.azure_merge.service.NewsService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import info.debatty.java.stringsimilarity.NormalizedLevenshtein;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yuwolianxi
 */
@Slf4j
@EnableScheduling
@Component
public class CalNewsDistance {
    @Autowired
    private NewsService newsService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Scheduled(cron = "0 */30 * * * ?")
    public void calDistance() {
        LambdaQueryWrapper<News> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(News::getNewsId, News::getNewsTitle).eq(News::getIsDeleted, 0).eq(News::getIsPublished, 1);
        List<News> newsList = newsService.list(queryWrapper);
        List<Integer> newsIdList = newsList.stream().map(News::getNewsId).collect(Collectors.toList());
        // do something with newsIdList
        List<String> newsTitleList = newsList.stream().map(News::getNewsTitle).collect(Collectors.toList());
        Map<Integer, List<Integer>> distanceMap = new HashMap<>();
        ListOperations listOperations = redisTemplate.opsForList();
        Set<String> keys = redisTemplate.keys("news_" + "*");
        redisTemplate.delete(keys);
        System.out.println(LocalTime.now());
        int len = newsTitleList.size();
        for (int i = 0; i < len - 1; i++) {
            redisTemplate.delete(newsIdList.get(i).toString());
            List<String> list = new ArrayList<>();
            for (int j = i + 1; j < len; j++) {
                String newsTitle1 = newsTitleList.get(i);
                String newsTitle2 = newsTitleList.get(j);
                NormalizedLevenshtein l = new NormalizedLevenshtein();
                double distance = l.distance(newsTitle1, newsTitle2);
                System.out.println("distance = " + distance);
                if (distance > 0.8) {
                    list.add(newsIdList.get(j).toString());
                }
            }
            for (int j = 0; j < list.size(); j++) {
                listOperations.leftPush("news_" + newsIdList.get(i).toString(), list.get(j).toString());
            }
            // 取值
            List<String> range = listOperations.range("news_" + newsIdList.get(i).toString(), 0, -1);
            for (String value : range) {
                System.out.println(value);
            }
        }
    }
}

