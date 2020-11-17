package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class AppointOrder implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.ACCOUNT);
        String userId = req.getParameter(USER_ID);
        String orderId = req.getParameter(ORDER_ID);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        try {
            if (!orderService.appointDriver(userId, orderId)) {
                router.setForward(PagePath.VIEW_ORDERS);
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Appoint order error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
