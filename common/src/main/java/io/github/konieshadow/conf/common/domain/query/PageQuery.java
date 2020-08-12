package io.github.konieshadow.conf.common.domain.query;

public class PageQuery {

    private int current;

    private int size;

    public PageQuery() {
    }

    public PageQuery(int current, int size) {
        this.current = current;
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}
