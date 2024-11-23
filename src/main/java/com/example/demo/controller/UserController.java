package com.example.demo.controller;

import com.example.demo.service.TransactionHistoryService;
import com.example.demo.service.UserService;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionHistoryService transactionHistoryService;
//    @GetMapping("/user0ps")
//    public void user0ps(){
//        User user = new User(2L,"l1zy","8288");
//        int f = userService.addUser(user);
//        System.out.println(f);
//
//    }
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginRequest) {
        Map<String, Object> response = new HashMap<>();
//        debug
//        System.out.println("username: " + loginRequest.getUsername() + " password: " + loginRequest.getPassword());
        User user = userService.getUserByUsername(loginRequest.getUsername());
        if(user.getUsername().equals(loginRequest.getUsername()) && user.getPassword().equals(loginRequest.getPassword())) {
//            String token = JwtUtil.generateToken(user.getUsername());
            response.put("success", true);
            response.put("balance", user.getBalance());
        }else {
            response.put("success", false);
        }
        return response;
    }


    @PostMapping("/deposit")
    public Map<String, Object> getBalance(@RequestBody User balanceRequest) {
        String f;
        String username = balanceRequest.getUsername();
        Double balance = balanceRequest.getBalance();
        Map<String, Object> response = new HashMap<>();

        //debug

        try {
            userService.updateUser(balance, username);
        } catch (Exception e) {
            System.out.println("failed");
            response.put("success", false);
            return response;
        }

        User user = userService.getUserByUsername(balanceRequest.getUsername());
        if(user != null) {
            response.put("success", true);
            response.put("newBalance", user.getBalance());
            f = "success";
        }else {
            response.put("success", false);
            f = "failed";
        }

//        assert user != null;
        transactionHistoryService.addTransactionHistory(username,"deposit", BigDecimal.valueOf(balance), LocalDateTime.now().toString(),f,BigDecimal.valueOf(user.getBalance()));
        return response;
    }


//    @PostMapping("/withdraw")
//    public Map<String, Object> withdraw(@RequestBody User withdrawalRequest) {
//        String username = withdrawalRequest.getUsername();
//        Double withdrawalAmount = withdrawalRequest.getBalance(); // 假设这里的balance字段表示取款金额
//        Map<String, Object> response = new HashMap<>();
//
//        // debug
//        try {
//            User user = userService.getUserByUsername(username);
//            if (user == null) {
//                response.put("success", false);
//                response.put("message", "用户不存在");
//                return response;
//            }
//            if (user.getBalance() < withdrawalAmount) {
//                response.put("success", false);
//                response.put("message", "余额不足");
//                return response;
//            }
//            double newBalance = user.getBalance() - withdrawalAmount;
//            userService.updateUser(newBalance, username);
//
//            response.put("success", true);
//            response.put("newBalance", newBalance);
//
//        } catch (Exception e) {
//            System.out.println("取款失败");
//            response.put("success", false);
//            response.put("message", "服务器错误");
//        }
//
//        return response;
//    }



}
