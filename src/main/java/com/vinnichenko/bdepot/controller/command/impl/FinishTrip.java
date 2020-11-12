package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.model.service.TripService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FinishTrip implements Command {
    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        long tripId = Long.parseLong(req.getParameter("trip_id"));
        TripService tripService = ServiceFactory.getInstance().getTripService();
        try {
            tripService.finishTrip(tripId);
        } catch (ServiceException e) {
            router.setForward(PagePath.ERROR_404);
        }
        return router;
    }
}
