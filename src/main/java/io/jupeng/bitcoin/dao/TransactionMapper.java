package io.jupeng.bitcoin.dao;


import feign.Param;
import io.jupeng.bitcoin.po.Transaction;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(Integer transactionId);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    Transaction selectByPrimaryKey(Integer transactionId);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);
    List<Transaction> selectByBlockId(@Param("blockId") Integer blockId);
}