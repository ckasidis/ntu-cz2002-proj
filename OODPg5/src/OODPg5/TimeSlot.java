package OODPg5;

import java.time.LocalTime;

import java.time.LocalDate;//


public class TimeSlot {
	private LocalTime startTime;
	private LocalTime endTime;
	private LocalDate date;//
	private Customer customer;
	
	//constructor
	public TimeSlot(LocalTime startTime, LocalTime endTime, LocalDate date) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.date = date; //
		customer = null;
		
	}
	
	//getters
	public LocalTime getStartTime() {return startTime;}
	public LocalTime getEndTime() {return endTime;}
	public LocalDate getDate() {return date;} //
	public Customer getCustomer() {return customer;}
	
	//setters
	public void setCustomer(Customer customer) {this.customer = customer;}

}
