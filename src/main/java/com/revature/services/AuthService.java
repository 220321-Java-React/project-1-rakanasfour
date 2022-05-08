package com.revature.services;

import com.revature.daos.UserDAO;
import com.revature.models.Request;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;

//remember, I'm semi-hardcoding here. You'll need to get this employee data from the DAO & DB
public class AuthService {

	//Typically, you'll want to validate username/password here in the service... 
	//by calling some DAO method that gets employees where username = ? and password = ?
	
	public User login(String username, String password) {
		
		//UserDAOB userDAOB = new UserDAOB();
		UserDAO userDAO = new UserDAO();
		
		User u = userDAO.login(username, password);
		
		return u;
		
	}
		//we would call the DAO method here, and use its results in the if/else below
		//the DAO method would return whatever gets found in the DB (records with matching username/pass)
		
		//this is hardcoding - telling Java exactly what we want the username/password to be
		//if(username.equals(userDAOB.getUsers()) && password.equals(userDAOB.getUsers())) {
/*
		if(username.equals("user") && password.equals("password")) {
			System.out.println(username);
		
			
			//empty Role object (no args constructor) just for simplicity here
			User u = new User();
			
			Status s = new Status();
			Type t = new Type();
	

			
			return new Request(1, 100, "rakan",u,s,t);
			
		} else {
			return null;
		}
		*/
	

}
