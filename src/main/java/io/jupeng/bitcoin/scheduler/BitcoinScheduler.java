package io.jupeng.bitcoin.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BitcoinScheduler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${bitcoin.sync.interval}")
    public void syncData(){
        logger.info("begin to sync bitcoin data");
    }

}
