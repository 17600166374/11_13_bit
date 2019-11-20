package io.jupeng.bitcoin.service;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.po.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    void syncTxDetailVout(JSONObject vout, Integer transactionId);

    void syncTxDetailVin(JSONObject vin, Integer transactionId);

    List<TransactionDetail> getByTransactionId(Integer transactionId);

    Integer getTotalByAddress(String address);

    Double getReceiveByAddres(String address);

    Double getSendByAddress(String address);
}
