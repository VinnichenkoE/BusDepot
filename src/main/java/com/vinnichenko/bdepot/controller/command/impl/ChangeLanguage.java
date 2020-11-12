package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class ChangeLanguage implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        String currentPage = (String) session.getAttribute(CURRENT_PAGE);
        if (currentPage.equals(PagePath.WELCOME_PAGE)) {
            currentPage = PagePath.WELCOME;
        }
        String lang = req.getParameter(LANGUAGE);
        session.setAttribute(LANGUAGE, lang);
        return new Router(currentPage);
    }
}
