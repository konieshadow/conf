package io.github.konieshadow.conf.console.controller;

import io.github.konieshadow.conf.common.domain.ConfigBean;
import io.github.konieshadow.conf.common.domain.OptResultBean;
import io.github.konieshadow.conf.common.domain.PageResultBean;
import io.github.konieshadow.conf.common.domain.query.PageQuery;
import io.github.konieshadow.conf.common.util.Constants;
import io.github.konieshadow.conf.common.domain.query.CreateConfigQuery;
import io.github.konieshadow.conf.common.domain.query.SelectConfigQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateConfigQuery;
import io.github.konieshadow.conf.console.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/config")
public class ConfigController {

    @Autowired
    private ConfigService configService;

    @GetMapping
    public PageResultBean<ConfigBean> getConfig(PageQuery<SelectConfigQuery> query) {
        return configService.selectConfig(query);
    }

    @GetMapping("{id}")
    public ConfigBean selectConfigById(@PathVariable Long id) {
        return configService.selectConfigById(id);
    }

    @PostMapping
    public ConfigBean createConfig(@Valid CreateConfigQuery query) {
        Long id = configService.createConfig(query);
        return configService.selectConfigById(id);
    }

    @PutMapping
    public ConfigBean createConfig(@Valid UpdateConfigQuery query) {
        Long id = configService.updateConfig(query);
        return configService.selectConfigById(id);
    }

    @DeleteMapping("{id}")
    public OptResultBean<Void> deleteConfig(@PathVariable Long id) {
        configService.delete(id);
        return new OptResultBean();
    }

}
