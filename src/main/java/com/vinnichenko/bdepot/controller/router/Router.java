package com.vinnichenko.bdepot.controller.router;

public class Router {
    private String page;
    private RouterType type = RouterType.FORWARD;

    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    public Router(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public RouterType getType() {
        return type;
    }

    public void setForward(String page) {
        type = RouterType.FORWARD;
        this.page = page;
    }

    public void setInclude(String page) {
        type = RouterType.INCLUDE;
        this.page = page;
    }
}
