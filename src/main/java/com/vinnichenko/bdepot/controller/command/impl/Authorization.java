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
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.USER;

public class Authorization implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.WELCOME);
        Map<String, String> parameters = new HashMap<>();
        parameters.put(LOGIN, req.getParameter(LOGIN));
        parameters.put(PASSWORD, req.getParameter(PASSWORD));
        UserService service = ServiceFactory.getInstance().getUserService();
        try {
            Optional<User> user = service.authorize(parameters);
            if (user.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute(USER, user.get());
            } else {
                req.setAttribute(MESSAGE, parameters.get(MESSAGE));
            }
        } catch (ServiceException e) {
            logger.error("Error during authorization", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
