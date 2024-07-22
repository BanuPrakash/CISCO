package com.cisco.prj.web;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter(value="*.jsp", dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.FORWARD})
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession ses = req.getSession(false);
        String uri = req.getRequestURI();
        System.out.println(uri);
        if( (ses != null && ses.getAttribute("user") != null) || uri.endsWith("login.jsp")) {
            chain.doFilter(request, response); // allow next one in the chain to execute
        } else {
            res.sendRedirect("login.jsp");
        }

    }
}
