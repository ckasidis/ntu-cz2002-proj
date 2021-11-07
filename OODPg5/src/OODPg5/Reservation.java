package OODPg5;

import java.time.LocalTime;
import java.time.LocalDate;

public class Reservation {
	private Customer customer;
	private LocalTime startTime;
	private LocalDate date;
	private int numOfPax;
	
	//constructor
	public Reservation(Customer customer, LocalTime startTime, int numOfPax, LocalDate date) {
		this.customer = customer;
		this.startTime = startTime;
		this.numOfPax = numOfPax;
		this.date = date;
	}

	//getters
	public Customer getCustomer() {
		return customer;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public int getNumOfPax() {
		return numOfPax;
	}
	public LocalDate getDate() {
		return date;
	}
}
