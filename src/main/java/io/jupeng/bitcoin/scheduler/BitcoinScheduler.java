package io.jupeng.bitcoin.scheduler;

import org.mybatis.logging.Logger;


import org.mybatis.logging.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BitcoinScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${bitcoin.sync.interval}")
    public void syncData(){

    }

}
