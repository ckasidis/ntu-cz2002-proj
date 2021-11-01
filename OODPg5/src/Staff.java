
public class Staff {
	private String name;
	private String gender;
	private int employeeID;
	private String jobTitle;
	
	//Constructors
	public Staff(String name, String gender, int employeeID, String jobTitle) {
		this.name = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}
	
	//Getters
	public String getName() {
		return name;
	}

}