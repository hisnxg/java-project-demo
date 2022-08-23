package com.nxg.pojo;

public class Menu {
    private Integer id;

    private String menuName;

    private String url;

    private Integer status;

    public Menu() {
    }

    public Menu(String menuName, String url, Integer status) {
        this.menuName = menuName;
        this.url = url;
        this.status = status;
    }

    public Menu(Integer id, String menuName, String url, Integer status) {
        this.id = id;
        this.menuName = menuName;
        this.url = url;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuName='" + menuName + '\'' +
                ", url='" + url + '\'' +
                ", status=" + status +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}