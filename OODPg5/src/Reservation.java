
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
	
}
