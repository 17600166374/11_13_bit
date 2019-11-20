package io.jupeng.bitcoin.po;

public class Transaction {
    private Integer transactionId;

    private String txid;

    private String txhash;

    private Long time;

    private Double amount;

    private Double fees;

    private Integer confirmations;

    private Byte status;

    private Integer sizeondisk;

    private Integer weight;

    private Double totalInput;

    private Double totalOutput;

    private Double feePerByte;

    private Double feePerWeightUnit;

    private Integer blockId;

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid == null ? null : txid.trim();
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash == null ? null : txhash.trim();
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getFees() {
        return fees;
    }

    public void setFees(Double fees) {
        this.fees = fees;
    }

    public Integer getConfirmations() {
        return confirmations;
    }

    public void setConfirmations(Integer confirmations) {
        this.confirmations = confirmations;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getSizeondisk() {
        return sizeondisk;
    }

    public void setSizeondisk(Integer sizeondisk) {
        this.sizeondisk = sizeondisk;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getTotalInput() {
        return totalInput;
    }

    public void setTotalInput(Double totalInput) {
        this.totalInput = totalInput;
    }

    public Double getTotalOutput() {
        return totalOutput;
    }

    public void setTotalOutput(Double totalOutput) {
        this.totalOutput = totalOutput;
    }

    public Double getFeePerByte() {
        return feePerByte;
    }

    public void setFeePerByte(Double feePerByte) {
        this.feePerByte = feePerByte;
    }

    public Double getFeePerWeightUnit() {
        return feePerWeightUnit;
    }

    public void setFeePerWeightUnit(Double feePerWeightUnit) {
        this.feePerWeightUnit = feePerWeightUnit;
    }

    public Integer getBlockId() {
        return blockId;
    }

    public void setBlockId(Integer blockId) {
        this.blockId = blockId;
    }
}