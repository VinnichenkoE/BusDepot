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

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class ChangeBusStatus implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.VIEW_BUSES);
        String statusId = req.getParameter(BUS_STATUS_ID);
        String busId = req.getParameter(BUS_ID);
        BusService busService = ServiceFactory.getInstance().getBusService();
        try {
            if (!busService.changeBusStatus(busId, statusId)) {
                router.setForward(PagePath.VIEW_BUSES);
                req.setAttribute(WRONG_DATA_MESSAGE, true);
            }
        } catch (ServiceException e) {
            logger.error("Change bus status error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
