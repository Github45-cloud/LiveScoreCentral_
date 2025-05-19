package com.LiveScore.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/portfolio")
public class PortfolioController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("PortfolioController accessed");  // Added logging for debugging
        request.getRequestDispatcher("/WEB-INF/pages/portfolio.jsp").forward(request, response);
    }
}
