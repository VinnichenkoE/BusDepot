package com.vinnichenko.bdepot.controller.command;

import com.vinnichenko.bdepot.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Router execute(HttpServletRequest req, HttpServletResponse resp);
}
