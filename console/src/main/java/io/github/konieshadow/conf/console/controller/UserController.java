package io.github.konieshadow.conf.console.controller;

import io.github.konieshadow.conf.common.domain.UserInfoBean;
import io.github.konieshadow.conf.console.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("current")
    public UserInfoBean currentUserInfo() {
        return userService.getCurrentUserInfo();
    }

}
