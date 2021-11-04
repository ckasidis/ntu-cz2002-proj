package OODPg5;

import java.time.LocalTime;

public class TimeSlot {
	private LocalTime startTime;
	private LocalTime endTime;
	private Customer customer;
	
	//constructor
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		customer = null;
	}
	
	//getters
	public LocalTime getStartTime() {return startTime;}
	public LocalTime getEndTime() {return endTime;}
	public Customer getCustomer() {return customer;}
	
	//setters
	public void setCustomer(Customer customer) {this.customer = customer;}

}
