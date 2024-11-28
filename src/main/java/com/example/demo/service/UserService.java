package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public int addUser(User user) {
        return userDao.addUser(user);
    }

    public User getUserByUsername(String username) {
        return userDao.findUserByUsername(username);
    }

    public void updateUser(Double depositAmount, String username) {
        userDao.updateUser(depositAmount, username);
    }

    public List<User> getUsers() {
        return userDao.getUsers();
    }

    public void save(User user) {
        userDao.save(user);
    }
}
