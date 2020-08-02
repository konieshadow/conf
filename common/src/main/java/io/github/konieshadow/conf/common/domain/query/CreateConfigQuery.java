package io.github.konieshadow.conf.common.domain.query;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateConfigQuery {

    @NotBlank
    private String dataId;

    @NotBlank
    private String groupId;

    @NotBlank
    private String namespace;

    @NotNull
    private String content;

    private String description;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateConfigQuery{" +
                "dataId='" + dataId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", namespace='" + namespace + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
