package OODPg5;

import java.time.LocalTime;

public class Reservation {
	private Customer customer;
	private LocalTime startTime;
	private int numOfPax;
	
	//constructor
	public Reservation(Customer customer, LocalTime startTime, int numOfPax) {
		this.customer = customer;
		this.startTime = startTime;
		this.numOfPax = numOfPax;
	}

	//getters
	public Customer getCustomer() {return customer;}
	public LocalTime getStartTime() {return startTime;}
	public int getNumOfPax() {return numOfPax;}

}
