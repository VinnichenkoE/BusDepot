package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.BUSES;
import static com.vinnichenko.bdepot.controller.SessionParameter.USER;

public class ViewBuses implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_BUSES_PAGE);
        User user = (User) req.getSession().getAttribute(USER);
        Map<Bus, User> buses = new HashMap<>();
        BusService busService = ServiceFactory.getInstance().getBusService();
        try {
            if (user.getRole().equals(User.Role.DISPATCHER)) {
                buses = busService.findAllWithDriver();
            } else {
                List<Bus> busList = busService.findByUserId(user.getUserId());
                for (Bus bus: busList) {
                    buses.put(bus, null);
                }
            }
            req.setAttribute(BUSES, buses);
        } catch (ServiceException e) {
            logger.error("View buses error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
