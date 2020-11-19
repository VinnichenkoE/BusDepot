package com.vinnichenko.bdepot.controller.router;

/**
 * The type Router.
 */
public class Router {
    private String page;
    private RouterType type = RouterType.FORWARD;

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     * @param type the type
     */
    public Router(String page, RouterType type) {
        this.page = page;
        this.type = type;
    }

    /**
     * Instantiates a new Router.
     *
     * @param page the page
     */
    public Router(String page) {
        this.page = page;
    }

    /**
     * Gets page.
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public RouterType getType() {
        return type;
    }

    /**
     * Sets forward.
     *
     * @param page the page
     */
    public void setForward(String page) {
        type = RouterType.FORWARD;
        this.page = page;
    }

    /**
     * Sets include.
     *
     * @param page the page
     */
    public void setInclude(String page) {
        type = RouterType.INCLUDE;
        this.page = page;
    }
}
