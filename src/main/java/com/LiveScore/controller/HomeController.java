package com.LiveScore.controller;

import com.LiveScore.model.WeeklyInsightsModel;
import com.LiveScore.service.WeeklyInsightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/home")
public class HomeController extends HttpServlet {

    private WeeklyInsightsService insightsService = new WeeklyInsightsService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WeeklyInsightsModel insight = insightsService.getWeeklyInsights();
        request.setAttribute("insight", insight);
        request.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(request, response);  // <-- Updated path
    }
}
