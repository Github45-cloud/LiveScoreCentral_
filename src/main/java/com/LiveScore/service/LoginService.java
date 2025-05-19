package com.LiveScore.service;

import java.sql.*;

import com.LiveScore.config.DbConfig;
import com.LiveScore.model.UserModel;
import com.LiveScore.util.PasswordUtil;

public class LoginService {
    private Connection dbConn;
    private boolean isConnectionError = false;

    public LoginService() {
        try {
            dbConn = DbConfig.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            isConnectionError = true;
        }
    }

    public boolean loginUser(UserModel userModel) {
        if (isConnectionError) {
            return false;
        }

        String query = "SELECT username, password FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, userModel.getUsername());
            ResultSet result = stmt.executeQuery();

            if (!result.next()) {
                return false; // User not found
            }

            return validatePassword(result, userModel);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean validatePassword(ResultSet result, UserModel userModel) throws SQLException {
        String dbUsername = result.getString("username");
        String dbEncryptedPassword = result.getString("password");
		return isConnectionError;

        
    }

    public Integer getRoleIdByUsername(String username) {
        String query = "SELECT role_id FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            if (result.next()) {
                return result.getInt("role_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserModel getUserByUsername(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new UserModel(
                    rs.getInt("user_id"),
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getString("username"),
                    rs.getString("email"),
                    rs.getString("password"),
                    rs.getInt("role_id"),
                    rs.getString("profile_picture") 
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean checkUsernameExists(String username) {
        String query = "SELECT 1 FROM user WHERE username = ?";
        try (PreparedStatement stmt = dbConn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet result = stmt.executeQuery();
            return result.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
