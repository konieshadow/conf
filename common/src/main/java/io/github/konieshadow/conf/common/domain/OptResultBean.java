package io.github.konieshadow.conf.common.domain;

import io.github.konieshadow.conf.common.util.Constants;

import java.util.Collection;

public class OptResultBean<R> {

    private String message = Constants.OPT_SUCCESSFUL;

    private Collection<R> results;

    public OptResultBean() {
    }

    public OptResultBean(String message) {
        this.message = message;
    }

    public OptResultBean(String message, Collection<R> results) {
        this.message = message;
        this.results = results;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<R> getResults() {
        return results;
    }

    public void setResults(Collection<R> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "OptResultBean{" +
                "message='" + message + '\'' +
                ", results=" + results +
                '}';
    }
}
