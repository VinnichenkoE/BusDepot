package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Trip;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.TripService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.USER;

public class ViewTrips implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_TRIPS);
        TripService service = ServiceFactory.getInstance().getTripService();
        List<Trip> trips;
        User user = (User) req.getSession().getAttribute(USER);
        try {
            if (user.getRole() == User.Role.DRIVER) {
                trips = service.findByUserId(user.getUserId());
                req.setAttribute(TRIPS, trips); //TODO
            }
        } catch (ServiceException e) {
            logger.error("view trips error", e);
            router.setForward(PagePath.ERROR_404);
        }
        return router;
    }
}
