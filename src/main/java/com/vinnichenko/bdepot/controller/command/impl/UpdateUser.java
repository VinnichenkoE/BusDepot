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
import java.util.HashMap;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.*;

/**
 * The type Update user.
 */
public class UpdateUser implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT, RouterType.REDIRECT);
        Map<String, String> parameters = new HashMap<>();
        UserService userService = ServiceFactory.getInstance().getUserService();
        User user = (User) req.getSession().getAttribute(USER);
        long id = user.getUserId();
        int roleIndex = user.getRole().ordinal();
        parameters.put(USER_ID, String.valueOf(id));
        parameters.put(USER_NEW_LOGIN, req.getParameter(USER_LOGIN));
        parameters.put(USER_LOGIN, user.getLogin());
        parameters.put(USER_NAME, req.getParameter(USER_NAME));
        parameters.put(USER_SURNAME, req.getParameter(USER_SURNAME));
        parameters.put(USER_PHONE_NUMBER, req.getParameter(USER_PHONE_NUMBER));
        parameters.put(USER_ROLE_INDEX, String.valueOf(roleIndex));
        try {
            boolean update = userService.updateUser(parameters);
            if (update) {
                req.getSession().setAttribute(USER, UserCreator.createUser(parameters));
            } else {
                req.setAttribute(PARAMETERS, parameters);
                router.setForward(PagePath.UPDATE_USER);
            }
        } catch (ServiceException e) {
            logger.error("User update error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
