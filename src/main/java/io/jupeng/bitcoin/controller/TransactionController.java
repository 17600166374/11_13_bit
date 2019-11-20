package io.jupeng.bitcoin.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import io.jupeng.bitcoin.constants.PageConfig;
import io.jupeng.bitcoin.dto.PageDTO;
import io.jupeng.bitcoin.po.Block;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.po.TransactionDetail;
import io.jupeng.bitcoin.service.BlockService;
import io.jupeng.bitcoin.service.TransactionDetailService;
import io.jupeng.bitcoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDetailService transactionDetailService;

    @GetMapping("/getRecentUnconfirmed")
    public List<JSONObject> getRecentUnconfirmed(@RequestParam(required = false, defaultValue = "20") Integer size){
        return null;
    }

    @GetMapping("/getByTxid")
    public JSONObject getByTxid(@RequestParam String txid){
        Transaction tx = transactionService.getByTxid(txid);

        JSONObject txJson = new JSONObject();
        txJson.put("txid", tx.getTxid());
        txJson.put("txhash", tx.getTxhash());
        txJson.put("time", tx.getTime());
        txJson.put("fees", tx.getFees());
        txJson.put("totalOutput", tx.getTotalOutput());

        List<TransactionDetail> txDetails = transactionDetailService.getByTransactionId(tx.getTransactionId());
        List<JSONObject> txDetailJsons = txDetails.stream().map(txDetail -> {
            JSONObject txDetailJson = new JSONObject();
            txDetailJson.put("address", txDetail.getAddress());
            txDetailJson.put("type", txDetail.getType());
            txDetailJson.put("amount", Math.abs(txDetail.getAmount()));
            return txDetailJson;
        }).collect(Collectors.toList());
        txJson.put("txDetails", txDetailJsons);

        return txJson;
    }

    @GetMapping("/getByBlockhashWithPage")
    public PageDTO<JSONObject> getByBlockhashWithPage(@RequestParam String blockhash,
                                                      @RequestParam(required = false, defaultValue = "1") Integer page){
        Block block = blockService.getByBlockhash(blockhash);
        Integer blockId = block.getBlockId();
        Page<Transaction> pageTx = transactionService.getByBlockIdWithPage(blockId, page);
        PageDTO<JSONObject> pageDTO = getPageDTOByPageTx(pageTx);
        return pageDTO;

    }

    @GetMapping("/getByAddressWithPage")
    public PageDTO<JSONObject> getByAddressWithPage(@RequestParam String address,
                                                    @RequestParam(required = false, defaultValue = "1") Integer page){
        Page<Transaction> pageTx = transactionService.getTransactionByAddressWithPage(address, page);
        PageDTO<JSONObject> pageDTO = getPageDTOByPageTx(pageTx);
        return pageDTO;
    }

    private PageDTO<JSONObject> getPageDTOByPageTx(Page<Transaction> pageTx){
        List<JSONObject> txJsons = pageTx.stream().map(tx -> {
            JSONObject txJson = new JSONObject();
            txJson.put("txid", tx.getTxid());
            txJson.put("txhash", tx.getTxhash());
            txJson.put("time", tx.getTime());
            txJson.put("fees", tx.getFees());
            txJson.put("totalOutput", tx.getTotalOutput());

            List<TransactionDetail> txDetails = transactionDetailService.getByTransactionId(tx.getTransactionId());
            List<JSONObject> txDetailJsons = txDetails.stream().map(txDetail -> {
                JSONObject txDetailJson = new JSONObject();
                txDetailJson.put("address", txDetail.getAddress());
                txDetailJson.put("type", txDetail.getType());
                txDetailJson.put("amount", Math.abs(txDetail.getAmount()));
                return txDetailJson;
            }).collect(Collectors.toList());
            txJson.put("txDetails", txDetailJsons);
            return txJson;
        }).collect(Collectors.toList());


        PageDTO<JSONObject> pageDTO = new PageDTO<>();
        pageDTO.setTotal(pageTx.getTotal());
        pageDTO.setPageSize(PageConfig.PAGE_SIZE);
        pageDTO.setCurrentPage(pageTx.getPageNum());
        pageDTO.setList(txJsons);

        return pageDTO;
    }

}
