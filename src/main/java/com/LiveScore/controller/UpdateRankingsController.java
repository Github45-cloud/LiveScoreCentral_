package com.LiveScore.controller;

import com.LiveScore.model.PowerRanking;
import com.LiveScore.service.WeeklyInsightsService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/updateRankings")
public class UpdateRankingsController extends HttpServlet {
    private WeeklyInsightsService service = new WeeklyInsightsService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<PowerRanking> rankings = new ArrayList<>();
        HttpSession session = request.getSession();

        for (int i = 1; i <= 10; i++) {
            String teamName = request.getParameter("team" + i + "Name");
            String recentResults = request.getParameter("team" + i + "Results");

            // Validate team name
            if (teamName == null || !teamName.matches("^[A-Za-z ]+$")) {
                session.setAttribute("error", "Team " + i + " name must contain only alphabets.");
                response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
                return;
            }

            // Validate recent results (W, L, D with optional spaces)
            if (recentResults == null || !recentResults.matches("^([WLD](\\s)?)+$")) {
                session.setAttribute("error", "Recent results for Team " + i + " must only contain W, L, or D.");
                response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
                return;
            }

            PowerRanking pr = new PowerRanking();
            pr.setRankPosition(i);
            pr.setTeamName(teamName);
            pr.setRecentResults(recentResults);
            rankings.add(pr);
        }

        if (!rankings.isEmpty()) {
            service.savePowerRankings(rankings);
            session.setAttribute("success", "Power rankings updated successfully.");
        }

        response.sendRedirect(request.getContextPath() + "/admin/updateWeeklyInsights");
    }
}
