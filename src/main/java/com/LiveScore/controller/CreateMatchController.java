package com.LiveScore.controller;

import com.LiveScore.model.MatchModel;
import com.LiveScore.service.MatchService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/create-match")
public class CreateMatchController extends HttpServlet {

    private MatchService matchService;

    @Override
    public void init() throws ServletException {
        matchService = new MatchService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matchId = request.getParameter("matchId");
        String homeTeam = request.getParameter("homeTeam");
        String awayTeam = request.getParameter("awayTeam");
        String homeScoreStr = request.getParameter("homeScore");
        String awayScoreStr = request.getParameter("awayScore");
        String kickoff = request.getParameter("kickoff");
        String location = request.getParameter("location");
        String stadium = request.getParameter("stadium");

        try {
            // Validation
            if (matchId == null || matchId.isEmpty() ||
                homeTeam == null || homeTeam.isEmpty() ||
                awayTeam == null || awayTeam.isEmpty() ||
                homeScoreStr == null || homeScoreStr.isEmpty() ||
                awayScoreStr == null || awayScoreStr.isEmpty() ||
                kickoff == null || kickoff.isEmpty() ||
                location == null || location.isEmpty() ||
                stadium == null || stadium.isEmpty()) {

                request.setAttribute("error", "All fields are required.");
                request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);
                return;
            }

            // Parse scores and timestamp
            int homeScore = Integer.parseInt(homeScoreStr);
            int awayScore = Integer.parseInt(awayScoreStr);
            Timestamp kickoffTime = Timestamp.valueOf(kickoff.replace("T", " ") + ":00");

            // Set data
            MatchModel match = new MatchModel();
            match.setMatchId(matchId);
            match.setHomeTeam(homeTeam);
            match.setAwayTeam(awayTeam);
            match.setHomeScore(homeScore);
            match.setAwayScore(awayScore);
            match.setKickoff(kickoffTime);
            match.setLocation(location);
            match.setStadium(stadium);

            // Save match
            matchService.addMatch(match);

            request.setAttribute("success", "Match created successfully!");
            request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            request.setAttribute("error", "Scores must be valid integers.");
            request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);

        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date/time format.");
            request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Something went wrong: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/admin/create-match.jsp").forward(request, response);
        }
        
    }
}
