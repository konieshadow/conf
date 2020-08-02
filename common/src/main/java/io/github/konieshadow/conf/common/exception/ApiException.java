package io.github.konieshadow.conf.common.exception;

import io.github.konieshadow.conf.common.domain.ErrorResultBean;

public class ApiException extends RuntimeException {

    private ErrorResultBean error;

    public ApiException() {
    }

    public ApiException(ErrorResultBean error) {
        super("ApiException: " + error.getMessage());
        this.error = error;
    }

    public ErrorResultBean getError() {
        return error;
    }

    public void setError(ErrorResultBean error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "ApiException{" +
                "error=" + error +
                '}';
    }
}
