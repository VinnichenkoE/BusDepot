package com.vinnichenko.bdepot.controller.command;

import com.vinnichenko.bdepot.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 * Handles client requests
 */
public interface Command {
    /**
     * Execute router.
     *
     * @param req  the req
     * @param resp the resp
     * @return the router
     */
    Router execute(HttpServletRequest req, HttpServletResponse resp);
}
