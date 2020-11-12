package com.vinnichenko.bdepot.controller;

import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.command.CommandProvider;
import com.vinnichenko.bdepot.controller.router.Router;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(urlPatterns = "/controller")
public class FrontController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandProvider.getCommand(req.getParameter(RequestParameter.COMMAND));
        Router router = command.execute(req, resp);
        HttpSession session = req.getSession();
        session.setAttribute(RequestParameter.CURRENT_PAGE, router.getPage());
        switch (router.getType()) {
            case FORWARD:
                req.getRequestDispatcher(router.getPage()).forward(req, resp);
                break;
            case REDIRECT:
                resp.sendRedirect(router.getPage());
                break;
            case INCLUDE:
                req.getRequestDispatcher(router.getPage()).include(req, resp);
                break;
        }
    }
}
