package OODPg5;

import java.time.LocalTime;
import java.time.LocalDate;
/**
 * Represents a reservation placed by the customer of the restaurant
 * @author Group 5
 *
 */
public class Reservation {
	/**
	 * Customer of the restaurant
	 */
	private Customer customer;
	
	/**
	 * Starting time of the reservation
	 */
	private LocalTime startTime;
	
	/**
	 * Date of the reservation
	 */
	private LocalDate date;
	
	/**
	 * Number of persons reservation is booked for
	 */
	private int numOfPax;
	
	//constructors
	
	/**
	 * Creates a reservation
	 * @param customer Customer booking the reservation
	 * @param startTime Starting time of the reservation
	 * @param numOfPax Number of persons reservation is booked for
	 * @param date Date of the reservation
	 */
	public Reservation(Customer customer, LocalTime startTime, int numOfPax, LocalDate date) {
		this.customer = customer;
		this.startTime = startTime;
		this.numOfPax = numOfPax;
		this.date = date;
	}

	//getters
	
	/**
	 * Get the customer booking the reservation
	 * @return Customer booking the reservation
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get the starting time of the reservation
	 * @return Starting time of the reservation
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	
	/**
	 * Get the date of the reservation
	 * @return Date of the reservation
	 */
	public LocalDate getDate() {
		return date;
	}
	
	/**
	 * Get the number of persons reservation is booked for
	 * @return Number of persons reservation is booked for
	 */
	public int getNumOfPax() {
		return numOfPax;
	}

}
