package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.controller.router.RouterType;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.creator.UserCreator;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.*;

public class SaveUser implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final String DRIVER_ROLE_INDEX = "1";

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.WELCOME, RouterType.REDIRECT);
        UserService userService = ServiceFactory.getInstance().getUserService();
        Map<String, String> parameters = new HashMap<>();
        HttpSession session = req.getSession();
        parameters.put(USER_LOGIN, req.getParameter(USER_LOGIN));
        parameters.put(USER_PASSWORD, req.getParameter(USER_PASSWORD));
        parameters.put(USER_NAME, req.getParameter(USER_NAME));
        parameters.put(USER_SURNAME, req.getParameter(USER_SURNAME));
        parameters.put(USER_PHONE_NUMBER, req.getParameter(USER_PHONE_NUMBER));
        User user = (User) session.getAttribute(USER);
        if (user != null && user.getRole() == User.Role.DISPATCHER) {
            parameters.put(USER_ROLE_INDEX, DRIVER_ROLE_INDEX);
        }
        try {
            if (userService.saveUser(parameters) > 0) {
                if (user == null) {
                    session.setAttribute(USER, UserCreator.createUser(parameters));
                } else {
                    router.setForward(PagePath.ACCOUNT);
                }
            } else {
                req.setAttribute(PARAMETERS, parameters);
                router.setForward(PagePath.REGISTRATION);
            }
        } catch (ServiceException e) {
            logger.error("User save error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
