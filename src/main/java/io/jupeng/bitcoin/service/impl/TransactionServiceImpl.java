package io.jupeng.bitcoin.service.impl;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.dao.BlockMapper;
import io.jupeng.bitcoin.dao.TransactionMapper;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.service.TranSactionService;
import org.springframework.beans.factory.annotation.Autowired;

public class TransactionServiceImpl implements TranSactionService{
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;
    @Autowired
    private TransactionMapper transactionMapper;


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
    }
}
