package io.github.konieshadow.conf.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.konieshadow.conf.common.domain.ConfigBean;
import io.github.konieshadow.conf.common.domain.PageResultBean;
import io.github.konieshadow.conf.common.domain.query.CreateConfigQuery;
import io.github.konieshadow.conf.common.domain.query.PageQuery;
import io.github.konieshadow.conf.common.domain.query.SelectConfigQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateConfigQuery;
import io.github.konieshadow.conf.common.util.ConverterUtil;
import io.github.konieshadow.conf.console.entity.ConfigInfo;
import io.github.konieshadow.conf.console.mapper.ConfigInfoMapper;
import io.github.konieshadow.conf.console.service.ConfigService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigInfoMapper configInfoMapper;

    @Override
    public PageResultBean<ConfigBean> selectConfigs(SelectConfigQuery query) {
        QueryWrapper<ConfigInfo> wrapper = new QueryWrapper<>();
        wrapper.select("id", "data_id", "group_id", "namespace", "content", "description");

        if (!StringUtils.isBlank(query.getNamespace())) {
            wrapper.like("namespace", query.getNamespace());
        }
        if (!StringUtils.isBlank(query.getDataId())) {
            wrapper.like("data_id", "%" + query.getDataId() + "%");
        }
        if (!StringUtils.isBlank(query.getGroupId())) {
            wrapper.like("group_id", "%" + query.getGroupId() + "%");
        }

        IPage<ConfigInfo> list = configInfoMapper.selectPage(new Page<>(query.getCurrent(), query.getSize()), wrapper);
        return ConverterUtil.toPageResult(list, ConfigServiceImpl::configBeanConverter);
    }

    @Override
    public ConfigBean selectConfigById(Long id) {
        if (id == null) {
            return null;
        }

        QueryWrapper<ConfigInfo> wrapper = new QueryWrapper<>();
        wrapper.select("id", "data_id", "group_id", "namespace", "content", "description");
        wrapper.eq("id", id);
        ConfigInfo record = configInfoMapper.selectOne(wrapper);
        return configBeanConverter(record);
    }

    @Override
    @Transactional
    public Long createConfig(CreateConfigQuery query) {
        ConfigInfo record = new ConfigInfo();
        record.setDataId(query.getDataId());
        record.setGroupId(query.getGroupId());
        record.setNamespace(query.getNamespace());
        record.setDescription(query.getDescription());
        configInfoMapper.insert(record);
        return record.getId();
    }

    @Override
    @Transactional
    public Long updateConfig(UpdateConfigQuery query) {
        ConfigInfo record = new ConfigInfo();
        record.setId(query.getId());
        record.setDataId(query.getDataId());
        record.setGroupId(query.getGroupId());
        record.setNamespace(query.getNamespace());
        record.setDescription(query.getDescription());
        configInfoMapper.updateById(record);
        return record.getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        configInfoMapper.deleteById(id);
    }

    private static ConfigBean configBeanConverter(ConfigInfo source) {
        if (source == null) {
            return null;
        }
        ConfigBean result = new ConfigBean();
        result.setId(source.getId());
        result.setDataId(source.getDataId());
        result.setGroupId(source.getGroupId());
        result.setNamespace(source.getNamespace());
        result.setContent(source.getContent());
        result.setDescription(source.getDescription());
        return result;
    }

}
