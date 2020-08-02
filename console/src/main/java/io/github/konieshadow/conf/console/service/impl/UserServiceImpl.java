package io.github.konieshadow.conf.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.konieshadow.conf.common.domain.UserInfoBean;
import io.github.konieshadow.conf.console.entity.User;
import io.github.konieshadow.conf.console.mapper.UserMapper;
import io.github.konieshadow.conf.console.service.UserService;
import io.github.konieshadow.conf.security.model.ConfUserDetail;
import io.github.konieshadow.conf.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfoBean getCurrentUserInfo() {
        ConfUserDetail userDetail = SecurityUtil.getCurrentUserDetail();
        if (userDetail == null) {
            return null;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "username");
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            return null;
        }

        UserInfoBean userInfo = new UserInfoBean();
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setUsername(user.getUsername());
        return userInfo;
    }
}
