package io.github.konieshadow.conf.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.konieshadow.conf.common.domain.ErrorResultBean;
import io.github.konieshadow.conf.common.domain.UserInfoBean;
import io.github.konieshadow.conf.common.domain.query.ModifyPasswordQuery;
import io.github.konieshadow.conf.common.exception.ApiException;
import io.github.konieshadow.conf.console.entity.User;
import io.github.konieshadow.conf.console.mapper.UserMapper;
import io.github.konieshadow.conf.console.service.UserService;
import io.github.konieshadow.conf.security.model.ConfUserDetail;
import io.github.konieshadow.conf.security.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoBean selectCurrentUserInfo() {
        ConfUserDetail userDetail = SecurityUtil.getCurrentUserDetail();
        if (userDetail == null) {
            return null;
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "username");
        wrapper.eq("id", userDetail.getId());
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

    @Override
    public void modifyPassword(ModifyPasswordQuery query) {
        ConfUserDetail userDetail = SecurityUtil.getCurrentUserDetail();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "password");
        wrapper.eq("id", userDetail.getId());
        User user = userMapper.selectOne(wrapper);

        if (passwordEncoder.matches(user.getPassword(), query.getOldPassword())) {
            throw new ApiException(new ErrorResultBean<>("Old password missmatch"));
        }

        String newPassword = passwordEncoder.encode(query.getNewPassword());
        User record = new User();
        record.setId(user.getId());
        record.setPassword(newPassword);
        userMapper.updateById(record);
    }
}
