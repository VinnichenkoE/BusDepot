package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.*;

/**
 * The type Update user page.
 */
public class UpdateUserPage implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.UPDATE_USER);
        Map<String, String> parameters = new HashMap<>();
        User user = (User)req.getSession().getAttribute(USER);
        parameters.put(USER_LOGIN, user.getLogin());
        parameters.put(USER_NAME, user.getName());
        parameters.put(USER_SURNAME, user.getSurname());
        parameters.put(USER_PHONE_NUMBER, user.getPhoneNumber());
        req.setAttribute(PARAMETERS, parameters);
        return router;
    }
}
