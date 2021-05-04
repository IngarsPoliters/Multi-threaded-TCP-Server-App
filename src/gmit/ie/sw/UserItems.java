package gmit.ie.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserItems {
	// ArrayList to store all the users from registeredUsers file 
	ArrayList<Users> users = new ArrayList<Users>();

	
	public UserItems() throws IOException {
		// Reading in all registered users and storing them to users array list
		BufferedReader br = new BufferedReader(new FileReader("registeredUsers.txt"));
		String line = null;

		while ((line = br.readLine()) != null) {
			String[] values = line.split(",");
			// each user is stored in users ArrayList
			users.add(new Users(values[0], values[1], values[2]));
		}
		br.close();// close the file 
	}
	
	
	public synchronized void addUser(String name, String id , String email) throws IOException {
		FileWriter writer = new FileWriter("registeredUsers.txt", true);
		// add users to the list
		users.add(new Users(name, id ,email));
		
		// add new user to users file
		writer.write(name + "," + id + "," + email + "\n");
		writer.close();
	}
	
	public synchronized boolean checkUsers(String name, String id) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getName().equals(name) && users.get(i).getId().equals(id)) {
				return true;
			}
		}
		return false;
	}	
	
	public ArrayList<Users> getUsers() {
		return users;
	}

}
