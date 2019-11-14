package io.jupeng.bitcoin.service.impl;
import com.alibaba.fastjson.JSONObject;

import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.dao.BlockMapper;
import io.jupeng.bitcoin.dao.TransactionMapper;
import io.jupeng.bitcoin.po.Block;
import io.jupeng.bitcoin.po.Transaction;
import io.jupeng.bitcoin.service.BlockService;
import io.jupeng.bitcoin.service.TranSactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private BitcoinRest bitcoinRest;
    @Autowired
    private TransactionServiceImpl transactionService;

    @Override
    public String syncBlock(String blockhash) {
        JSONObject blockJson = bitcoinRest.getBlockNoTxDetails(blockhash);
        Block block = new Block();
        block.setBlockhash(blockJson.getString("hash"));
        block.setConfirmations(blockJson.getInteger("confirmations"));
        block.setHeight(blockJson.getInteger("height"));
        block.setTime(blockJson.getLong("time"));
        block.setDifficulty(blockJson.getDouble("difficulty"));
        block.setSizeondisk(blockJson.getInteger("size"));
        block.setMerkleRoot(blockJson.getString("merkleroot"));
        block.setTxsize(blockJson.getInteger("nTx"));
        block.setVersion(blockJson.getString("versionHex"));
        block.setNonce(blockJson.getInteger("nonce").toString());
        block.setWeight(blockJson.getInteger("weight"));
        //todo get block reward
        //todo change bits to string
        //todo calculate tx volume, fee

        blockMapper.insert(block);

        Integer blockId = block.getBlockId();
        Integer confirmations = block.getConfirmations();
        Long time = block.getTime();


        ArrayList<String> txids= (ArrayList<String>) blockJson.get("tx");
        for(String txid:txids){

        }

        String nextblockhash = blockJson.getString("nextblockhash");
        return nextblockhash;
    }

    @Override
    public void syncBlocks(String fromBlockhash) {
        String tempBlockhash = fromBlockhash;
        while (tempBlockhash != null){
            tempBlockhash = syncBlock(tempBlockhash);
        }
    }
}
