package com.example.demo;

import com.example.demo.dao.TransactionHistoryDao;
import com.example.demo.entity.TransactionHistory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TransactionHistoryDaoTest {

    @InjectMocks
    private TransactionHistoryDao transactionHistoryDao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private TransactionHistory transactionHistory;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionHistory = new TransactionHistory(1, "user1", "deposit", new BigDecimal("100.00"),
                LocalDateTime.now(), "Deposit", "success", new BigDecimal("500.00"));
    }

    @Test
    void testAddTransactionHistory() {
        // 模拟插入成功，返回影响的行数
        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any()))
                .thenReturn(1);

        int rowsAffected = transactionHistoryDao.addTransactionHistory(transactionHistory);
        assertEquals(1, rowsAffected);
        verify(jdbcTemplate, times(1)).update(anyString(), any(), any(), any(), any(), any(), any(), any());
    }

    @Test
    void testFindTransactionHistoryByUsername() {
        // 模拟返回一个交易记录列表
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class), eq("user1")))
                .thenReturn(List.of(transactionHistory));

        List<TransactionHistory> transactionHistories = transactionHistoryDao.findTransactionHistoryByUsername("user1");
        assertNotNull(transactionHistories);
        assertEquals(1, transactionHistories.size());
        assertEquals("user1", transactionHistories.get(0).getUsername());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class), eq("user1"));
    }

    @Test
    void testGetAllTransactionHistory() {
        // 模拟返回一个交易记录列表
        when(jdbcTemplate.query(anyString(), any(BeanPropertyRowMapper.class)))
                .thenReturn(List.of(transactionHistory));

        List<TransactionHistory> transactionHistories = transactionHistoryDao.getAllTransactionHistory();
        assertNotNull(transactionHistories);
        assertEquals(1, transactionHistories.size());
        verify(jdbcTemplate, times(1)).query(anyString(), any(BeanPropertyRowMapper.class));
    }

    @Test
    void testFindLatestTransactionByUsername() {
        // 模拟返回最新的交易记录
        when(jdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("user1")))
                .thenReturn(transactionHistory);

        TransactionHistory latestTransaction = transactionHistoryDao.findLatestTransactionByUsername("user1");
        assertNotNull(latestTransaction);
        assertEquals("user1", latestTransaction.getUsername());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq("user1"));
    }

    @Test
    void testFindTransactionById() {
        // 模拟根据ID查询交易记录
        when(jdbcTemplate.queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq(1)))
                .thenReturn(transactionHistory);

        TransactionHistory foundTransaction = transactionHistoryDao.findTransactionById(1);
        assertNotNull(foundTransaction);
        assertEquals(1, foundTransaction.getId());
        verify(jdbcTemplate, times(1)).queryForObject(anyString(), any(BeanPropertyRowMapper.class), eq(1));
    }
}
