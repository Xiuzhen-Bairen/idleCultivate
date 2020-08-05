package com.idlewow.rms.filter;

import org.apache.logging.log4j.ThreadContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogSessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpSession session = ((HttpServletRequest) request).getSession(false);
            if (session != null) {
                ThreadContext.put("sessionId", session.getId());
            }

            chain.doFilter(request, response);
        } finally {
            ThreadContext.remove("sessionId");
        }
    }

    @Override
    public void destroy() {
    }
}
