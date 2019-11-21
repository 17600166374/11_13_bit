package io.jupeng.bitcoin.scheduler;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import org.mybatis.logging.Logger;


import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        int size = originMempoolTx.size();
        int newSize = nempoolContenTs.size();
        if(newSize <= size){
            return ;
        }



        for(Map.Entry<String,Object> stringObjectEntry:nempoolContenTs.entrySet())
        {
            String key = stringObjectEntry.getKey();

            if(originMempoolTx.containsKey(key)){
                JSONObject jsonObject = nempoolContenTs.getJSONObject(key);
                jsonObject.put("txid",key);

                deltaTx.add(jsonObject);
        }

        }
        List<JSONObject> deltaTxesJson = deltaTx.stream().map(t -> {
            JSONObject tJson = new JSONObject();

            tJson.put("txid", t.getInteger("txid"));
            tJson.put("wtxid", t.getString("wtxid"));
            tJson.put("time", t.getLong("time"));
            return tJson;
        }).collect(Collectors.toList());
        List<JSONObject> sortDeltaTxesJson = deltaTxesJson.stream().sorted(Comparator.comparingLong(t -> t.getLong("time"))).collect(Collectors.toList());

        simpMessAginTemplate.convertAndSend("bitcoin/deltaTx",sortDeltaTxesJson);
        deltaTx =new LinkedList<>();
        originMempoolTx = nempoolContenTs;

    }

}
