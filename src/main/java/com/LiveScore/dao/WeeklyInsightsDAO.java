package com.LiveScore.dao;

import com.LiveScore.config.DbConfig;
import com.LiveScore.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WeeklyInsightsDAO {

    // ----- Result Distribution -----
    public void insertResultDistribution(ResultDistribution result) {
        String sql = "INSERT INTO weekly_result_distribution (home_wins, away_wins, draws) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getHomeWins());
            stmt.setInt(2, result.getAwayWins());
            stmt.setInt(3, result.getDraws());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultDistribution getLatestResultDistribution() {
        String sql = "SELECT * FROM weekly_result_distribution ORDER BY updated_at DESC LIMIT 1";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                ResultDistribution result = new ResultDistribution();
                result.setId(rs.getInt("id"));
                result.setHomeWins(rs.getInt("home_wins"));
                result.setAwayWins(rs.getInt("away_wins"));
                result.setDraws(rs.getInt("draws"));
                return result;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ----- Power Rankings -----
    public void insertPowerRankings(List<PowerRanking> rankings) {
        String deleteSql = "DELETE FROM weekly_power_rankings";
        String insertSql = "INSERT INTO weekly_power_rankings (rank_position, team_name, recent_results) VALUES (?, ?, ?)";

        try (Connection conn = DbConfig.getConnection();
        
             Statement deleteStmt = conn.createStatement();
             PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {

        	deleteStmt.executeUpdate(deleteSql);  // Clear old rankings

            for (PowerRanking pr : rankings) {
                insertStmt.setInt(1, pr.getRankPosition());
                insertStmt.setString(2, pr.getTeamName());
                insertStmt.setString(3, pr.getRecentResults());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<PowerRanking> getPowerRankings() {
        List<PowerRanking> rankings = new ArrayList<>();
        String sql = "SELECT * FROM weekly_power_rankings ORDER BY rank_position ASC";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PowerRanking pr = new PowerRanking();
                pr.setId(rs.getInt("id"));
                pr.setRankPosition(rs.getInt("rank_position"));
                pr.setTeamName(rs.getString("team_name"));
                pr.setRecentResults(rs.getString("recent_results"));
                rankings.add(pr);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return rankings;
    }

    // ----- Highlights -----
    public void insertHighlights(Highlights highlights) {
        String sql = "INSERT INTO weekly_highlights (top_goalscorer, top_assists, most_saves, most_interceptions, most_yellow_cards, most_red_cards) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, highlights.getTopGoalscorer());
            stmt.setString(2, highlights.getTopAssists());
            stmt.setString(3, highlights.getMostSaves());
            stmt.setString(4, highlights.getMostInterceptions());
            stmt.setString(5, highlights.getMostYellowCards());
            stmt.setString(6, highlights.getMostRedCards());
            stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Highlights getLatestHighlights() {
        String sql = "SELECT * FROM weekly_highlights ORDER BY updated_at DESC LIMIT 1";

        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                Highlights h = new Highlights();
                h.setId(rs.getInt("id"));
                h.setTopGoalscorer(rs.getString("top_goalscorer"));
                h.setTopAssists(rs.getString("top_assists"));
                h.setMostSaves(rs.getString("most_saves"));
                h.setMostInterceptions(rs.getString("most_interceptions"));
                h.setMostYellowCards(rs.getString("most_yellow_cards"));
                h.setMostRedCards(rs.getString("most_red_cards"));
                return h;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
