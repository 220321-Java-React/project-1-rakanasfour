
package com.revature.daos;

import java.util.ArrayList;


import com.revature.models.Status;

//This interface will lay out every method that I want my RoleDAO to have
public interface StatusDAOInterface {

	//ctrl + shift + o to import multiple Classes at once
	
	//get all roles
	ArrayList<Status> getStatuses();
	Status getStatusById(int id);

	
}
