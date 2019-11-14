package io.jupeng.bitcoin.client;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "bitcoinRest", url = "${bitcoin.rest.address}")
public interface BitcoinRest {

    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    List<JSONObject> getBlockHeaders(@PathVariable Integer count, @PathVariable String blockhash);

    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getBlockNoTxDetails(@PathVariable String blockhash);

    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlockInfo(@PathVariable String blockhash);

    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable String txhash);

    @GetMapping("/rest/blockhashbyheight/{height}.json")
    JSONObject getBlockhashByHeight(@PathVariable Integer height);

    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

    @GetMapping("/rest/getutxos/{txid}-{n}.json")
    JSONObject getUTXO(@PathVariable String txid, @PathVariable Integer n);
}
