package io.jupeng.bitcoin.service;

import com.github.pagehelper.Page;
import io.jupeng.bitcoin.po.Block;

import java.util.List;

public interface BlockService {

    String syncBlock(String blockhash);

    void syncBlocks(String fromBlockhash);

    List<Block> getRecent();

    Page<Block> getWithPage(Integer page);

    Block getByBlockhash(String blockhash);
}
