package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;//

/**
 * Represent a time slot during restaurant opening hours
 * 
 * @author Group 5
 *
 */
public class TimeSlot {
	/**
	 * Starting time of time slot
	 */
	private LocalTime startTime;
	
	/**
	 * Ending time of time slot
	 */
	private LocalTime endTime;
	
	/**
	 * Array list of reservations
	 */
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	//constructors
	
	/**
	 * Creates a time slot
	 * 
	 * @param startTime Starting time of the time slot
	 * @param endTime Ending time of the time slot
	 */
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	//getters
	
	/**
	 * Get the starting time of the time slot
	 * @return Starting time of the time slot
	 */
	public LocalTime getStartTime() {
		return startTime;
	}
	
	/**
	 * Get the ending time of time slot
	 * @return Ending time of the time slot
	 */
	public LocalTime getEndTime() {
		return endTime;
	}
	
	/**
	 * Get the list of reservations
	 * @return Array list of reservations
	 */
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	}
	
	//methods
	
	/**
	 * Add new reservation to the list of reservations
	 * @param reservation New reservation to be added
	 */
	public void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}
	
	/**
	 * Remove the reservations on specified date from the list of reservations
	 * @param date Date of reservation to be removed
	 */
	public void removeReservation(LocalDate date) {
		if (!reservationList.isEmpty()) {
			for (Reservation res : reservationList) {
				if (res.getDate().equals(date)) reservationList.remove(res);
				break;
			}
		}
	}
}
