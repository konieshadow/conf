package io.github.konieshadow.conf.console.service;

import io.github.konieshadow.conf.common.domain.UserInfoBean;
import io.github.konieshadow.conf.common.domain.query.ModifyPasswordQuery;

public interface UserService {

    UserInfoBean selectCurrentUserInfo();

    void modifyPassword(ModifyPasswordQuery query);

}
