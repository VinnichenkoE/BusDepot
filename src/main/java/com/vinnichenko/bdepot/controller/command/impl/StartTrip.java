package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.TripService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class StartTrip implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        long userId = Long.parseLong(req.getParameter(USER_ID));
        long orderId = Long.parseLong(req.getParameter(ORDER_ID));
        TripService tripService = ServiceFactory.getInstance().getTripService();
        try {
            if (!tripService.startTrip(userId, orderId)) {
                router.setForward(PagePath.VIEW_ORDERS);
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Start trip error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
