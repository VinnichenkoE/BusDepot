package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The type Logout.
 */
public class Logout implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.WELCOME);
        HttpSession session = req.getSession();
        session.invalidate();
        return router;
    }
}
