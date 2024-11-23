package com.example.demo;

import com.example.demo.dao.TransactionHistoryDao;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.service.TransactionHistoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class Demo2ApplicationTests {

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    @Autowired
    private TransactionHistoryService transactionHistoryService;




}
