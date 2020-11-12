package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class ChangeBusStatus implements Command {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router("controller?command=view_buses");
        String statusId = req.getParameter(BUS_STATUS_ID);
        String busId = req.getParameter(BUS_ID);
        BusService busService = ServiceFactory.getInstance().getBusService();
        Bus.BusStatus status = Bus.BusStatus.values()[Integer.parseInt(statusId)];
        try {
            Optional<Bus> bus = busService.findById(Integer.parseInt(busId));
            if (bus.isPresent()) {
                bus.get().setStatus(status);
                busService.update(bus.get());
            }
        } catch (ServiceException e) {
            logger.error("Change bus status error", e);
            router.setForward(PagePath.ERROR_404);
        }
        return router;
    }
}
