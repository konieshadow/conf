package io.github.konieshadow.conf.common.domain.query;

public class PageQuery<T> {

    private int current;

    private int size;

    private T data;

    public PageQuery() {
    }

    public PageQuery(int current, int size) {
        this.current = current;
        this.size = size;
    }

    public PageQuery(int current, int size, T data) {
        this.current = current;
        this.size = size;
        this.data = data;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PageQuery{" +
                "current=" + current +
                ", size=" + size +
                '}';
    }
}
