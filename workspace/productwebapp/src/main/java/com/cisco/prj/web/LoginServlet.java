package com.cisco.prj.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // assume all users are valid, no validation
        String email = req.getParameter("email");
        String pwd = req.getParameter("pwd");
        // validation
        // if valid create a session else redirect to login
        HttpSession session = req.getSession(true); // create session if not exist, use it if it exists
        session.setAttribute("user", email);

        resp.sendRedirect("index.jsp");
    }
}
