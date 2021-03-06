package com.vinnichenko.bdepot.controller.filter;

import com.vinnichenko.bdepot.controller.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Page security filter.
 */
@WebFilter(urlPatterns = "/jsp/*")
public class PageSecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect(PagePath.INDEX);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
