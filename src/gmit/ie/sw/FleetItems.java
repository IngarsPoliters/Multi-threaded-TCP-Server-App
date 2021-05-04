package gmit.ie.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

public class FleetItems {
	
	ArrayList<Machinery> fleet = new ArrayList<Machinery>();
	
	public FleetItems() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("FleetItems.txt"));
		String line = null;
		
		while((line = br.readLine()) != null) {
			String[] values = line.split(",");
			//convert ID from string back to UUID
			UUID uid = UUID.fromString(values[2]);
			
			fleet.add(new Machinery(values[0],values[1],uid,values[3],values[4],values[5],values[6],
					values[7],values[8],values[9]));// add values here
		}
		br.close();//close the file after populating list 
	}
	
	public synchronized void addMachine(String name, String age, UUID machineID ,String clubID, String vendor, String valuation, 
			String lastServiceKM, String lastServiceDate, String nextServiceKM, String currentKM) throws IOException 
	{
		FileWriter writer = new FileWriter("FleetItems.txt", true);
		// add machinery to the list
		fleet.add(new Machinery(name,age,machineID,clubID,vendor,valuation,lastServiceKM,lastServiceDate,nextServiceKM,currentKM));
		
		//add new machine to fleet items file
		writer.write(name+","+age+","+machineID+","+clubID+","+vendor+","+valuation+","+lastServiceKM+","+
					lastServiceDate+","+nextServiceKM+","+currentKM+ "\n");
		writer.close();
	}
	
	public synchronized ArrayList<Machinery> getFleetItems(String id) {
		ArrayList<Machinery> temp = new ArrayList<Machinery>();
		for(int i = 0; i < fleet.size() ; i++ ) {
			if(fleet.get(i).getClubID().equalsIgnoreCase(id) ) {
				//System.out.println(fleet.get(i).toString());
				temp.add(fleet.get(i));
			}
		}
		System.out.println(temp.toString());
		return temp;
	}
	
	
	
	

}
