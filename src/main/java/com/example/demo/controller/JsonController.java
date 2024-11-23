//package com.example.demo.controller;
//
//import com.example.demo.entity.User;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/json")
//public class JsonController {
//
//    @PostMapping("/book")
//    @CrossOrigin(value = "http://localhost:8082",maxAge = 1800,allowedHeaders = "*")
//    public String addBook(String name){
//        return "receive:"+name;
//    }
////    @DeleteMapping("/{id}")
////    @CrossOrigin(value = "http://localhost:8082",maxAge = 1800,allowedHeaders = "*")
//
//
//
//
////    @RequestMapping("/user")
////    public User getUser() {
////        return new User(1L, "倪升武", "123456");
////    }
//
//    @PostMapping("/user")
//    @CrossOrigin(value = "http://localhost:8082",maxAge = 1800,allowedHeaders = "*")
//
//    public String addUser(@RequestBody User user){
//        return "receive:" + user.getPassword()+"  "+user.getUsername();
//    }
//
//
//    @RequestMapping("/list")
//    public List<User> getUserList() {
//        List<User> userList = new ArrayList<>();
//        User user1 = new User(1L, "倪升武", "123456");
//        User user2 = new User(2L, "达人课", "123456");
//        userList.add(user1);
//        userList.add(user2);
//        return userList;
//    }
//
//    @RequestMapping("/map")
//    public Map<String, Object> getMap() {
//        Map<String, Object> map = new HashMap<>(3);
//        User user = new User(1L, "倪升武", "123456");
//        map.put("作者信息", user);
//        map.put("博客地址", "http://blog.itcodai.com");
//        map.put("CSDN地址", "http://blog.csdn.net/eson_15");
//        map.put("粉丝数量", 4153);
//        return map;
//    }
//}
