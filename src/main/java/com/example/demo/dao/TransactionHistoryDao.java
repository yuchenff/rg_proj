package com.example.demo.dao;

import com.example.demo.entity.TransactionHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionHistoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 添加一条交易记录
    public int addTransactionHistory(TransactionHistory transactionHistory) {
        String sql = "INSERT INTO transaction_history(username, transaction_type, amount, transaction_time, description, transaction_status, balance_after_transaction) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                transactionHistory.getUsername(),
                transactionHistory.getTransactionType(),
                transactionHistory.getAmount(),
                transactionHistory.getTransactionTime(),
                transactionHistory.getDescription(),
                transactionHistory.getTransactionStatus(),
                transactionHistory.getBalanceAfterTransaction());
    }

    // 根据用户名查询该用户的所有交易记录
    public List<TransactionHistory> findTransactionHistoryByUsername(String username) {
        String sql = "SELECT * FROM transaction_history WHERE username = ? ORDER BY transaction_time DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TransactionHistory.class), username);
    }

    // 查询所有交易记录
    public List<TransactionHistory> getAllTransactionHistory() {
        String sql = "SELECT * FROM transaction_history ORDER BY transaction_time DESC";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(TransactionHistory.class));
    }

    // 查询某个用户的最近一笔交易记录
    public TransactionHistory findLatestTransactionByUsername(String username) {
        String sql = "SELECT * FROM transaction_history WHERE username = ? ORDER BY transaction_time DESC LIMIT 1";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TransactionHistory.class), username);
    }

    // 根据交易记录ID查询
    public TransactionHistory findTransactionById(Integer id) {
        String sql = "SELECT * FROM transaction_history WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(TransactionHistory.class), id);
    }

}

