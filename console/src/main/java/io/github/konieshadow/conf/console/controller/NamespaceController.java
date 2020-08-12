package io.github.konieshadow.conf.console.controller;

import io.github.konieshadow.conf.common.domain.NamespaceBean;
import io.github.konieshadow.conf.common.domain.OptResultBean;
import io.github.konieshadow.conf.common.domain.query.CreateNamespaceQuery;
import io.github.konieshadow.conf.common.domain.query.UpdateNamespaceQuery;
import io.github.konieshadow.conf.console.service.NamespaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/namespace")
public class NamespaceController {

    @Autowired
    private NamespaceService namespaceService;

    @GetMapping
    public List<NamespaceBean> selectNamespaces() {
        return namespaceService.selectNamespaces();
    }

    @GetMapping("all")
    public List<String> selectAllNamespaceNames() {
        return namespaceService.selectAllNamespaceNames();
    }

    @GetMapping("{id}")
    public NamespaceBean selectById(@PathVariable Long id) {
        return namespaceService.selectById(id);
    }

    @PostMapping
    public NamespaceBean createNamespace(@Valid CreateNamespaceQuery query) {
        Long id = namespaceService.createNamespace(query);
        return namespaceService.selectById(id);
    }

    @PutMapping
    public NamespaceBean updateNamespace(@Valid UpdateNamespaceQuery query) {
        Long id = namespaceService.updateNamespace(query);
        return namespaceService.selectById(id);
    }

    @DeleteMapping("{id}")
    public OptResultBean<Void> deleteNamespace(@PathVariable Long id) {
        namespaceService.deleteNamespace(id);
        return new OptResultBean<>();
    }

}
