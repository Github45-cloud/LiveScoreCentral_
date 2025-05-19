// UpcomingMatchesController.java
package com.LiveScore.controller;

import java.io.IOException;
import java.util.List;

import com.LiveScore.model.MatchModel;
import com.LiveScore.service.MatchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/upcoming-matches")
public class UpcomingMatchesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private MatchService matchService;

    public UpcomingMatchesController() {
        super();
        matchService = new MatchService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        List<MatchModel> upcomingMatches = matchService.getUpcomingMatches();
        request.setAttribute("matches", upcomingMatches);

        request.getRequestDispatcher("/WEB-INF/pages/upcoming-matches.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}
