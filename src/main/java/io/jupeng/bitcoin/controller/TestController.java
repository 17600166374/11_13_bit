package io.jupeng.bitcoin.controller;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BitcoinRest bitcoinRest;

    @GetMapping("/hello")
    public String hello(){
        JSONObject chainInfo = bitcoinRest.getChainInfo();
        JSONObject blockhashByHeight = bitcoinRest.getBlockhashByHeight(10);
        List<JSONObject> blockHeaders = bitcoinRest.getBlockHeaders(5, "0000000000000183691e29729ea6cec432fa9d3521507fd7c728a2893dcdb708");
        JSONObject blockNoTxDetails = bitcoinRest.getBlockNoTxDetails("00000000001c31eefd455987ae4b805a961edbb2df195b57d0b1481c4c9d5f80");
        JSONObject blockInfo = bitcoinRest.getBlockInfo("00000000001c31eefd455987ae4b805a961edbb2df195b57d0b1481c4c9d5f80");
        JSONObject tx = bitcoinRest.getTransaction("e00fd08ec52cc53312a3d97ee91c0662d952c564534aceb84a8e038a73230019");
        JSONObject mempoolInfo = bitcoinRest.getMempoolInfo();
        JSONObject mempoolContents = bitcoinRest.getMempoolContents();
        JSONObject utxo = bitcoinRest.getUTXO("e00fd08ec52cc53312a3d97ee91c0662d952c564534aceb84a8e038a73230019", 0);
        return null;
    }
}
