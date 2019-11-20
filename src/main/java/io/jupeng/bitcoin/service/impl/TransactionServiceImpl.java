package io.jupeng.bitcoin.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.constants.PageConfig;
import io.jupeng.bitcoin.dao.TransactionMapper;;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TransactionDetailServiceImpl transactionDetailService;

    @Override
    public void syncTransaction(String txid, Integer blockId, Long time) {
        JSONObject transactionJson = bitcoinRest.getTransaction(txid);
        Transaction transaction = new Transaction();
        //set amount
        transaction.setBlockId(blockId);
        transaction.setSizeondisk(transactionJson.getInteger("size"));
        transaction.setStatus((byte)0);
        transaction.setTime(time);
        transaction.setTxhash(transactionJson.getString("hash"));
        transaction.setTxid(transactionJson.getString("txid"));
        transaction.setWeight(transactionJson.getInteger("weight"));

        transactionMapper.insert(transaction);

        Integer transactionId = transaction.getTransactionId();

        List<JSONObject> vouts = transactionJson.getJSONArray("vout").toJavaList(JSONObject.class);
        for (JSONObject vout : vouts) {
            transactionDetailService.syncTxDetailVout(vout, transactionId);
        }

        List<JSONObject> vins = transactionJson.getJSONArray("vin").toJavaList(JSONObject.class);
        for (JSONObject vin : vins) {
            transactionDetailService.syncTxDetailVin(vin, transactionId);
        }

    }

    @Override
    public List<Transaction> getByBlockId(Integer blockId) {
        List<Transaction> transactions = transactionMapper.selectByBlockId(blockId);
        return transactions;
    }

    @Override
    public Page<Transaction> getByBlockIdWithPage(Integer blockId, Integer page) {
        PageHelper.startPage(page, PageConfig.PAGE_SIZE);
        Page<Transaction> transactions = transactionMapper.selectByBlockIdWithPage(blockId);
        return transactions;
    }

    @Override
    public Transaction getByTxid(String txid) {
        Transaction transaction = transactionMapper.selectByTxid(txid);
        return transaction;
    }

    @Override
    public Page<Transaction> getTransactionByAddressWithPage(String address, Integer page) {
        PageHelper.startPage(page, PageConfig.PAGE_SIZE);
        Page<Transaction> transactions = transactionMapper.selectTransactionByAddress(address);
        return transactions;
    }
}
