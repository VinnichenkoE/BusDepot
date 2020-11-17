package com.vinnichenko.bdepot.controller.command.impl;


import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.USER;

public class ChangePassword implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(USER_OLD_PASSWORD, req.getParameter(USER_OLD_PASSWORD));
        parameters.put(USER_NEW_PASSWORD, req.getParameter(USER_NEW_PASSWORD));
        parameters.put(USER_LOGIN, ((User) req.getSession().getAttribute(USER)).getLogin());
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            if (!userService.updatePassword(parameters)) {
                parameters.put(USER_LOGIN, req.getParameter(USER_LOGIN));
                parameters.put(USER_NAME, req.getParameter(USER_NAME));
                parameters.put(USER_SURNAME, req.getParameter(USER_SURNAME));
                parameters.put(USER_PHONE_NUMBER, req.getParameter(USER_PHONE_NUMBER));
                req.setAttribute(PARAMETERS, parameters);
                router.setForward(PagePath.UPDATE_USER);
            }
        } catch (ServiceException e) {
            logger.error("Change password error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
