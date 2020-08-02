package io.github.konieshadow.conf.console.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.konieshadow.conf.common.util.Constants;
import io.github.konieshadow.conf.console.entity.User;
import io.github.konieshadow.conf.console.mapper.UserMapper;
import io.github.konieshadow.conf.security.model.ConfUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("id", "username", "password");
        User user = userMapper.selectOne(wrapper);

        if (user == null) {
            return null;
        }

        ConfUserDetail userDetail = new ConfUserDetail(user.getUsername(), user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(Constants.ROLE_ADMIN)));
        userDetail.setId(user.getId());
        return userDetail;
    }
}
