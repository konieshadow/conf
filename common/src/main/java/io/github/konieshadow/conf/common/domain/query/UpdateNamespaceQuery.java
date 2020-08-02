package io.github.konieshadow.conf.common.domain.query;

import javax.validation.constraints.NotNull;

public class UpdateNamespaceQuery extends CreateNamespaceQuery {

    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UpdateNamespaceQuery{" +
                "id=" + id + '\'' +
                ", name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }
}
