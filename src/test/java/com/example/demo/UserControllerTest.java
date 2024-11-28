package com.example.demo;

import com.example.demo.controller.UserController;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)  // 指定测试的Controller类
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;  // 用于模拟 HTTP 请求

    @Mock
    private UserService userService;  // Mock UserService

    @InjectMocks
    private UserController userController;  // 注入控制器

    private ObjectMapper objectMapper;  // 用于序列化请求体

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();  // 初始化 ObjectMapper
    }

    @Test
    void testLoginSuccess() throws Exception {
        // 创建模拟的 User 对象
        User mockUser = new User();
        mockUser.setUsername("lzy");
        mockUser.setPassword("888");

        // 模拟 userService 返回的结果
        when(userService.getUserByUsername("lzy")).thenReturn(mockUser);

        // 创建请求体
        User loginRequest = userService.getUserByUsername("lzy");

        // 执行 POST 请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())  // 状态码为 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true));  // 检查 success 为 true
    }

    @Test
    void testLoginFailure() throws Exception {
        // 创建模拟的 User 对象
        User mockUser = new User();
        mockUser.setUsername("testuser");
        mockUser.setPassword("password123");
        mockUser.setBalance(100.0);

        // 模拟 userService 返回的结果
        when(userService.getUserByUsername("testuser")).thenReturn(mockUser);

        // 创建请求体，密码错误
        User loginRequest = new User();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("wrongpassword");

        // 执行 POST 请求并验证结果
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())  // 状态码为 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))  // 检查 success 为 false
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("用户名或密码错误"));  // 检查错误消息
    }


}
