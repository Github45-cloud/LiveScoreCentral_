package com.LiveScore.service;

import java.util.List;

import com.LiveScore.dao.MatchDAO;
import com.LiveScore.model.MatchModel;

public class MatchService {
    private MatchDAO matchDAO;

    public MatchService() {
        matchDAO = new MatchDAO();
    }

    public void addMatch(MatchModel match) throws Exception {
        matchDAO.insertMatch(match);
    }

    public List<MatchModel> getAllMatches() {
        try {
            return matchDAO.getAllMatches();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
 
    public List<MatchModel> getUpcomingMatches() {
        try {
            return matchDAO.getUpcomingMatches();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean updateMatch(String matchId, int homeScore, int awayScore, String status) {
        try {
            return matchDAO.updateMatch(matchId, homeScore, awayScore, status);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean deleteMatch(String matchId) {
        try {
            return matchDAO.deleteMatchById(matchId);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
