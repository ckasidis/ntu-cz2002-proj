package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.time.LocalDate;//


public class TimeSlot {
	private LocalTime startTime;
	private LocalTime endTime;
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	
	//constructor
	public TimeSlot(LocalTime startTime, LocalTime endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	//getters
	public LocalTime getStartTime() {
		return startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public ArrayList<Reservation> getReservationList() {
		return reservationList;
	} //
	
	//add reservation
	public void addReservation(Reservation reservation) {
		reservationList.add(reservation);
	}
	
	//remove reservation
	public void removeReservation(LocalDate date) {
		if (!reservationList.isEmpty()) {
			for (Reservation res : reservationList) {
				if (res.getDate().equals(date)) reservationList.remove(res);
			}
		}
	}
}
