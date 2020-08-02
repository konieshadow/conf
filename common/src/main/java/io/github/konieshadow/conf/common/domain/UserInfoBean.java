package io.github.konieshadow.conf.common.domain;

public class UserInfoBean {

    private Long id;

    private String name;

    private String username;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfoBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
