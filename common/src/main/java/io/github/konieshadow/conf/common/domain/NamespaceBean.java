package io.github.konieshadow.conf.common.domain;

public class NamespaceBean {

    private Long id;

    private String name;

    private String key;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NamespaceBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
