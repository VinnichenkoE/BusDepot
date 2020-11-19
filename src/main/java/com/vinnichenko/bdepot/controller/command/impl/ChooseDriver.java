package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

/**
 * The type Choose driver.
 */
public class ChooseDriver implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.APPOINT_DRIVER);
        String busId = req.getParameter(BUS_ID);
        BusService busService = ServiceFactory.getInstance().getBusService();
        UserService userService = ServiceFactory.getInstance().getUserService();
        try {
            List<User> users = userService.findFreeDrivers();
            Optional<Bus> bus = busService.findById(busId);
            if (bus.isPresent()) {
                req.setAttribute(BUS, bus.get());
                req.setAttribute(USERS, users);
            } else {
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Choose driver error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
