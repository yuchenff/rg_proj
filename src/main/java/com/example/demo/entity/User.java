package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

    @Id
    private Long id;
    private String username;
    private String password;
    private  Double balance;



    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }


    // 无参构造函数（JPA 需要）
    public User() {
    }

    // 带参构造函数
    public User(Long id, String username, String password , Double balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
