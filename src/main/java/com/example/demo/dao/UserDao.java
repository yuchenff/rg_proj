package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    public int addUser(User user) {
        Long id = user.getId();
        String username = user.getUsername();
        String password = user.getPassword();
        Double balance = user.getBalance();
        return jdbcTemplate.update("insert into user(id,username,password,balance) values(?,?,?,?)",id,username,password,balance);
    }

    public User findUserByUsername(String username) {
        String sql = "select * from `user` where username=?";
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
    }

    public int updateUser(Double depositAmount,String username) {
        String sql = "UPDATE user SET balance = ? WHERE username = ?";
        return jdbcTemplate.update(sql,depositAmount,username);
    }

    public List<User> getUsers() {
        return jdbcTemplate.query("select * from user",new BeanPropertyRowMapper<>(User.class));
    }

    public int save(User user) {
        String sql = "UPDATE user SET balance = ? WHERE username = ?";
        return jdbcTemplate.update(sql,user.getBalance(),user.getUsername());
    }
}
