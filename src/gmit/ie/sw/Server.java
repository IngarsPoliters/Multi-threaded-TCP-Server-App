package gmit.ie.sw;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.UUID;

public class Server extends Thread {

	private Socket conn;
	private int id;
	ObjectOutputStream out;
	ObjectInputStream in;
	String storedUser = "default";
	String storedPass = "default";
	String regid = "default";
	String option;
	String response;
	String message;
	String username;
	String password;
	boolean exist = false;
	int machineId;
	StringBuilder sb = new StringBuilder();

	UserItems users;
	FleetItems fleet;

	public Server(Socket c, int i, UserItems u, FleetItems f) throws IOException {
		conn = c;
		id = i;
		users = u;
		fleet = f;
	}

	public void run() {
		// System.out.println(user);

		try {
			out = new ObjectOutputStream(conn.getOutputStream());
			in = new ObjectInputStream(conn.getInputStream());

			// Server Conversation starts
			do {

				out.writeObject("Press 1 for Login or 2 for Register");
				out.flush();

				option = (String) in.readObject();

				// User Login
				if (option.equalsIgnoreCase("1")) {

					out.writeObject("Please enter the BusinessName and ID");
					out.flush();

					username = (String) in.readObject();
					password = (String) in.readObject();

					// checking if the username and password exists in users list
					exist = users.checkUsers(username, password);
					if (!exist) {
						System.out.println("User does not exist");
						response = "FAIL";
						out.writeObject("FAIL");
						out.flush();
					} else {
						System.out.println("User exists");
						response = "OK";
						out.writeObject("Welcome " + username);
						out.flush();
					}

					// user register
				} else if (option.equalsIgnoreCase("2")) {
					// name
					out.writeObject("Please enter name");
					out.flush();
					message = (String) in.readObject();
					String name = message; // stored name of user

					// Business ID
					out.writeObject("Please enter your Business ID");
					out.flush();
					message = (String) in.readObject();
					String id = message;

					// email
					out.writeObject("Please enter your email");
					out.flush();
					message = (String) in.readObject();
					String email = message;

					// The same business name and id can only be registered once)
					// checking if the username and id exist in the list
					exist = users.checkUsers(name, id);
					if (!exist) {
						out.writeObject("FALSE");
						out.flush();
						// user is registerd and added to the list and file
						users.addUser(name, id, email);
					} else {
						out.writeObject("TRUE");
						out.flush();
					}
					response = "FAIL";
				}

			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));

			// Logged in
			// Main Menu

			do {
				out.writeObject("========Main Menu========\n" + "1) Add new Machinery\n"
						+ "2) Update Machinery Records(NOT WORKING)\n"
						+ "3) Update the current machinery kilometer reading\n"
						+ "4) Search all fleet items that are currently within 1000Km of their next service\n"
						+ "5) Remove a fleet item from the fleet\n" + "6) Register a fleet item for Sale\n"
						+ "7) Search all fleet items for Sale\n" + "8) Exit System");
				out.flush();
				option = (String) in.readObject();

				// test to see if Current id is present
				// System.out.println("This is the CurrentID: "+ password);

				// add new machinery
				if (option.equalsIgnoreCase("1")) {

					// generating unique id
					UUID machineryId = UUID.randomUUID();

					// machine name
					out.writeObject("Enter Machine Name");
					out.flush();
					String name = (String) in.readObject();

					// machine age
					out.writeObject("Enter Machine Age");
					out.flush();
					String age = (String) in.readObject();

					// vendor
					out.writeObject("Enter Vendor");
					out.flush();
					String vendor = (String) in.readObject();

					// valuation
					out.writeObject("Enter valuation");
					out.flush();
					String valuation = (String) in.readObject();

					// lastServiceKM
					out.writeObject("Enter Last service in KM");
					out.flush();
					String lastServiceKM = (String) in.readObject();

					// lastServiceDate
					out.writeObject("Enter Last service date");
					out.flush();
					String lastServiceDate = (String) in.readObject();

					// nextServiceKM
					out.writeObject("Enter Next service in KM");
					out.flush();
					String nextServiceKM = (String) in.readObject();

					// currentKM
					out.writeObject("Enter Current Kilometers");
					out.flush();
					String currentKM = (String) in.readObject();

					// add machinery to fleet
					fleet.addMachine(name, age, machineryId, password, vendor, valuation, lastServiceKM,
							lastServiceDate, nextServiceKM, currentKM);

					response = (String) in.readObject();

				} // end if option 1
					// test to see if machine is in array
					// System.out.println(machinery.getFleetItems().toString());

				// update machinery service records
				if (option.equalsIgnoreCase("2")) {
					// Has not been clearly explained on how to approach this.
					// Who is the Service Agent ? I need to Append machinery service record and not
					// add to it.
					// I can update Kilometers here, and also asked to update current KM reading on
					// fleet item in option 3.
					// What is "Description of the Server" ?
					/**
					 * Additionally, the following items of the machine profile should also be
					 * updated: Last Service in Kilometres Next Service in Kilometres
					 * 
					 * This can be done but also has to be done in option 3
					 */

					response = (String) in.readObject();
				}

				// Update the current kilometer reading on a fleet item.
				if (option.equalsIgnoreCase("3")) {
					// create temp list to store fleetitems for this user
					ArrayList<Machinery> temp;
					// requesting fleet items matching the business id
					temp = fleet.getFleetItems(password);
					System.out.println(temp.size());
					
					//Checking if returned fleet is not empty
					if (temp.size() != 0) {
						//send to client TRUE to start if statement
						out.writeObject("TRUE");
						out.flush();
						
						//send message to client to select machinery
						out.writeObject("Please select machinery to update ( 1 - " + temp.size() + " )");
						out.flush();

						for (int i = 0; i < temp.size(); i++) {
							sb.append(i + 1 + ") ");
							sb.append("Name: " + temp.get(i).getName());
							sb.append(" Age: " + temp.get(i).getAge() + "\n");
						}
						String fleet = sb.toString();

						out.writeObject(fleet);
						out.flush();
						
						option = (String)in.readObject();
						
						
						
					} else {
						//Send to client FLASE if no machinery in the list
						out.writeObject("FALSE");
						out.flush();
						
						out.writeObject("Machinery List is empty, Please add new machinery");
						out.flush();
						response = "OK";
						sleep(3000);// putting thread to sleep for user to read message in time 
					}
				}

				// Search all fleet items that are currently within 1000Km of their next
				// service.
				if (option.equalsIgnoreCase("4")) {

				}

				// Remove a fleet item from the fleet.
				if (option.equalsIgnoreCase("5")) {

				}
				// Register a fleet item for Sale
				if (option.equalsIgnoreCase("6")) {

				}

				// Search all fleet items for Sale
				if (option.equalsIgnoreCase("7")) {

				}
				// Exit to terminate thread
				if (option.equalsIgnoreCase("8")) {
					response = (String) in.readObject();
				}

			} while (response.equalsIgnoreCase("OK"));

		} // end Try
		catch (Exception e) {

		}
	}// end Run

	// Main
	public static void main(String[] args) throws IOException {
		ServerSocket listener;
		Socket connection;
		int id = 0;

		UserItems users = new UserItems();
		// System.out.println(users.getUsers().toString());
		FleetItems fleet = new FleetItems();

		try {
			listener = new ServerSocket(25000, 10);

			while (true)// while server is running
			{
				System.out.println("Listening for a connection");
				connection = listener.accept();
				System.out.println("Received Connection from " + connection.getInetAddress());

				Server th = new Server(connection, id, users, fleet);
				id++;
				th.start();
			}
		} catch (Exception e) {

		}

	}// end Main

}
