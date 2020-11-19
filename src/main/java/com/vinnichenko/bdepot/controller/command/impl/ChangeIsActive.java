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
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

/**
 * The type Change is active.
 */
public class ChangeIsActive implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_USERS);
        String login = req.getParameter(USER_LOGIN);
        byte isActive = Byte.parseByte(req.getParameter(USER_IS_ACTIVE));
        UserService userService = ServiceFactory.getInstance().getUserService();
        Optional<User> user;
        try {
            user = userService.findUserByLogin(login);
            if (user.isPresent()) {
                user.get().setIsActive(isActive);
                userService.updateUser(user.get());
            } else {
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Change is active error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
