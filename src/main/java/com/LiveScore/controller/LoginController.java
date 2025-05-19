package com.LiveScore.controller;

import com.LiveScore.model.UserModel;
import com.LiveScore.service.LoginService;
import com.LiveScore.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private LoginService loginService;

    @Override
    public void init() throws ServletException {
        loginService = new LoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel user = loginService.getUserByUsername(username);

        if (user != null && user.getPassword().equals(PasswordUtil.encrypt(username, password))) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            // Redirect based on role_id
            if (user.getRoleId() == 1) {
                response.sendRedirect(request.getContextPath() + "/adminHome");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");
            }
        } else {
            request.setAttribute("error", "Invalid username or password.");
            request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
        }
    }
}
