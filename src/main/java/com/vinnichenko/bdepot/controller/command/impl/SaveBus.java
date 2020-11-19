package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.controller.router.RouterType;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

/**
 * The type Save bus.
 */
public class SaveBus implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> parameters = new HashMap<>();
        Router router;
        parameters.put(BRAND, req.getParameter(BRAND));
        parameters.put(MODEL, req.getParameter(MODEL));
        parameters.put(REGISTRATION_NUMBER, req.getParameter(REGISTRATION_NUMBER));
        parameters.put(NUMBER_OF_SEATS, req.getParameter(NUMBER_OF_SEATS));
        parameters.put(RATE, req.getParameter(RATE));
        parameters.put(IMAGE_NAME, (String) req.getAttribute(IMAGE_NAME));
        BusService busService = ServiceFactory.getInstance().getBusService();
        try {
            if (busService.save(parameters) > 0) {
                router = new Router(PagePath.VIEW_BUSES, RouterType.REDIRECT);
            } else {
                req.setAttribute(PARAMETERS, parameters);
                router = new Router(PagePath.ADD_BUS);
            }
        } catch (ServiceException e) {
            logger.error("Save bus error", e);
            router = new Router(PagePath.ERROR_500);
        }
        return router;
    }
}
