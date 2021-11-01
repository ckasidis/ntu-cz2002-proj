
public class Staff {
	private String name;
	private String gender;
	private int employeeID;
	private String jobTitle;
	
	//Constructors
	public Staff(String name, String gender, int employeeID, String jobTitle) {
		this.name  = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int getEmployeeID() {
		return employeeID;
	}


	public String getJobTitle() {
		return jobTitle;
	}

}