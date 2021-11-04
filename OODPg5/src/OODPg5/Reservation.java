package OODPg5;

public class Reservation {
	private String date;
	private String time;
	private int numOfPax;
	private String name;
	private int contactNo;
	
	//Constructors
	public Reservation(String date, String time, int numOfPax, String name, int contactNo) {
		this.date = date;
		this.time = time;
		this.numOfPax = numOfPax;
		this.name = name;
		this.contactNo = contactNo;
	}

	//Getters and Setters
	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	public int getNumOfPax() {
		return numOfPax;
	}

	public String getName() {
		return name;
	}

	public int getContactNo() {
		return contactNo;
	}

}
