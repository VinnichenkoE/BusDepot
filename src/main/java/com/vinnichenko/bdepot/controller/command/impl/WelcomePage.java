package com.vinnichenko.bdepot.controller.command.impl;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.router.Router;
import com.vinnichenko.bdepot.exception.ServiceException;
import com.vinnichenko.bdepot.model.entity.Bus;
import com.vinnichenko.bdepot.model.service.BusService;
import com.vinnichenko.bdepot.model.service.ServiceFactory;
import com.vinnichenko.bdepot.util.PaginationUtil;
import com.vinnichenko.bdepot.validator.DataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;

public class WelcomePage implements Command {
    private static final Logger logger = LogManager.getLogger();
    private static final int DEFAULT_NUMBER_ITEMS_PER_PAGE = 4;

    @Override
    public Router execute(HttpServletRequest req, HttpServletResponse resp) {
        Router router = new Router(PagePath.WELCOME_PAGE);
        BusService busService = ServiceFactory.getInstance().getBusService();
        String stringPageNumber = req.getParameter(PAGE_NUMBER);
        String stringNumberItems = req.getParameter(NUMBER_ITEMS_PER_PAGE);
        HttpSession session = req.getSession();
        int pageNumber;
        int numberItems;
        if (stringPageNumber == null || !DataValidator.isNumber(stringPageNumber)) {
            pageNumber = 1;
        } else {
            pageNumber = Integer.parseInt(stringPageNumber);
        }
        if (stringNumberItems == null || !DataValidator.isNumber(stringNumberItems)) {
            if (session.getAttribute(NUMBER_ITEMS_PER_PAGE) == null) {
                numberItems = DEFAULT_NUMBER_ITEMS_PER_PAGE;
                session.setAttribute(NUMBER_ITEMS_PER_PAGE, numberItems);
            } else {
                numberItems = (Integer) session.getAttribute(NUMBER_ITEMS_PER_PAGE);
            }
        } else {
            numberItems = Integer.parseInt(stringNumberItems);
            session.setAttribute(NUMBER_ITEMS_PER_PAGE, numberItems);
        }
        try {
            List<Bus> buses = busService.findAll();
            List<Bus> subList = PaginationUtil.splitByNumberItemsPerPage(buses, numberItems, pageNumber);
            int amountPage = PaginationUtil.amountPages(buses.size(), numberItems);
            req.setAttribute(BUSES, subList);
            req.setAttribute(NUMBER_PAGES, amountPage);
        } catch (ServiceException e) {
            logger.error("Welcome page error", e);
            router.setForward(PagePath.ERROR_500);
        }
        return router;
    }
}
