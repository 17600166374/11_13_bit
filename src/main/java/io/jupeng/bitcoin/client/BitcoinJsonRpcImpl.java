package io.jupeng.bitcoin.client;

import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

@Service
public class BitcoinJsonRpcImpl implements BitcoinJsonRpc{

    private JsonRpcHttpClient jsonRpcHttpClient;

    public BitcoinJsonRpcImpl(@Value("${bitcoin.jsonrpc.url}") String url,
                              @Value("${bitcoin.jsonrpc.username}") String username,
                              @Value("${bitcoin.jsonrpc.password}") String password) throws MalformedURLException {
        jsonRpcHttpClient = new JsonRpcHttpClient(new URL(url));
        HashMap<String, String> headers = new HashMap<>();

        String str = username+":"+password;
        String str64 = Base64.getEncoder().encodeToString(str.getBytes());
        headers.put("Authorization", "Basic "+str64);
        jsonRpcHttpClient.setHeaders(headers);

    }

    @Override
    public JSONObject getRawTransaction(String txid) throws Throwable {
        JSONObject jsonObject = jsonRpcHttpClient.invoke("getrawtransaction", new Object[] { txid, true }, JSONObject.class);
        return jsonObject;
    }
}
