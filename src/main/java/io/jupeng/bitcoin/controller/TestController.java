package io.jupeng.bitcoin.controller;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinJsonRpcImpl;
import io.jupeng.bitcoin.client.BitcoinRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private BitcoinJsonRpcImpl bitcoinJsonRpc;

    @GetMapping("/hello")
    public String hello() throws Throwable {
        JSONObject jsonObject = bitcoinJsonRpc.getRawTransaction("df53a7893a26f245c62905984d6cab8fcf6df3eeac24aa450b0b4928a7181453");


        return null;
    }
}
