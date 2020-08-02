package io.github.konieshadow.conf.common.domain.query;

import javax.validation.constraints.NotNull;

public class UpdateConfigQuery extends CreateConfigQuery {

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
        return "UpdateConfigQuery{" +
                "id='" + id + '\'' +
                ", dataId='" + getDataId() + '\'' +
                ", groupId='" + getGroupId() + '\'' +
                ", namespace='" + getNamespace() + '\'' +
                ", content='" + getContent() + '\'' +
                ", description='" + getDescription() + '\'' +
                '}';
    }

}
