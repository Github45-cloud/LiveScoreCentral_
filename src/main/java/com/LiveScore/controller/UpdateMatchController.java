package com.LiveScore.controller;

import com.LiveScore.service.MatchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/updateMatch")
public class UpdateMatchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatchService matchService;

    public void init() {
        matchService = new MatchService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matchId = request.getParameter("matchId");
        String homeScoreStr = request.getParameter("homeScore");
        String awayScoreStr = request.getParameter("awayScore");
        String status = request.getParameter("status");

        String errorMessage = validateInputs(matchId, homeScoreStr, awayScoreStr, status);

        if (errorMessage != null) {
            request.setAttribute("error", errorMessage);
            request.getRequestDispatcher("/WEB-INF/admin/update-match.jsp").forward(request, response);
            return;
        }

        // Convert scores after validation
        int homeScore = Integer.parseInt(homeScoreStr);
        int awayScore = Integer.parseInt(awayScoreStr);

        boolean success = matchService.updateMatch(matchId, homeScore, awayScore, status);

        if (success) {
            request.setAttribute("message", "Match updated successfully.");
        } else {
            request.setAttribute("error", "Failed to update match. Please check Match ID.");
        }

        request.getRequestDispatcher("/WEB-INF/admin/update-match.jsp").forward(request, response);
    }

    private String validateInputs(String matchId, String homeScoreStr, String awayScoreStr, String status) {
        if (matchId == null || matchId.trim().isEmpty()) {
            return "Match ID is required.";
        }

        int homeScore, awayScore;
        try {
            homeScore = Integer.parseInt(homeScoreStr);
            if (homeScore < 0) {
                return "Home team score cannot be negative.";
            }
        } catch (NumberFormatException e) {
            return "Home team score must be a valid number.";
        }

        try {
            awayScore = Integer.parseInt(awayScoreStr);
            if (awayScore < 0) {
                return "Away team score cannot be negative.";
            }
        } catch (NumberFormatException e) {
            return "Away team score must be a valid number.";
        }

        // Validate status is one of allowed values
        if (status == null || !(status.equals("Upcoming") || status.equals("Live") || status.equals("Interval") ||
                status.equals("Finished") || status.equals("Postponed"))) {
            return "Invalid match status selected.";
        }

        return null; // no error
    }
}
