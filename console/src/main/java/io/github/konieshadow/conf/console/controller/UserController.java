package io.github.konieshadow.conf.console.controller;

import io.github.konieshadow.conf.common.domain.OptResultBean;
import io.github.konieshadow.conf.common.domain.UserInfoBean;
import io.github.konieshadow.conf.common.domain.query.ModifyPasswordQuery;
import io.github.konieshadow.conf.console.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("current")
    public UserInfoBean currentUserInfo() {
        return userService.selectCurrentUserInfo();
    }

    @PutMapping("current/password")
    public OptResultBean<Void> modifyPassword(ModifyPasswordQuery query) {
        userService.modifyPassword(query);
        return new OptResultBean<>();
    }

}
