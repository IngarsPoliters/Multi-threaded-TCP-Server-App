package gmit.ie.sw;

import java.util.ArrayList;

public class Users {

	
	private String name, id, email ;
	
	public Users(String name, String id, String email) {
		setName(name);
		setId(id);
		setEmail(email);
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "___________________________________________"
			  +"\nBusiness Details: "+ name
			  +"\n_________________________________________"
			  +"\nID: "+ id
			  +"\nEmail: "+ email;
	}
}
