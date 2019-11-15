package io.jupeng.bitcoin.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.dao.BlockMapper;
import io.jupeng.bitcoin.dao.TransactionMapper;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.service.TranSactionService;
import io.jupeng.bitcoin.service.TranSactionServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceImpl implements TranSactionService{
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private TranSactionServiceDetail tranSactionServiceDetail;

    @Override
    public void Transaction(String txid,Integer blockId,Integer confirmations,Long time) {
        JSONObject transaction = bitcoinRest.getTransaction(txid);
        Transaction trasction =new Transaction();
        trasction.setBlockId(blockId);
        trasction.setConfirmations(confirmations);
        trasction.setSizeondisk(transaction.getInteger("size"));
        trasction.setStatus((byte)0);
        trasction.setTime(time);
        trasction.setTxhash(transaction.getString("hash"));
        trasction.setTxid(transaction.getString("txid"));
        trasction.setWeight(transaction.getInteger("weight"));
        transactionMapper.insert(trasction);
        Integer transactionId = trasction.getTransactionId();

        List<JSONObject> vouts = transaction.getJSONArray("vout").toJavaList(JSONObject.class);
        for (JSONObject vout : vouts) {
            tranSactionServiceDetail.TxDetailVout(vout, transactionId);
        }

        //todo insert tx detail vin
        List<JSONObject> vins = transaction.getJSONArray("vin").toJavaList(JSONObject.class);
        for (JSONObject vin : vins) {
            tranSactionServiceDetail.TxDetailVin(vin, transactionId);
        }
    }

    @Override
    public List<Transaction> getByBlockId(Integer blockId) {
        List<Transaction> transactions = transactionMapper.selectByBlockId(blockId);
        return transactions;
    }
}
