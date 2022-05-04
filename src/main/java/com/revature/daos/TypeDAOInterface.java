

package com.revature.daos;

import java.util.ArrayList;


import com.revature.models.Type;

//This interface will lay out every method that I want my RoleDAO to have
public interface TypeDAOInterface {

	//ctrl + shift + o to import multiple Classes at once
	
	//get all roles
	ArrayList<Type> getTypes();
	Type getTypeById(int id);
	

	
}
