package io.jupeng.bitcoin.service;

public interface TranSactionService {
    void Transaction(String txid,Integer blockId,Integer confirmations,Long time);

}
