package com.noorg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class BaseTest {

    @Value("${load}")
    private String lastCoverConfig;

    @Value("${elasticsearch.host}")
    private String elasticsearchUrl;

    @Value("${redis.host}")
    private String redisHost;
    @Value("${redis.port}")
    private String redisPort;



    @Test
    public void TestConfig(){
        log.info("配置最后最加载的:{}",lastCoverConfig);
    }

    @Test
    public void LoadNacos(){
        log.info("{},host:{},port:{}",elasticsearchUrl,redisHost,redisPort);
    }
}
