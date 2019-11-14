# Block
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| block_id  | int(11)  | 主键 自增 | Id  |
| blockhash | char(64)  | 唯一索引 非空 | 区块hash  |
| height  | int(11)  | 唯一索引 非空 | 区块高度  |
| time  | bigint(20)  | 唯一索引 非空 | 出块时间  |
| miner  | varchar(50)  |   | 矿工  |
| sizeOnDisk  | int(11)  |   | 字节大小  |
| confirmations  | int(11)  |   | 确认数  |
| txSize  | int(11)  |   | 交易总数  |
| difficulty  | double  |   | 难度系数  |
| merkle_root  | char(64)  |   | 默尔克树  |
| bits  | int(11)  |   | 位  |
| version  | varchar(20)  |   | 版本  |
| weight  | int(11)  |   | 重量  |
| block_reward  | double   |   | 出块奖励  |
| fee_reward  | double  |   | 交易费  |
| transaction_volume  | double  |   | 交易金额总量  |
| nonce  | varchar(20)  |   | 随机值  |

# Transaction
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| transaction_id  | int  | 主键 自增 | Id  |
| txid | char(64)  | 唯一索引 非空 | 交易id  |
| txhash  | char(64)  | 唯一索引 非空 | 交易hash  |
| time  | bigint  | 索引 非空  | 交易时间（出块时间） |
| amount  | double  | 非空  | 金额  |
| fees  | double  |   | 费用  |
| confirmations  | int  |   | 确认数  |
| status  | tinyint  |   | 状态（确认0、未确认1）  |
| sizeOnDisk  | int  |   | 字节大小  |
| weight  | int  |   | 重量  |
| total_input  | double  |   | 总输入金额  |
| total_output  | double  |   | 总输出金额  |
| fee_per_byte  | double  | 默认值0.0  | 每字节费用  |
| fee_per_weight_unit  | double  | 默认值0.0  | 每单位重量费用  |
| block_id  | int(11)  | 外键 索引 非空 | 区块id  |

# TransactionDetail
| 字段  | 类型  | 约束  |  说明 |
|---|---|---|---|
| tx_detail_id  | bigint  | 主键 自增 | Id  |
| address  | varchar(50)  | 索引  | 地址  |
| type  | tinyint  |   | 类型（发送0、接受1）  |
| amount  | double  |   | 金额（正负，正接受，负发送）  |
| transaction_id  | int  | 外键 索引 非空 | 交易id  |