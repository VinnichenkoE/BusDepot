package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.entity.User;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.USER;

public class ViewOrders implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_ORDERS_PAGE);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        User user = (User) req.getSession().getAttribute(USER);
        List<Order> orders;
        try {
            if (user.getRole().equals(User.Role.DISPATCHER)) {
                orders = orderService.findSubmittedOrders();
            } else {
                orders = orderService.findUserOrders(user.getUserId());
            }
            req.setAttribute(ORDERS, orders);
        } catch (ServiceException e) {
            logger.error("View orders error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
