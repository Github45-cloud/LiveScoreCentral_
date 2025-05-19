package com.LiveScore.controller;

import com.LiveScore.service.MatchService;
import com.LiveScore.model.MatchModel;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/live-matches")
public class LiveMatchesController extends HttpServlet {
    private MatchService matchService = new MatchService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<MatchModel> matches = matchService.getAllMatches();
        request.setAttribute("matches", matches);
        request.getRequestDispatcher("/WEB-INF/pages/live-matches.jsp").forward(request, response);
    }
}
