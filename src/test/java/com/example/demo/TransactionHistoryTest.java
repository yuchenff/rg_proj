package com.example.demo;

import com.example.demo.entity.TransactionHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryTest {

    private TransactionHistory transactionHistory;

    @BeforeEach
    void setUp() {
        // 每个测试前初始化一个TransactionHistory对象
        transactionHistory = new TransactionHistory();
    }

    @Test
    void testTransactionHistoryNoArgsConstructor() {
        // 测试无参构造函数
        assertNull(transactionHistory.getId());
        assertNull(transactionHistory.getUsername());
        assertNull(transactionHistory.getTransactionType());
        assertNull(transactionHistory.getAmount());
        assertNull(transactionHistory.getTransactionTime());
        assertNull(transactionHistory.getDescription());
        assertNull(transactionHistory.getTransactionStatus());
        assertNull(transactionHistory.getBalanceAfterTransaction());
    }

    @Test
    void testTransactionHistoryWithArgsConstructor() {
        // 测试带参构造函数
        transactionHistory = new TransactionHistory(1, "user1", "deposit", new BigDecimal("100.00"),
                LocalDateTime.now(), "Deposit", "success", new BigDecimal("500.00"));
        assertEquals(1, transactionHistory.getId());
        assertEquals("user1", transactionHistory.getUsername());
        assertEquals("deposit", transactionHistory.getTransactionType());
        assertEquals(new BigDecimal("100.00"), transactionHistory.getAmount());
        assertNotNull(transactionHistory.getTransactionTime());
        assertEquals("Deposit", transactionHistory.getDescription());
        assertEquals("success", transactionHistory.getTransactionStatus());
        assertEquals(new BigDecimal("500.00"), transactionHistory.getBalanceAfterTransaction());
    }

    @Test
    void testSetAndGetAmount() {
        // 测试setAmount和getAmount方法
        transactionHistory.setAmount(new BigDecimal("200.50"));
        assertEquals(new BigDecimal("200.50"), transactionHistory.getAmount());
    }

    @Test
    void testSetAndGetBalanceAfterTransaction() {
        // 测试setBalanceAfterTransaction和getBalanceAfterTransaction方法
        transactionHistory.setBalanceAfterTransaction(new BigDecimal("-100.00"));
        assertEquals(new BigDecimal("-100.00"), transactionHistory.getBalanceAfterTransaction());
    }

    @Test
    void testSetAndGetTransactionStatus() {
        // 测试setTransactionStatus和getTransactionStatus方法
        transactionHistory.setTransactionStatus("failed");
        assertEquals("failed", transactionHistory.getTransactionStatus());
    }

    @Test
    void testSetAndGetTransactionTime() {
        // 测试setTransactionTime和getTransactionTime方法
        LocalDateTime time = LocalDateTime.of(2023, 1, 1, 10, 30, 0, 0);
        transactionHistory.setTransactionTime(time);
        assertEquals(time, transactionHistory.getTransactionTime());
    }
}
