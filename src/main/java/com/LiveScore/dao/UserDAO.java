package com.LiveScore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.LiveScore.model.UserModel;
import com.LiveScore.config.DbConfig;

public class UserDAO {

	public boolean insertUser(UserModel user) {
	    String sql = "INSERT INTO user (username, first_name, last_name, email, password, role_id, profile_picture) " +
	                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = DbConfig.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, user.getUsername());
	        stmt.setString(2, user.getFirstName());
	        stmt.setString(3, user.getLastName());
	        stmt.setString(4, user.getEmail());
	        stmt.setString(5, user.getPassword());
	        stmt.setInt(6, user.getRoleId());
	        stmt.setString(7, user.getProfilePicture());  // file name/path

	        return stmt.executeUpdate() > 0;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}