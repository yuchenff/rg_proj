package com.example.demo.entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionHistory {

    private Integer id;                        // 交易记录唯一标识
    private String username;                   // 用户名
    private String transactionType;            // 交易类型（存款、取款、转账等）
    private BigDecimal amount;                 // 交易金额
    private LocalDateTime transactionTime;     // 交易时间
    private String description;                // 交易描述
    private String transactionStatus;          // 交易状态（成功、失败）
    private BigDecimal balanceAfterTransaction; // 交易后的账户余额

    // 构造方法
    public TransactionHistory() {}

    public TransactionHistory(Integer id, String username, String transactionType, BigDecimal amount,
                              LocalDateTime transactionTime, String description, String transactionStatus,
                              BigDecimal balanceAfterTransaction) {
        this.id = id;
        this.username = username;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.description = description;
        this.transactionStatus = transactionStatus;
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

    // Getter 和 Setter 方法
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public BigDecimal getBalanceAfterTransaction() {
        return balanceAfterTransaction;
    }

    public void setBalanceAfterTransaction(BigDecimal balanceAfterTransaction) {
        this.balanceAfterTransaction = balanceAfterTransaction;
    }

}
