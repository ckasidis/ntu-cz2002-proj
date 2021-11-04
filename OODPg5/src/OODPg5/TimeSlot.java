package OODPg5;

import java.time.LocalTime;

public class TimeSlot {
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean isReserved;
	private Customer customer;
	
	//constructor
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		isReserved = false;
		customer = null;
	}
	
	//getters
	public LocalTime getStartTime() {return startTime;}
	public LocalTime getEndTime() {return endTime;}
	public boolean getIsReserved() {return isReserved;}
	public Customer getCustomer() {return customer;}
	
	//setters
	public void setIsReserved(boolean isReserved) {this.isReserved = isReserved;}
	public void setCustomer(Customer customer) {this.customer = customer;}

}
