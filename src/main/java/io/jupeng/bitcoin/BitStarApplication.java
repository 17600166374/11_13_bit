package io.jupeng.bitcoin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableFeignClients
@MapperScan("io.jupeng.bitcoin.dao")
@EnableAsync
public class BitStarApplication {
    public static void main(String[] args) {
        SpringApplication.run(BitStarApplication.class,args);
    }
}
