package gmit.ie.sw;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	ObjectOutputStream out;

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		Socket connection;
		ObjectOutputStream out;
		ObjectInputStream in;
		String message;
		String response = "";
		String option;
		int machineId;

		try {
			connection = new Socket("127.0.0.1", 25000);
			out = new ObjectOutputStream(connection.getOutputStream());
			in = new ObjectInputStream(connection.getInputStream());

			do {
				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();

				out.writeObject(option);
				out.flush();

				System.out.println(option);

				if (option.equalsIgnoreCase("1")) {
					System.out.println("Login");
					// user login
					message = (String) in.readObject();
					System.out.println(message);

					// Username
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Password
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					response = (String) in.readObject();
					System.out.println(response);

				} else if (option.equalsIgnoreCase("2")) {
					System.out.println("Register");
					// user register
					// name
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// Business ID
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// email
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					message = (String) in.readObject();
					if (message.equalsIgnoreCase("TRUE")) {
						System.out.println("Cannot register user.\nUser already exists");
					} else if (message.equalsIgnoreCase("FALSE")) {
						System.out.println("Registered");
					}

					response = "FAIL";
					// System.out.println(response);
				}

			} while (option.equalsIgnoreCase("2") || response.equalsIgnoreCase("FAIL"));

			System.out.println("Exiting Login");

			// Logged in
			do {
				message = (String) in.readObject();
				System.out.println(message);
				option = input.nextLine();
				out.writeObject(option);
				out.flush();

				// add new machinery
				if (option.equalsIgnoreCase("1")) {

					// enter Machine Name
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Machine Age
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Vendor
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Valuation
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Last Service in KM
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Last Service Date
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Next Service in KM
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// enter Current KM
					message = (String) in.readObject();
					System.out.println(message);
					response = input.nextLine();
					out.writeObject(response);
					out.flush();

					// send OK to repeat do while loop
					out.writeObject("OK");
					out.flush();
					response = "OK";
				} // end if option 1

				if (option.equalsIgnoreCase("2")) {
					out.writeObject("OK");
					out.flush();
					response = "OK";
				}
				
				// Update the current kilometer reading on a fleet item.
				if (option.equalsIgnoreCase("3")) {

					response = (String) in.readObject();

					if (response.equalsIgnoreCase("TRUE")) {
						// message to select machinery
						message = (String) in.readObject();
						System.out.println(message);

						// machinery list
						message = (String) in.readObject();
						System.out.println(message);
						
						//send back the response to server
						response = input.nextLine();
						out.writeObject(response);
						out.flush();
						
						
					}else {
						message = (String)in.readObject();
						System.out.println(message);
						response = "OK";
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

				// Exit to terminate program
				if (option.equalsIgnoreCase("8")) {
					out.writeObject("EXIT");
					out.flush();
					response = "EXIT";
				}

			} while (response.equalsIgnoreCase("OK"));

		} catch (Exception e) {
			System.out.println("Error");
		}

	}// end Main

}
