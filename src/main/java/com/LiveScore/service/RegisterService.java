package com.LiveScore.service;

import java.sql.*;

import com.LiveScore.config.DbConfig;
import com.LiveScore.model.UserModel;

public class RegisterService {

    public boolean registerUser(UserModel user) {
        String insertQuery = "INSERT INTO user (first_name, last_name, username, email, password, role_id, profile_picture) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection dbConn = DbConfig.getConnection();
             PreparedStatement stmt = dbConn.prepareStatement(insertQuery)) {

            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getUsername());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPassword()); // already encrypted before passing
            stmt.setInt(6, user.getRoleId());
            stmt.setString(7, user.getProfilePicture()); // file name only (VARCHAR)

            return stmt.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean usernameExists(String username) {
        String query = "SELECT 1 FROM user WHERE username = ?";
        try (Connection dbConn = DbConfig.getConnection();
             PreparedStatement stmt = dbConn.prepareStatement(query)) {

            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean emailExists(String email) {
        String query = "SELECT 1 FROM user WHERE email = ?";
        try (Connection dbConn = DbConfig.getConnection();
             PreparedStatement stmt = dbConn.prepareStatement(query)) {

            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
