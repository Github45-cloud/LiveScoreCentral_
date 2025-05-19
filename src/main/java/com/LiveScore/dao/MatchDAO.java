package com.LiveScore.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.LiveScore.config.DbConfig;
import com.LiveScore.model.MatchModel;

public class MatchDAO {

    public void insertMatch(MatchModel match) throws Exception {
    	String sql = "INSERT INTO matches (match_id, home_team_name, away_team_name, home_team_score, away_team_score, kickoff_date_time, location, stadium_name) "
    	           + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        System.out.println("Inserting into DB: " + match.getMatchId());

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, match.getMatchId());
            stmt.setString(2, match.getHomeTeam());
            stmt.setString(3, match.getAwayTeam());
            stmt.setInt(4, match.getHomeScore());
            stmt.setInt(5, match.getAwayScore());
            stmt.setTimestamp(6, match.getKickoff());
            stmt.setString(7, match.getLocation());
            stmt.setString(8, match.getStadium());

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Rows inserted: " + rowsInserted);

        }
    }

    public List<MatchModel> getAllMatches() throws Exception {
        List<MatchModel> matchList = new ArrayList<>();

        String sql = "SELECT * FROM matches WHERE kickoff_date_time <= NOW() ORDER BY kickoff_date_time DESC";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                MatchModel match = new MatchModel();
                match.setMatchId(rs.getString("match_id"));
                match.setHomeTeam(rs.getString("home_team_name"));
                match.setAwayTeam(rs.getString("away_team_name"));
                match.setHomeScore(rs.getInt("home_team_score"));
                match.setAwayScore(rs.getInt("away_team_score"));
                match.setKickoff(rs.getTimestamp("kickoff_date_time"));
                match.setLocation(rs.getString("location"));
                match.setStadium(rs.getString("stadium_name"));

                matchList.add(match);
            }
        }

        return matchList;
    }


    public List<MatchModel> getUpcomingMatches() throws Exception {
        List<MatchModel> matchList = new ArrayList<>();

        // DELETE matches that are starting within the next 2 minutes 
        String deleteSQL = "DELETE FROM matches WHERE kickoff_date_time < (NOW() + INTERVAL 2 MINUTE) AND kickoff_date_time >= CURDATE() + INTERVAL 1 DAY";

        // SELECT matches scheduled for tomorrow onward
        String selectSQL = "SELECT * FROM matches WHERE kickoff_date_time >= CURDATE() + INTERVAL 1 DAY ORDER BY kickoff_date_time ASC";

        try (Connection conn = DbConfig.getConnection()) {

            // Step 1: Delete matches starting in the next 2 minutes (but only future matches)
            try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSQL)) {
                int deleted = deleteStmt.executeUpdate();
                System.out.println("Deleted upcoming matches starting in 2 min: " + deleted);
            }

            // Step 2: Fetch still-upcoming matches (tomorrow onward)
            try (PreparedStatement stmt = conn.prepareStatement(selectSQL);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    MatchModel match = new MatchModel();
                    match.setMatchId(rs.getString("match_id"));
                    match.setHomeTeam(rs.getString("home_team_name"));
                    match.setAwayTeam(rs.getString("away_team_name"));
                    match.setHomeScore(rs.getInt("home_team_score"));
                    match.setAwayScore(rs.getInt("away_team_score"));
                    match.setKickoff(rs.getTimestamp("kickoff_date_time"));
                    match.setLocation(rs.getString("location"));
                    match.setStadium(rs.getString("stadium_name"));
                    matchList.add(match);
                }
            }
        }

        return matchList;
    }
    public boolean updateMatch(String matchId, int homeScore, int awayScore, String status) throws Exception {
        String sql = "UPDATE matches SET home_team_score = ?, away_team_score = ?, match_status = ? WHERE match_id = ?";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, homeScore);
            stmt.setInt(2, awayScore);
            stmt.setString(3, status);
            stmt.setString(4, matchId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        }
    }
    public boolean deleteMatchById(String matchId) throws Exception {
        String sql = "DELETE FROM matches WHERE match_id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, matchId);
            return stmt.executeUpdate() > 0;
        }
    }
}