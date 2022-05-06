
package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.models.Request;
import com.revature.models.RequestDTO;
import com.revature.models.Status;
import com.revature.models.Type;
import com.revature.models.User;
import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

//DAO stands for Data Access Object - it's the layer of classes that directly interact with our database
public class RequestDAO implements RequestDAOInterface{

	//Instantiate a RoleDAO object so that we can use one of it's methods in the getEmployees() method
	UserDAOB uDAO = new UserDAOB();
	StatusDAO sDAO = new StatusDAO();
	TypeDAO tDAO = new TypeDAO();
	//This TEMPORARY method will return the employees from the database
	//In the future, this method body will actually be communicating directly to the database
	public ArrayList<Request> getRequests(){
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//a String that will represent our SQL statement
			String sql = "select * from ers_reimbursement;";
			
			//a Statement object to execute our query 
			Statement s = conn.createStatement();
			
			//execute our query into a ResultSet object, which will hold all the data 
			//executeQuery() is what actually queries the database! Then we put the records into a ResultSet
			ResultSet rs = s.executeQuery(sql);
			
			//Instantiate an ArrayList to put our Employee objects into
			ArrayList<Request> requestList = new ArrayList<>();
			
			//use rs.next() in a while loop to create Employee objects and populate our ArrayList with them.
			//remember, the ResultSet is what's holding our data. We need to turn it into something Java can read (objects)
			while(rs.next()) {
				//Create a new Employee object from each record in the ResultSet
				//we're using the all args constructor of Employee to fill in new Employee objects with DB data
				
				Request req = new Request(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getString("reimb_submitted"),
						null,
						null,
						null
					
						
						
							);
						//there is no JDBC method for getRole... we'll add the Role object in below
						//this is an extra step we have to take because in the DB, the role_id_fk is an int
						//but we need a Role object here
					int userFK = rs.getInt("reimb_author_fk");
		
					User u = uDAO.getUserById(userFK);
					req.setUser(u);
					requestList.add(req);
					
					int statusFK = rs.getInt("reimb_status_id_fk");
					
					Status st = sDAO.getStatusById(statusFK);
					req.setStatus(st);
					requestList.add(req);
				
					
					int typeFK = rs.getInt("reimb_type_id_fk");
					Type t = tDAO.getTypeById(typeFK);
					req.setType(t);
					requestList.add(req);
			
				
				//we need to get the role of each employee somehow...
				//we need to use the DAO method for getRoleById from the RoleDAO
				
				
				
				
				
			

			}
				
				//get a Role object from the RoleDAO
				
			
				
				
				//use the SETTER of the Employee class to set the Role object to the one we got from the DB above.
				
				//thanks to this setter, our Employee objects can be FULLY initialized (every variable has a value)
				
			
			//once the while loop ends (when rs.next() == false), return the ArrayList
			return requestList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting all Request!");
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	
	
	@Override
	public void insertRequest(RequestDTO request) {
	
		try(Connection conn = ConnectionUtil.getConnection()){
		
		//First we need our SQL String that represents the INSERT statement we'll send to the DB
		//Again, there are variables in this statement, that we can fill out thanks to PreparedStatement
		String sql = "insert into ers_reimbursement (reimb_amount, reimb_submitted, reimb_author_fk, reimb_status_id_fk, reimb_type_id_fk)"
				+ "values (?, ?, ?,?,?);";
				
		//Instantiate a PreparedStatement to fill in the variables of our initial SQL String
		PreparedStatement ps = conn.prepareStatement(sql);
		
		//fill in the values of our variables using ps.setXYZ()
		ps.setInt(1, request.getReimb_amount());
		ps.setString(2, request.getReimb_submitted());
		ps.setInt(3, request.getUser());
		ps.setInt(4, request.getStatus());
		ps.setInt(5, request.getType());



		
	
		//note how the DB role_id is an int, but in Java, Employees have a Role OBJECT
		//this is my workaround of choice... have the user input the id of the desired role when inserting the user data
		
		//Execute the Update!! (the method is called executeUpdate(), but it's for INSERTS, UPDATES, and DELETES)
		ps.executeUpdate();
		
		//Tell the user the insert was successful
		System.out.println("Request " + request.getReimb_amount()+ " added. Welcome aboard agagagagaga!");
			
		} catch (SQLException e) {
			System.out.println("Something went wrong inserting Employee!");
			e.printStackTrace();
		}
		
	} 
	
	/*
	@Override
	public void updateRoleSalary(String title, int salary) {
	
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//write out our SQL UPDATE command
			String sql = "update roles set role_salary = ? where role_title = ?";
			
			//create our PreparedStatement
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//input the appropriate values into our PreparedStatement
			ps.setInt(1, salary); //update the first ? with the int salary from the method arguments
			ps.setString(2, title); //update the second ? with the String title from the method arguments
			
			//execute the update!!
			ps.executeUpdate();
			
			//tell the user that the update was successful
			System.out.println(title + " salary has been updated to: " + salary);
			
		} catch (SQLException e) {
			System.out.println("Couldn't update :(");
			e.printStackTrace();
		}
		
	}

	/*

	//We want a method that can take in a Role title, and return all Employees with that Role
	@Override
	public ArrayList<Request> getRequestByEmail(String email) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//We need a SQL String with a JOIN
			//We need to join employees on roles in order to access the role_title column from the roles table
			//since I want to get employees by their role title, I need access to the data in both tables
			String sql = "select * from employees inner join roles "
					+ "on role_id = role_id_fk where role_title = ?;";
			
			//we have a variable in the SQL statement, so we need a PreparedStatement to fill it in
			PreparedStatement ps = conn.prepareStatement(sql);
			
			//now we just need to input the variable value
			ps.setString(1, email);
			
			//Execute the query into a ResultSet object
			ResultSet rs = ps.executeQuery();
			
			//Instantiate an empty ArrayList that we'll fill with the data from the ResultSet
			ArrayList<Request> employeeList = new ArrayList<>();
			
			//while there are records remaining in the ResultSet...
			while(rs.next()) {
				
				//create new Employee objects based on the data, and fill in the ArrayList
				Request r = new Request(
						rs.getInt("employee_id"),
						rs.getString("first_name"),
						rs.getString("last_name"),
						null
						);
				
				//get the foreign key from the Employees table to use in our getRoleById() method
				int roleFK = rs.getInt("role_id_fk");
				
				User u = rDAO.getUserById(roleFK);
				
				//fill in the previously null Role variable in this new Employee object (with the setter!)
				u.setRole(r);
				
				//fill in the employeeList with each while loop until eventually rs.next() == false.
				requestList.add(e);
			}
			
			return requestList;
			
		} catch (SQLException e) {
			System.out.println("Something went wrong selecting employees by ID");
			e.printStackTrace();
		}
		
		return null;
	}


	

	@Override
	public void removeRequest(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			//SQL String that we want to send to the DB
			String sql = "delete from employees where employee_id = ?;";
			
			//instantiate our PreparedStatement to fill in the variable
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			//ps.executeUpdate() to send our delete to the DB
			ps.executeUpdate();
			
			//let the user know that the dreams of their former employee have been crushed
			System.out.println("Get outta here, Request #" + id);
			
		} catch (SQLException e) {
			System.out.println("YOU CAN'T FIRE ME MY FATHER WILL SUE");
			e.printStackTrace();
		}
		
	}
	
	
	
	
	//Ben is leaving this unimplemented... Check RoleDAO for findById functionality
	@Override
	public Request getRequestById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	*/

}
