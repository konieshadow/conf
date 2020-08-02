package io.github.konieshadow.conf.console.service;

import io.github.konieshadow.conf.common.domain.NamespaceBean;
import io.github.konieshadow.conf.common.domain.query.CreateNamespaceQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateNamespaceQuery;

import java.util.List;

public interface NamespaceService {

    List<NamespaceBean> selectNamespace();

    NamespaceBean selectById(Long id);

    Long createNamespace(CreateNamespaceQuery query);

    Long updateNamespace(UpdateNamespaceQuery query);

    void deleteNamespace(Long id);

}
