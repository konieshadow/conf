package io.github.konieshadow.conf.common.domain.query;

public class SelectConfigQuery {

    private String dataId;

    private String groupId;

    private String namespace;

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

    @Override
    public String toString() {
        return "ConfigQuery{" +
                "dataId='" + dataId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", namespace='" + namespace + '\'' +
                '}';
    }
}
