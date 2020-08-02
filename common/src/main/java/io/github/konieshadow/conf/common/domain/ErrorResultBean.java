package io.github.konieshadow.conf.common.domain;

import java.util.Collection;

public class ErrorResultBean<E> {

    private String message;

    private Collection<E> errors;

    public ErrorResultBean() {
    }

    public ErrorResultBean(String message) {
        this.message = message;
    }

    public ErrorResultBean(String message, Collection<E> errors) {
        this.message = message;
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<E> getErrors() {
        return errors;
    }

    public void setErrors(Collection<E> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "ErrorResultBean{" +
                "message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
