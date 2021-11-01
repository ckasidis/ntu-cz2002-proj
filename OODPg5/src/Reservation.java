
public class Reservation {
	private String date;
	private String time;
	private int numOfPax;
	private String name;
	private int contactNo;
	
	//Constructors
	public Reservation(String date, String time, int numOfPax, String name, int contactNo) {
		this.setDate(date);
		this.setTime(time);
		this.setNumOfPax(numOfPax);
		this.setName(name);
		this.setContactNo(contactNo);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNumOfPax() {
		return numOfPax;
	}

	public void setNumOfPax(int numOfPax) {
		this.numOfPax = numOfPax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getContactNo() {
		return contactNo;
	}

	public void setContactNo(int contactNo) {
		this.contactNo = contactNo;
	}
	
}
