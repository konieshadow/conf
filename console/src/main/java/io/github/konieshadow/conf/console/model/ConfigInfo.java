package io.github.konieshadow.conf.console.model;

public class ConfigInfo {

    private Long id;

    private String dataId;

    private String groupId;

    private String namespace;

    private String content;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        return "ConfigInfo{" +
                "id=" + id +
                ", dataId='" + dataId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", namespace='" + namespace + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
