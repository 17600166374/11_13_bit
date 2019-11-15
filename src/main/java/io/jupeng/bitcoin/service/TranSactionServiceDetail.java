package io.jupeng.bitcoin.service;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.po.Transaction;

import java.util.List;

public interface TranSactionServiceDetail {

    void TxDetailVout(JSONObject vout, Integer transactionId);

    void TxDetailVin(JSONObject vin, Integer transactionId);
}
