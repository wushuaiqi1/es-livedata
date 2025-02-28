package com.noorg;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class EsLiveDataApplication {

    public static void main(String[] args) {
        log.info("EsLiveDataApplication 启动...");
        SpringApplication.run(EsLiveDataApplication.class, args);
        log.info("EsLiveDataApplication 启动成功...");
    }
}
