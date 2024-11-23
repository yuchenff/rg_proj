package com.example.demo.controller;

import com.example.demo.dao.TransactionHistoryDao;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.service.TransactionHistoryService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TransactionHistoryService transactionHistoryService;

    @Autowired
    private TransactionHistoryDao transactionHistoryDao;

    // 登录接口
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginRequest) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(loginRequest.getUsername());

        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            response.put("success", true);
            response.put("balance", user.getBalance());
        } else {
            response.put("success", false);
            response.put("message", "用户名或密码错误");
        }
        return response;
    }

    // 存款接口
    @PostMapping("/deposit")
    public Map<String, Object> deposit(@RequestBody User balanceRequest) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(balanceRequest.getUsername());

        if (user == null) {
            response.put("success", false);
            response.put("message", "用户不存在");
            return response;
        }

        double newBalance = user.getBalance() + balanceRequest.getBalance();
        try {
            userService.updateUser(newBalance, user.getUsername());
            response.put("success", true);
            response.put("newBalance", newBalance);

            // 添加交易记录
            transactionHistoryService.addTransactionHistory(user.getUsername(), "deposit", BigDecimal.valueOf(balanceRequest.getBalance()), LocalDateTime.now().toString(), "success", BigDecimal.valueOf(newBalance));
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "存款失败");
        }
        return response;
    }

    // 取款接口
    @PostMapping("/withdraw")
    public Map<String, Object> withdraw(@RequestBody User withdrawalRequest) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(withdrawalRequest.getUsername());

        if (user == null) {
            response.put("success", false);
            response.put("message", "用户不存在");
            return response;
        }

        if (user.getBalance() < withdrawalRequest.getBalance()) {
            response.put("success", false);
            response.put("message", "余额不足");
            return response;
        }

        double newBalance = user.getBalance() - withdrawalRequest.getBalance();
        try {
            userService.updateUser(newBalance, user.getUsername());
            response.put("success", true);
            response.put("newBalance", newBalance);

            // 添加交易记录
            transactionHistoryService.addTransactionHistory(user.getUsername(), "withdraw", BigDecimal.valueOf(withdrawalRequest.getBalance()), LocalDateTime.now().toString(), "success", BigDecimal.valueOf(newBalance));
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "取款失败");
        }
        return response;
    }

    // 获取用户余额
    @GetMapping("/getBalance")
    public Map<String, Object> getBalance(@RequestParam String username) {
        Map<String, Object> response = new HashMap<>();
        User user = userService.getUserByUsername(username);

        if (user != null) {
            response.put("success", true);
            response.put("newBalance", user.getBalance());
        } else {
            response.put("success", false);
            response.put("message", "用户不存在");
        }
        return response;
    }

    // 获取用户交易记录
    @GetMapping("/history/{username}")
    public List<TransactionHistory> getTransactionHistory(@PathVariable("username") String username) {
        return transactionHistoryDao.findTransactionHistoryByUsername(username);
    }

    // 获取所有用户交易记录（管理员权限）
    @GetMapping("/all")
    public List<TransactionHistory> getAllTransactionHistory() {
        return transactionHistoryDao.getAllTransactionHistory();
    }

    // 获取某个用户的最新交易记录
    @GetMapping("/latest/{username}")
    public TransactionHistory getLatestTransaction(@PathVariable("username") String username) {
        return transactionHistoryDao.findLatestTransactionByUsername(username);
    }
}
