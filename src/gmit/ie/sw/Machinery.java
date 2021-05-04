package gmit.ie.sw;

import java.util.ArrayList;
import java.util.UUID;

public class Machinery {

	private String name;
	private String age;
	private UUID machineID;
	private String clubID;
	private String vendor;
	private String valuation;
	private String lastServiceKM;
	private String lastServiceDate;
	private String nextServiceKM;
	private String currentKM;
	
	ArrayList<Machinery> fleetItems = new ArrayList<Machinery>();
	
	//null constructor
	public Machinery() {
	}
	
	public Machinery(String name, String age, UUID machineID ,String clubID, String vendor, String valuation, 
			String lastServiceKM, String lastServiceDate, String nextServiceKM, String currentKM) 
	{
		setName(name);
		setAge(age);
		setMachineID(machineID);
		setClubID(clubID);
		setVendor(vendor);
		setValuation(valuation);
		setLastServiceKM(lastServiceKM);
		setLastServiceDate(lastServiceDate);
		setNextServiceKM(nextServiceKM);
		setCurrentKM(currentKM);
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	// Using UUID as I found this to be useful in my last project (Data Representation & Querying).
	public UUID getMachineID() {
		return machineID;
	}
	public void setMachineID(UUID machineryId) {
		this.machineID = machineryId;
	}
	public String getClubID() {
		return clubID;
	}
	public void setClubID(String clubID) {
		this.clubID = clubID;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getValuation() {
		return valuation;
	}
	public void setValuation(String valuation) {
		this.valuation = valuation;
	}
	public String getLastServiceKM() {
		return lastServiceKM;
	}
	public void setLastServiceKM(String lastServiceKM) {
		this.lastServiceKM = lastServiceKM;
	}
	public String getLastServiceDate() {
		return lastServiceDate;
	}
	public void setLastServiceDate(String lastServiceDate) {
		this.lastServiceDate = lastServiceDate;
	}
	public String getNextServiceKM() {
		return nextServiceKM;
	}
	public void setNextServiceKM(String nextServiceKM) {
		this.nextServiceKM = nextServiceKM;
	}
	public String getCurrentKM() {
		return currentKM;
	}
	public void setCurrentKM(String currentKM) {
		this.currentKM = currentKM;
	}

	@Override
	public String toString() {
		return "Machinery [name=" + name + ", age=" + age + ", machineID=" + machineID + ", clubID=" + clubID
				+ ", vendor=" + vendor + ", valuation=" + valuation + ", lastServiceKM=" + lastServiceKM
				+ ", lastServiceDate=" + lastServiceDate + ", nextServiceKM=" + nextServiceKM + ", currentKM="
				+ currentKM + "]";
	}
}
