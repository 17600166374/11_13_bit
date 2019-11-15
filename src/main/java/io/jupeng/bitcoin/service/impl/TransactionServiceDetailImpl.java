package io.jupeng.bitcoin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.dao.BlockMapper;
import io.jupeng.bitcoin.dao.TransactionDetailMapper;
import io.jupeng.bitcoin.dao.TransactionMapper;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.po.TransactionDetail;
import io.jupeng.bitcoin.service.TranSactionService;
import io.jupeng.bitcoin.service.TranSactionServiceDetail;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TransactionServiceDetailImpl implements TranSactionServiceDetail{
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionDetailMapper transactionDetailMapper;
    @Autowired
    private TranSactionServiceDetail tranSactionServiceDetail;
    @Autowired
    private TransactionDetail transactionDetail;

    @Override
    public void TxDetailVout(JSONObject vout, Integer transactionId) {

        TransactionDetail transactionDetail = new TransactionDetail();
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses != null){
            String address = (String) addresses.get(0);
            transactionDetail.setAddress(address);
            transactionDetail.setAmount(vout.getDouble("value"));
            transactionDetail.setType((byte) TxDetailVout.Receive.ordinal());
            transactionDetail.setTransactionId(transactionId);

            transactionDetailMapper.insert(transactionDetail);
        }
    }
    }

    @Override
    public void TxDetailVin(JSONObject vin, Integer transactionId) {

    }
}
