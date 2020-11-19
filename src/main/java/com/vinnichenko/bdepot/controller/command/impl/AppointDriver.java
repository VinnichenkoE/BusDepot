package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vinnichenko.bdepot.controller.PagePath.*;
import static com.vinnichenko.bdepot.controller.RequestParameter.*;

/**
 * The type Appoint driver.
 */
public class AppointDriver implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(VIEW_BUSES);
        String userId = req.getParameter(USER_ID);
        String vehicleId = req.getParameter(BUS_ID);
        BusService busService = ServiceFactory.getInstance().getBusService();
        try {
            if (!busService.appointUser(vehicleId, userId)) {
                router.setForward(APPOINT_DRIVER);
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Appoint driver error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
