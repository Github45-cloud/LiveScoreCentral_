package com.LiveScore.controller;

import com.LiveScore.model.ResultDistribution;
import com.LiveScore.service.WeeklyInsightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/updateResults")
public class UpdateResultsController extends HttpServlet {
    private WeeklyInsightsService service = new WeeklyInsightsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int homeWins = Integer.parseInt(request.getParameter("homeWins"));
            int awayWins = Integer.parseInt(request.getParameter("awayWins"));
            int draws = Integer.parseInt(request.getParameter("draws"));

            if (homeWins < 0 || awayWins < 0 || draws < 0) {
                request.setAttribute("error", "All values must be non-negative integers.");
                request.getRequestDispatcher("/WEB-INF/admin/updateWeeklyInsights.jsp").forward(request, response);
                return;
            }

            ResultDistribution result = new ResultDistribution();
            result.setHomeWins(homeWins);
            result.setAwayWins(awayWins);
            result.setDraws(draws);

            service.saveResultDistribution(result);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Please enter valid numeric values for all fields.");
            request.getRequestDispatcher("/WEB-INF/admin/updateWeeklyInsights.jsp").forward(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
    }
}
