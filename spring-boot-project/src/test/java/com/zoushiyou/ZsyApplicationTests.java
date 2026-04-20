package com.zoushiyou;

import com.zoushiyou.entity.User;
import com.zoushiyou.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ZsyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    void contextLoads() {
        assertNotNull(userService);
    }

    @Test
    void testGetUserById() {
        User user = userService.getById(1L);
        assertNotNull(user);
        assertEquals("admin", user.getCode());
        System.out.println("用户名: " + user.getName());
    }
}
