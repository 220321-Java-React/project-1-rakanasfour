
package com.revature.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.daos.RequestDAO;
import com.revature.models.Request;
import com.revature.models.RequestDTO;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.services.RequestService;

import io.javalin.http.Handler;

//The Controller layer is where HTTP requests get sent after Javalin directs them
//It's in this layer that we'll parse any JSON into Java objects and vice versa
//We'll either be getting data from the service layer (which gets the data from the DAO)
//OR we'll be sending data that came from the webpage to the service layer (which sends the data to the DAO)
public class RequestController {
	
	//we need an EmployeeService object 
	RequestService rs = new RequestService();
	RequestDAO rDAO = new RequestDAO();
	User user = new User();
	Status status = new Status();
	Type type = new  Type();
	
	
	

	//this Handler will get the HTTP GET request for all employees, and send back the employees from the database
	public Handler getRequestsHandler = (ctx) -> {

		
		if(AuthController.ses != null) { //if there is an active session from the AuthController...
			
		//we need an ArrayList of Employee objects (which we'll get from the service layer)
		ArrayList<Request> Requests =rs.getRequests();
		
		//create a GSON object to convert our Java object into JSON (since we can only transfer JSON, not Java)
		Gson gson = new Gson();
		
		//use the JSON .toJson() method to turn our Java into JSON
		String JSONRequests = gson.toJson(Requests);
		
		//Give a HTTP response containing our JSON string back to the webpage (or wherever the HTTP request came from)
		ctx.result(JSONRequests); //.result() sends a response of data back
		ctx.status(200); //.status() sets the HTTP status code. 200 stands for "OK"
		
	} else { //if a session DOESN'T exist (user isn't logged in)
			ctx.status(400);
	}
		
	};
	

	//empty HttpSession object that will be filled upon successful login
	static HttpSession ses;
	
	//we need a loginHandler to handle login requests (which come to app.post("/login", xxx)
	public Handler setRequestsHandler = (ctx) -> {
		
		//with POST requests, we have some data coming in, which we access with ctx.body();
		//body??? it means the BODY of the request... which is just the data the user sent.
		String body = ctx.body();
		
		//create a new GSON object to make Java <-> JSON conversions.
		Gson gson = new Gson();
		
		//turn that JSON String directly into a LoginDTO object
		//remember, fromJson() is the method that takes JSON and turns it into some Java object
		RequestDTO rDTO = gson.fromJson(body, RequestDTO.class);
		/*
		rDTO.setUser(user);
		rDTO.setType(type);
		rDTO.setStatus(status);
		*/
		rs.insertRequest(rDTO);
		
		
			 
/*
		
		//control flow to determine what happens in the event of successful/unsuccessful login
		if(as.login(LDTO.getUsername(), LDTO.getPassword()) != null) {
			System.out.println(LDTO.getUsername());
			//if login is successful, create a user session so that they can access the application's functionalities
			ses = ctx.req.getSession(true); //we'll check if this is null in functionality that requires an active session
			
			
			//return a successful status code
			ctx.status(202); //202 stands for "accepted"
			
			//get our employee object as JSON
			//here, we're using .toJson() to take in the result of our successful login (which is an Employee object)
			String requestJSON = gson.toJson(as.login(LDTO.getUsername(), LDTO.getPassword()));
			//I want to add the receipt number 
			
			//send back our Employee JSON object
			ctx.result(requestJSON);
			
		} else {
			ctx.status(401); //401 stands for "unauthorized"
			System.out.println("heyo login failed");
		}
		*/
		
	};
	
	
	
}
