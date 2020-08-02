package io.github.konieshadow.conf.common.domain.query;

import javax.validation.constraints.NotBlank;

public class CreateNamespaceQuery {

    @NotBlank
    private String name;

    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CreateNamespaceQuery{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
