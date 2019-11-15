package io.jupeng.bitcoin.service;

import io.jupeng.bitcoin.po.Transaction;

import java.util.List;

public interface TranSactionService {
    void Transaction(String txid,Integer blockId,Integer confirmations,Long time);
    List<Transaction> getByBlockId(Integer blockId);

}
