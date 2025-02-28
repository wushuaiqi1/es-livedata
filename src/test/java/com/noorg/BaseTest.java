package com.noorg;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class BaseTest {

    @Value("${load}")
    private String lastCoverConfig;

    @Test
    public void TestConfig(){
        log.info("配置最后最加载的:{}",lastCoverConfig);
    }
}
