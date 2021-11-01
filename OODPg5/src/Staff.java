
public class Staff {
	private String name;
	private String gender;
	private int employeeID;
	private String jobTitle;
	
	//Constructors
	public Staff(String name, String gender, int employeeID, String jobTitle) {
		this.setName(name);
		this.setGender(gender);
		this.setEmployeeID(employeeID);
		this.setJobTitle(jobTitle);
	}

	//Getters and Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	

}