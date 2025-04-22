package com.sean.springbootinit.service;

import javax.annotation.Resource;

import com.sean.springbootinit.model.dto.user.UserRegisterRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 用户服务测试
 *
 */
@SpringBootTest
public class UserServiceTest {

    @Resource
    private UserService userService;

    @Test
    void userRegister() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        String userAccount = "sean";
        String userPassword = "";
        String checkPassword = "123456";
        userRegisterRequest.setUserAccount(userAccount);
        userRegisterRequest.setUserPassword(userPassword);
        userRegisterRequest.setCheckPassword(checkPassword);
        try {
            long result = userService.userRegister(userRegisterRequest);
            Assertions.assertEquals(-1, result);
            userAccount = "yu";
            result = userService.userRegister(userRegisterRequest);
            Assertions.assertEquals(-1, result);
        } catch (Exception e) {

        }
    }
}
