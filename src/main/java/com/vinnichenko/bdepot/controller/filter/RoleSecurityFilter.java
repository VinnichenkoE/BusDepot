package com.vinnichenko.bdepot.controller.filter;

import com.vinnichenko.bdepot.controller.PagePath;
import com.vinnichenko.bdepot.controller.command.Command;
import com.vinnichenko.bdepot.controller.command.CommandProvider;
import com.vinnichenko.bdepot.controller.command.CommandSet;
import com.vinnichenko.bdepot.model.entity.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

import static com.vinnichenko.bdepot.controller.RequestParameter.*;
import static com.vinnichenko.bdepot.controller.SessionParameter.*;

/**
 * The type Role security filter.
 */
@WebFilter(urlPatterns = "/*")
public class RoleSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String commandName = request.getParameter(COMMAND);
        if (commandName != null) {
            User user = (User) session.getAttribute(USER);
            Command command = CommandProvider.getCommand(commandName.toUpperCase());
            Set<Command> commandSet = null;
            if (user == null) {
                commandSet = CommandSet.GUEST.getCommandSet();
            } else {
                switch (user.getRole()) {
                    case CUSTOMER:
                        commandSet = CommandSet.CUSTOMER.getCommandSet();
                        break;
                    case DRIVER:
                        commandSet = CommandSet.DRIVER.getCommandSet();
                        break;
                    case DISPATCHER:
                        commandSet = CommandSet.DISPATCHER.getCommandSet();
                        break;
                }
            }
            if (!commandSet.contains(command)) {
                request.getRequestDispatcher(PagePath.ERROR_404).forward(request, response);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
