package io.github.konieshadow.conf.common.domain;

public class FieldValidationErrorBean {

    private String resource;

    private String field;

    private String code;

    private String description;

    public FieldValidationErrorBean() {
    }

    public FieldValidationErrorBean(String resource, String field, String code) {
        this.resource = resource;
        this.field = field;
        this.code = code;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ErrorDetailBean{" +
                "resource='" + resource + '\'' +
                ", field='" + field + '\'' +
                ", code='" + code + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
