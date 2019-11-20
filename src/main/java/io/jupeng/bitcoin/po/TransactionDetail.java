package io.jupeng.bitcoin.po;

public class TransactionDetail {
    private Long txDetailId;

    private String address;

    private Byte type;

    private Double amount;

    private Integer transactionId;

    public Long getTxDetailId() {
        return txDetailId;
    }

    public void setTxDetailId(Long txDetailId) {
        this.txDetailId = txDetailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
}