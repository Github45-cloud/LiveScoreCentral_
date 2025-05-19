package com.LiveScore.dao;

import com.LiveScore.model.FeedbackModel;
import com.LiveScore.config.DbConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    public void saveFeedback(FeedbackModel feedback) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO feedback (name, email, message) VALUES (?, ?, ?)";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, feedback.getName());
            stmt.setString(2, feedback.getEmail());
            stmt.setString(3, feedback.getMessage());
            stmt.executeUpdate();
        }
    }

    public List<FeedbackModel> getAllFeedback() throws SQLException, ClassNotFoundException {
        List<FeedbackModel> feedbackList = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY submitted_at DESC";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FeedbackModel f = new FeedbackModel();
                f.setFeedbackId(rs.getInt("feedback_id"));
                f.setName(rs.getString("name"));
                f.setEmail(rs.getString("email"));
                f.setMessage(rs.getString("message"));
                f.setSubmittedAt(rs.getTimestamp("submitted_at"));
                feedbackList.add(f);
            }
        }
        return feedbackList;
    }
    public void deleteFeedbackById(int feedbackId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";
        try (Connection conn = DbConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, feedbackId);
            stmt.executeUpdate();
        }
    }

}
