package io.jupeng.bitcoin.scheduler;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import org.mybatis.logging.Logger;


import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Component
public class BitcoinScheduler {

    @Autowired
    BitcoinRest bitcoinRest;

    @Autowired
    private SimpMessagingTemplate simpMessAginTemplate;

    private JSONObject originMempoolTx =new JSONObject();


    private List<JSONObject> deltaTx =new LinkedList<>();

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Scheduled(cron = "${bitcoin.sync.interval}")
    public void syncData(){

    }
    @Scheduled(cron = "${bitcoin.syMempoolTx.interval }")
    public void syMempoolTx(){
        JSONObject nempoolContenTs = bitcoinRest.getMempoolContents();

        for(Map.Entry<String,Object> stringObjectEntry:nempoolContenTs.entrySet())
        {
            String key = stringObjectEntry.getKey();

            if(originMempoolTx.containsKey(key)){
                JSONObject jsonObject = nempoolContenTs.getJSONObject(key);
                jsonObject.put("txid",key);

                deltaTx.add(jsonObject);
        }

        }

        simpMessAginTemplate.convertAndSend("bitcoin/deltaTx",deltaTx);
        deltaTx =new LinkedList<>();
        originMempoolTx = nempoolContenTs;

    }

}
