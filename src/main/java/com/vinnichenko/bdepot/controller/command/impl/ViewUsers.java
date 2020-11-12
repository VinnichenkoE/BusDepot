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
import java.util.List;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class ViewUsers implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_USERS);
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> users;
        try {
            users = userService.findAll();
            req.setAttribute(USERS, users);
        } catch (ServiceException e) {
            router.setForward(PagePath.ERROR_404);
           logger.error("find all users error", e);
        }
        return router;
    }
}
