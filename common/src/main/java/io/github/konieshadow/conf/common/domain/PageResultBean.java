package io.github.konieshadow.conf.common.domain;

import java.util.List;

public class PageResultBean<T> {

    private long total;

    private List<T> records;

    public PageResultBean() {
    }

    public PageResultBean(long total, List<T> records) {
        this.total = total;
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "PageResultBean{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
