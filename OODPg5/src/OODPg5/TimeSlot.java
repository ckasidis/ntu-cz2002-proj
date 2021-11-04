package OODPg5;

import java.time.LocalTime;

public class TimeSlot {
	private LocalTime startTime;
	private LocalTime endTime;
	private boolean isReserved;
	private Reservation reservation;
	
	//constructor
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		isReserved = false;
		reservation = null;
	}
	
	//getters
	public LocalTime getStartTime() {return startTime;}
	public LocalTime getEndTime() {return endTime;}
	public boolean getIsReserved() {return isReserved;}
	public Reservation getReservation() {return reservation;}
	
	//setters
	public void setIsReserved(boolean isReserved) {this.isReserved = isReserved;}
	public void setReservation(Reservation reservation) {this.reservation = reservation;}

}
