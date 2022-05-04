

package com.revature.daos;

import java.util.ArrayList;


import com.revature.models.User;

//This interface will lay out every method that I want my RoleDAO to have
public interface UserDAOInterface {

	//ctrl + shift + o to import multiple Classes at once
	
	//get all roles
	ArrayList<User> getUsers();
	
	//take in an int, and get the Role which has a role_id == that int
	User getUserById(int id);
	
	//take in an int and a String, and change the appropriate role's salary using those arguments
	//void updateUserEmail(String name, String email);
	
}
