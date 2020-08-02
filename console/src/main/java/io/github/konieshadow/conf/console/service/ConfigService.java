package io.github.konieshadow.conf.console.service;

import io.github.konieshadow.conf.common.domain.ConfigBean;
import io.github.konieshadow.conf.common.domain.PageResultBean;
import io.github.konieshadow.conf.common.domain.query.CreateConfigQuery;
import io.github.konieshadow.conf.common.domain.query.PageQuery;
import io.github.konieshadow.conf.common.domain.query.SelectConfigQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateConfigQuery;

public interface ConfigService {

    PageResultBean<ConfigBean> selectConfig(PageQuery<SelectConfigQuery> query);

    ConfigBean selectConfigById(Long id);

    Long createConfig(CreateConfigQuery query);

    Long updateConfig(UpdateConfigQuery query);

    void delete(Long id);

}
