package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.example.demo.entity.User;
class UserTest {

    private User user;

    @BeforeEach
    void setUp() {
        // 每个测试前初始化一个User对象
        user = new User();
    }

    @Test
    void testUserNoArgsConstructor() {
        // 测试无参构造函数
        assertNull(user.getId());
        assertNull(user.getUsername());
        assertNull(user.getPassword());
        assertNull(user.getBalance());
    }

    @Test
    void testUserWithArgsConstructor() {
        // 测试带参构造函数
        user = new User(1L, "testUser", "testPass", 100.0);
        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("testPass", user.getPassword());
        assertEquals(100.0, user.getBalance());
    }

    @Test
    void testGetBalance() {
        // 测试getBalance方法
        user.setBalance(50.0);
        assertEquals(50.0, user.getBalance());
    }

    @Test
    void testSetBalanceNegative() {
        // 测试setBalance方法，设置负数
        user.setBalance(-10.0);
        assertEquals(-10.0, user.getBalance());
    }

    @Test
    void testSetBalanceZero() {
        // 测试setBalance方法，设置零
        user.setBalance(0.0);
        assertEquals(0.0, user.getBalance());
    }

    @Test
    void testSetBalanceLarge() {
        // 测试setBalance方法，设置大数值
        user.setBalance(999999.99);
        assertEquals(999999.99, user.getBalance());
    }
}
