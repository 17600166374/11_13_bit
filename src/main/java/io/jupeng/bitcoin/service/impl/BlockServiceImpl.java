package io.jupeng.bitcoin.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jupeng.bitcoin.client.BitcoinRest;
import io.jupeng.bitcoin.dao.BlockMapper;
import io.jupeng.bitcoin.po.Block;
import io.jupeng.bitcoin.service.BlockService;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BitcoinRest bitcoinRest;

    @Autowired
    private BlockMapper blockMapper;

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
        blockMapper.insert(block);
        Integer blockId = block.getBlockId();
        Long time = block.getTime();

        ArrayList<String> txids = (ArrayList<String>) blockJson.get("tx");
        for (String txid : txids) {
            transactionService.syncTransaction(txid, blockId, time);
        }

        String nextblockhash = blockJson.getString("nextblockhash");

        return nextblockhash;
    }

    @Override
    @Async
    public void syncBlocks(String fromBlockhash) {
        String tempBlockhash = fromBlockhash;
        while (tempBlockhash != null){
            tempBlockhash = syncBlock(tempBlockhash);
        }

    }

    @Override
    public List<Block> getRecent() {
        List<Block> blocks = blockMapper.selectRecent();
        return blocks;
    }

    @Override
    public Page<Block> getWithPage(Integer page) {
          PageHelper.startPage(page, 20);
          Page<Block> blocks = blockMapper.selectWithPage();
          return blocks;
    }

    @Override
    public Block getByBlockhash(String blockhash) {
        Block block = blockMapper.selectByBlockhash(blockhash);
        return block;
    }
}
