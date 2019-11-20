package io.jupeng.bitcoin.dao;

import com.github.pagehelper.Page;
import io.jupeng.bitcoin.po.TransactionDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionDetailMapper {
    int deleteByPrimaryKey(Long txDetailId);

    int insert(TransactionDetail record);

    int insertSelective(TransactionDetail record);

    TransactionDetail selectByPrimaryKey(Long txDetailId);

    int updateByPrimaryKeySelective(TransactionDetail record);

    int updateByPrimaryKey(TransactionDetail record);

//    custom
    List<TransactionDetail> selectByTransactionId(@Param("transactionId") Integer transactionId);

    Integer selectTotalByAddress(@Param("address") String address);

    Double selectReceiveByAddress(@Param("address") String address);

    Double selectSendByAddress(@Param("address") String address);

}