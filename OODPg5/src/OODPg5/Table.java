package OODPg5;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
///
//import java.util.Calendar;
import java.time.LocalDate;
////

public class Table {
	private int tableNo;
	private int numOfSeats;
	private Customer customer;
	private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	
	//constructor
	public Table(int TableNo, int numOfSeats) {
		this.tableNo = TableNo;
		this.numOfSeats = numOfSeats;
		this.customer = null;
		
		LocalTime startTime = LocalTime.parse("11:00:00");
		LocalTime endTime = LocalTime.parse("12:00:00");
		
		//dates for 1 month from now
		for (int i = 0; i < 11; i++) {
			timeSlots.add(new TimeSlot(startTime, endTime));//edited
			startTime = startTime.plusHours(1);
			endTime = endTime.plusHours(1);
		}
	}
	
	//getters
	public int getTableNo() {return tableNo;}
	public int getNumOfSeats() {return numOfSeats;}
	public Customer getCustomer() {return customer;}
	public ArrayList<TimeSlot> getTimeSlots() {return timeSlots;}
	
	//remove expired reservations
	private void removeExpired() {
		for (TimeSlot ts : timeSlots) {
			if (!ts.getReservationList().isEmpty()) {
				for (Reservation res : ts.getReservationList()) {
					//if current date > book date, remove reservation
					if (LocalDate.now().compareTo(res.getDate()) > 0) {
						System.out.println("Reservation Expired for Customer: " + res.getCustomer().getName());
						System.out.println("Book Date: " + res.getDate() + ", Book Time: " + res.getStartTime());
						System.out.println("Current Date: " + LocalDate.now() + ", Current Time: " + LocalTime.now());
						ts.removeReservation(res.getDate());
						return;
					} else if (LocalDate.now().equals(res.getDate())) {
						//if late 10 minutes after book time, remove reservation
						if (Duration.between(res.getStartTime(), LocalTime.now()).toMinutes() >= 10) {
							System.out.println("Reservation Expired for Customer: " + res.getCustomer().getName());
							System.out.println("Book Date: " + res.getDate() + ", Book Time: " + res.getStartTime());
							System.out.println("Current Date: " + LocalDate.now() + ", Current Time: " + LocalTime.now());
							ts.removeReservation(res.getDate());
							return;
						}
					}
				}
			}
		}
	}
	
	//assign and unAssign
	public boolean assign(Customer customer) {
		//remove all expired reservations
		removeExpired();
		
		//if occupied, cannot assign
		if (this.customer != null) return false;
		
		//check reservation
		for (TimeSlot ts : timeSlots) {
			//locate time slot
			if (LocalTime.now().compareTo(ts.getStartTime()) >= 0 && LocalTime.now().compareTo(ts.getEndTime()) < 0) {
				if (!ts.getReservationList().isEmpty()) {
					for (Reservation res : ts.getReservationList()) {
						//locate date
						if (LocalDate.now().equals(res.getDate())) {
							//table reserved by this customer and the reservation is not expired, can assign
							if (res.getCustomer().getContactNo() == customer.getContactNo()) {
								ts.removeReservation(res.getDate());
								this.customer = customer;
								System.out.println(customer.getName() + " assigned to table " + tableNo);
								return true;
							//table reserved by another customer, cannot assign
							} else return false;
						}
					}
				}
				
				//no reservation, can assign if customer arrive before XX:20 (to avoid clash with next time slot)
				if (Duration.between(ts.getStartTime(), LocalTime.now()).toMinutes() <= 20) {
					this.customer = customer;
					System.out.println(customer.getName() + " assigned to table " + tableNo);
					return true;
				}
				
				//can also assign if this slot is not the last slot and next slot is not reserved
				if (timeSlots.indexOf(ts) == timeSlots.size()-1) {
					return false;
				}
				TimeSlot tsNext = timeSlots.get(timeSlots.indexOf(ts)+1);
				if (!tsNext.getReservationList().isEmpty()) {
					for (Reservation res : tsNext.getReservationList()) {
						//if there is reservation in next slot, cannot assign
						if (LocalDate.now().equals(res.getDate())) {
							return false;
						} 
					}
					this.customer = customer;
					System.out.println(customer.getName() + " assigned to table " + tableNo);
					return true;
				} else {
					this.customer = customer;
					System.out.println(customer.getName() + " assigned to table " + tableNo);
					return true;
				}
			}
		}
		return false;
	}
	
	public void unAssign() {
		//remove all expired reservations
		removeExpired();
		
		if (this.customer != null) {
			this.customer = null;
			System.out.println("Table unassigned successfully");
		} else System.out.println("Table is already unasigned");
	}
	
	public void printTableStatus(LocalDate date) {
		//remove all expired reservations
		removeExpired();
		
		System.out.println("Table " + tableNo + " (" + numOfSeats + " Max Seats)");
		System.out.println("Current Time: " + LocalTime.now());
		System.out.println("Availability: " + (customer != null ? "Occupied by " + customer.getName() : "Unoccupied"));
		System.out.println("--------------------");
		System.out.println("Bookings for Table " + tableNo + " on " + date);
		for (TimeSlot ts : timeSlots) {
			boolean booked = false;
			if (!ts.getReservationList().isEmpty()) {
				for (Reservation res : ts.getReservationList()) {
					if (res.getDate().equals(date)) {
						System.out.println(ts.getStartTime() + " to " + ts.getEndTime() + " is BOOKED" + 
								", Name: " + res.getCustomer().getName() + 
								", Contact Number: " + res.getCustomer().getContactNo());
						booked = true;
						break;
					}
				} if (!booked) System.out.println(ts.getStartTime() + " to " + ts.getEndTime() + " is FREE");
			} else {
				System.out.println(ts.getStartTime() + " to " + ts.getEndTime() + " is FREE");
			}
		}
		System.out.println("--------------------");
	}
	
	//book slot & free slot
	public boolean bookSlot(Reservation reservation) {
		//remove all expired reservations
		removeExpired();
		
		//locate time slot
		for (TimeSlot ts : timeSlots) {
			if (ts.getStartTime() == reservation.getStartTime()) {
				//if that time slot is already booked at the same date, reservation failed
				if (!ts.getReservationList().isEmpty()) {
					for (Reservation res : ts.getReservationList()) {
						if (res.getDate().equals(reservation.getDate())) return false;
					}
				}
				ts.addReservation(reservation);
				System.out.println("Booked successfully");
				System.out.println("--------------------");
				System.out.println("Table Number: " + tableNo);
				System.out.println("Customer Name: " + reservation.getCustomer().getName());
				System.out.println("Contact Number: " + reservation.getCustomer().getContactNo());
				System.out.println("Booked Date: " + reservation.getDate());
				System.out.println("Booked Time: " + reservation.getStartTime());
				System.out.println("Number of Pax: " + reservation.getNumOfPax());
				System.out.println("--------------------");
				return true;
			}
		}
		return false;
	}
	
	public void freeSlot(LocalTime startTime, LocalDate date) {
		//remove all expired reservations
		removeExpired();
		
		//locate time slot
		for (TimeSlot ts : timeSlots) {
			if (ts.getStartTime() == startTime) {
				//locate reservation with this date
				if (!ts.getReservationList().isEmpty()) {
					for (Reservation res : ts.getReservationList()) {
						if (res.getDate().equals(date)) {
							System.out.println("Free time slot successfully");
							ts.removeReservation(date);
							return;
						}
					}
				}
				System.out.println("Time slot is already free");
				return;
			}
		}
	}
}
