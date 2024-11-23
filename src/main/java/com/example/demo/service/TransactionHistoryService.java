package com.example.demo.service;

import com.example.demo.dao.TransactionHistoryDao;
import com.example.demo.entity.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionHistoryService {

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    // 添加一条交易记录
    public boolean addTransactionHistory(String username, String transactionType, BigDecimal amount, String description, String f,BigDecimal balanceAfterTransaction) {
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setUsername(username);
        transactionHistory.setTransactionType(transactionType);
        transactionHistory.setAmount(amount);
        transactionHistory.setTransactionTime(LocalDateTime.now());
        transactionHistory.setDescription(description);
        transactionHistory.setTransactionStatus(f); // 默认交易成功
        transactionHistory.setBalanceAfterTransaction(balanceAfterTransaction);

        int result = transactionHistoryDao.addTransactionHistory(transactionHistory);
        return result > 0; // 返回操作是否成功
    }

    // 根据用户名查询所有交易记录
    public List<TransactionHistory> getTransactionHistoryByUsername(String username) {
        return transactionHistoryDao.findTransactionHistoryByUsername(username);
    }

    // 获取所有交易记录
    public List<TransactionHistory> getAllTransactionHistory() {
        return transactionHistoryDao.getAllTransactionHistory();
    }

    // 查询用户的最新交易记录
    public TransactionHistory getLatestTransactionByUsername(String username) {
        return transactionHistoryDao.findLatestTransactionByUsername(username);
    }

    // 查询交易记录详情
    public TransactionHistory getTransactionById(Integer id) {
        return transactionHistoryDao.findTransactionById(id);
    }
}
