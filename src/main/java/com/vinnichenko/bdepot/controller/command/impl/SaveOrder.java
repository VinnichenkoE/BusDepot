package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.controller.router.RouterType;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

/**
 * The type Save order.
 */
public class SaveOrder implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT, RouterType.REDIRECT);
        OrderService service = ServiceFactory.getInstance().getOrderService();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(USER_ID, req.getParameter(USER_ID));
        parameters.put(NUMBER_OF_SEATS, req.getParameter(NUMBER_OF_SEATS));
        parameters.put(DISTANCE, req.getParameter(DISTANCE));
        parameters.put(START_DATE, req.getParameter(START_DATE));
        parameters.put(END_DATE, req.getParameter(END_DATE));
        parameters.put(START_POINT, req.getParameter(START_POINT));
        parameters.put(END_POINT, req.getParameter(END_POINT));
        try {
            if (service.save(parameters) < 0) {
                req.setAttribute(PARAMETERS, parameters);
                router.setForward(PagePath.ADD_ORDER);
            }
        } catch (ServiceException e) {
            router.setForward(PagePath.ERROR_500);
            logger.error("Save order error", e);
        }
        return router;
    }
}
