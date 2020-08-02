package io.github.konieshadow.conf.console.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.github.konieshadow.conf.common.domain.NamespaceBean;
import io.github.konieshadow.conf.common.domain.query.CreateNamespaceQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateNamespaceQuery;
import io.github.konieshadow.conf.common.util.ConverterUtil;
import io.github.konieshadow.conf.console.entity.Namespace;
import io.github.konieshadow.conf.console.mapper.NamespaceMapper;
import io.github.konieshadow.conf.console.service.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NamespaceServiceImpl implements NamespaceService {

    @Autowired
    private NamespaceMapper namespaceMapper;

    @Override
    public List<NamespaceBean> selectNamespace() {
        QueryWrapper<Namespace> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "key", "description");
        List<Namespace> records = namespaceMapper.selectList(wrapper);
        return ConverterUtil.toList(records, NamespaceServiceImpl::namespaceBeanConverter);
    }

    @Override
    public NamespaceBean selectById(Long id) {
        QueryWrapper<Namespace> wrapper = new QueryWrapper<>();
        wrapper.select("id", "name", "key", "description");
        wrapper.eq("id", id);
        Namespace record = namespaceMapper.selectOne(wrapper);
        return namespaceBeanConverter(record);
    }

    @Override
    public Long createNamespace(CreateNamespaceQuery query) {
        Namespace record = new Namespace();
        record.setName(query.getName());
        record.setKey(UUID.randomUUID().toString());
        record.setDescription(query.getDescription());
        namespaceMapper.insert(record);
        return record.getId();
    }

    @Override
    public Long updateNamespace(UpdateNamespaceQuery query) {
        Namespace record = new Namespace();
        record.setId(query.getId());
        record.setName(query.getName());
        record.setDescription(query.getDescription());
        namespaceMapper.updateById(record);
        return record.getId();
    }

    @Override
    public void deleteNamespace(Long id) {
        namespaceMapper.deleteById(id);
    }

    private static NamespaceBean namespaceBeanConverter(Namespace source) {
        if (source == null) {
            return null;
        }
        NamespaceBean result = new NamespaceBean();
        result.setId(source.getId());
        result.setName(source.getName());
        result.setKey(source.getKey());
        result.setDescription(source.getDescription());
        return result;
    }
}
