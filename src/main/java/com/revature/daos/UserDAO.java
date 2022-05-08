package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.LoginDTO;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

//This is a (sort of fake) DAO class that deals with user data
//In P0, you should have a user table that keeps track of users and their password
//But here, I'm going to hardcode a "correct" username and password
//You will have to figure out how to check the user inputted credentials against the database
public class UserDAO {

	
	public User login(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){
		
		String sql = "select * from ers_users where ers_username = ? and ers_password = ?;";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ResultSet rs = ps.executeQuery();
		while  (rs.next()) {
			User user = new User();
			
					user.setErs_username(username);
					user.setErs_password(password);
					return user;
					
		}
		
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
		}
	}

	/*
	//This method will return true in the case of successful login, otherwise it will return false
	public boolean login(String username, String password) {
		
		if(username.equals("user") && password.equals("password")) {
			return true;
		}
		
		return false;
		
	}
	
	
	}
	*/
	
	
	
			
			
			
		
	

		
	
	//Ok Ben hardcoded the credentials... thanks a lot...
	//How might I go about actually checking the DB for username/password?
	
	//You'll need a table of users that store usernames and passwords
	//This DAO method should use the inputted username and password in a select statement
	//Might I suggest select * from users where username = ? and password = ?;
	//If a record comes back, there IS a username and password matching what the user sent in
	//If "null" comes back, there is no username and password pair matching what the user sent in
	

