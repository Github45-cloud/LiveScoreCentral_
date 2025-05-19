package com.LiveScore.controller;

import com.LiveScore.service.MatchService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/admin/deleteMatch")
public class DeleteMatchController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private MatchService matchService;

    public void init() {
        matchService = new MatchService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matchId = request.getParameter("matchId");

        if (matchId == null || matchId.trim().isEmpty()) {
            request.setAttribute("error", "Match ID is required.");
            request.getRequestDispatcher("/WEB-INF/admin/delete-match.jsp").forward(request, response);
            return;
        }

        boolean success = matchService.deleteMatch(matchId);

        if (success) {
            request.setAttribute("message", "Match deleted successfully.");
        } else {
            request.setAttribute("error", "Failed to delete match. Please check Match ID.");
        }

        request.getRequestDispatcher("/WEB-INF/admin/delete-match.jsp").forward(request, response);
    }
}
