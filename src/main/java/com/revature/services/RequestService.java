package com.revature.services;

import java.util.ArrayList;

import com.revature.daos.RequestDAO;
import com.revature.models.Request;

//The service layer contains additional business logic needed to process requests/responses
//It probably won't be doing much here, but it comes into play more with things like login
public class RequestService {

	//We need an EmployeeDAO Class so that we can call our getEmployees() method.
	RequestDAO rDAO = new RequestDAO();
	
	//this method gets all employees from the DAO (we will call this method from the Handler in the controller layer).
	//this method will be pretty simple, since we don't really need to process any data - just send it around
	public ArrayList<Request> getRequests(){
		
		//get the List of employees from the DAO
		ArrayList<Request> requests = rDAO.getRequests();
		
		//return that List of employees
		return requests;
		
	}
	
}
