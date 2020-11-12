package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.creator.OrderCreator;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class SaveOrder implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        Order order;
        OrderService service = ServiceFactory.getInstance().getOrderService();
        HashMap<String, String> parameters = new HashMap<>();
        parameters.put(USER_ID, req.getParameter(USER_ID));
        parameters.put(NUMBER_OF_SEATS, req.getParameter(NUMBER_OF_SEATS));
        parameters.put(DISTANCE, req.getParameter(DISTANCE));
        parameters.put(START_DATE, req.getParameter(START_DATE));
        parameters.put(END_DATE, req.getParameter(END_DATE));
        parameters.put(START_POINT, req.getParameter(START_POINT));
        parameters.put(END_POINT, req.getParameter(END_POINT));
        if (DataValidator.checkOrderData(parameters)) {
            order = OrderCreator.createOrder(parameters);
            long userId = Long.parseLong(parameters.get(USER_ID));
            try {
                service.save(userId, order);
            } catch (ServiceException e) {
                router.setForward(PagePath.ERROR_404);
                logger.error("save order error", e);
            }
        } else {
            req.setAttribute(PARAMETERS, parameters);
            router.setForward(PagePath.ADD_ORDER);
        }
        return router;
    }
}
