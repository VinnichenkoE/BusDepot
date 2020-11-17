package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.entity.Order;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.OrderService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class AppointBus implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.APPOINT_BUS);
        String id = req.getParameter(ORDER_ID);
        OrderService orderService = ServiceFactory.getInstance().getOrderService();
        BusService busService = ServiceFactory.getInstance().getBusService();
        Optional<Order> order;
        List<Bus> buses;
        try {
            order = orderService.findById(id);
            if (order.isPresent()) {
                buses = busService.findFreeBusesByNumberSeats(order.get().getNumberOfSeats(),
                        order.get().getStartDate(), order.get().getEndDate());
                req.setAttribute(BUSES, buses);
                req.setAttribute(ORDER, order.get());
            } else {
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Appoint bus error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}