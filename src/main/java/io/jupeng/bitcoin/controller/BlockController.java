package io.jupeng.bitcoin.controller;

import com.alibaba.fastjson.JSONObject;
import io.jupeng.bitcoin.dto.PageDTO;
import io.jupeng.bitcoin.po.Block;
import io.jupeng.bitcoin.service.BlockService;
import io.jupeng.bitcoin.service.TransactionDetailService;
import io.jupeng.bitcoin.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {

    @Autowired
    private BlockService blockService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private TransactionDetailService transactionDetailService;

    @GetMapping("/getRecent")
    public List<JSONObject> getRecent(){
        List<Block> blocks = blockService.getRecent();
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject blockJson = new JSONObject();
            blockJson.put("height", block.getHeight());
            blockJson.put("blockhash", block.getBlockhash());
            blockJson.put("time", block.getTime());
            blockJson.put("miner", block.getMiner());
            blockJson.put("size", block.getSizeondisk());
            return blockJson;
        }).collect(Collectors.toList());
        return blockJsons;
    }

    @GetMapping("/getWithPage")
    public PageDTO<JSONObject> getWithPage(@RequestParam(required = false, defaultValue = "1") Integer page){
        Page<Block> blocks = blockService.getWithPage(page);
        List<JSONObject> blockJsons = blocks.stream().map(block -> {
            JSONObject blockJson = new JSONObject();
            blockJson.put("height", block.getHeight());
            blockJson.put("blockhash", block.getBlockhash());
            blockJson.put("time", block.getTime());
            blockJson.put("miner", block.getMiner());
            blockJson.put("size", block.getSizeondisk());
            return blockJson;
        }).collect(Collectors.toList());

        PageDTO<JSONObject> pageDTO = new PageDTO<>();
        pageDTO.setList(blockJsons);
        pageDTO.setTotal(blocks.getTotal());
        pageDTO.setPageSize(blocks.getPageSize());
        pageDTO.setCurrentPage(blocks.getPageNum());

        return pageDTO;
    }

    @GetMapping("/getInfoByHash")
    public JSONObject getInfoByHash(@RequestParam String blockhash){

        JSONObject blockInfoJson = new JSONObject();

        Block block = blockService.getByBlockhash(blockhash);
        blockInfoJson.put("blockhash",block.getBlockhash());
        blockInfoJson.put("confirmations",null);
        blockInfoJson.put("time",block.getTime());
        blockInfoJson.put("height",block.getHeight());
        blockInfoJson.put("miner",block.getMiner());
        blockInfoJson.put("txSize",block.getTxsize());
        blockInfoJson.put("difficulty",block.getDifficulty());
        blockInfoJson.put("merkleroot",block.getMerkleRoot());
        blockInfoJson.put("version",block.getVersion());
        blockInfoJson.put("bits",block.getBits());
        blockInfoJson.put("weight",block.getWeight());
        blockInfoJson.put("sizeOnDisk",block.getSizeondisk());
        //todo nonce negative
        blockInfoJson.put("nonce",block.getNonce());
        blockInfoJson.put("txVol",block.getTransactionVolume());
        blockInfoJson.put("blockReward",block.getBlockReward());
        blockInfoJson.put("feeReward",block.getFeeReward());

        return blockInfoJson;
    }

    @GetMapping("/getInfoByHeight")
    public JSONObject getInfoByHeight(@RequestParam Integer height){
        return null;
    }
}
