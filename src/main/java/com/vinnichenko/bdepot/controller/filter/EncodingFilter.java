package com.vinnichenko.bdepot.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * The type Encoding filter.
 */
@WebFilter(urlPatterns = "/*")
public class EncodingFilter implements Filter {
    private static final String ENCODING_TYPE = "UTF-8";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding(ENCODING_TYPE);
        servletResponse.setCharacterEncoding(ENCODING_TYPE);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
