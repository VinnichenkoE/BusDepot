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

public class AppointDriver implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router("controller?command=view_buses");
        String stringUserId = req.getParameter("user_id");
        String stringVehicleId = req.getParameter("bus_id");
        long userId;
        if (stringUserId.equals("")) {
            userId = 0L;
        } else {
            userId = Long.parseLong(stringUserId);
        }
        BusService busService = ServiceFactory.getInstance().getBusService();
        try {
            busService.appointUser(Integer.parseInt(stringVehicleId), userId);
        } catch (ServiceException e) {
            logger.error("Appoint driver error", e);
            router.setForward(PagePath.ERROR_404);
        }
        return router;
    }
}
