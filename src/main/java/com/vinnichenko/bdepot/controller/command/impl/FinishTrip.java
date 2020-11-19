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

/**
 * The type Finish trip.
 */
public class FinishTrip implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        String tripId = req.getParameter(TRIP_ID);
        TripService tripService = ServiceFactory.getInstance().getTripService();
        try {
            tripService.finishTrip(tripId);
        } catch (ServiceException e) {
            logger.error("Finish trip error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
