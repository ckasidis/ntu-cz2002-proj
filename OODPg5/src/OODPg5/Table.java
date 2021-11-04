package OODPg5;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class Table {
	private int tableNo;
	private int numOfSeats;
	private boolean isOccupied;
	private ArrayList<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
	
	//constructor
	public Table() {isOccupied = false;}
	
	public Table(int TableNo, int numOfSeats) {
		this.tableNo = TableNo;
		this.numOfSeats = numOfSeats;
		this.isOccupied = false;
		
		LocalTime startTime = LocalTime.parse("11:00:00");
		LocalTime endTime = LocalTime.parse("12:00:00");
				
		//11:00-12:00 to 21:00-22:00 with 1hr interval between Slots
		for (int i = 0; i < 11; i++) {
			timeSlots.add(new TimeSlot(startTime, endTime));
			startTime = startTime.plusHours(1);
			endTime = endTime.plusHours(1);
		}
	}
	
	//getters
	public int getTableNo() {return tableNo;}
	public int getNumOfSeats() {return numOfSeats;}
	public boolean getIsOccupied() {return isOccupied;}
	public ArrayList<TimeSlot> getTimeSlots() {return timeSlots;}
	
	//assign and unAssign
	public boolean assign(Customer customer) {
		
		//check vacancy
		if (isOccupied) {
			System.out.println("Table is already occupied!");
			return false;
		}
		
		//check reservation
		for (TimeSlot ts : timeSlots) {
			//find time slot
			if (LocalTime.now().compareTo(ts.getStartTime()) >= 0 || LocalTime.now().compareTo(ts.getEndTime()) <= 0) {
				//if another customer reserved this slot, can assign the table to this customer only if another customer did not arrive in 5 minutes
				if (ts.getIsReserved() && ts.getCustomer().getContactNo() != customer.getContactNo()) {
					if (Duration.between(LocalTime.now(), ts.getStartTime()).toMinutes() < 5) {
						System.out.println("Table was already reserved by " + ts.getCustomer().getName() + "! Please choose another table");
						return false;
					} else {
						ts.setIsReserved(false);
						ts.setCustomer(null);
						System.out.println("Reserved slot from " + ts.getCustomer().getName() + " has expired (exceeded 5 minutes from booked time)");
						isOccupied = true;
						System.out.println(customer.getName() + " assigned to table " + tableNo);
						return true;
					}
				//If this customer reserved this slot or there is no reservation, can assign the table
				} else {
					ts.setIsReserved(false);
					ts.setCustomer(null);
					isOccupied = true;
					System.out.println(customer.getName() + " assigned to table " + tableNo);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void unAssign() {
		if (isOccupied) {
			isOccupied = false;
			System.out.println("Table Unassigned Successfully");
		} else System.out.println("Table is already unasigned");

	}
	
	public void printTimeSlots() {
		System.out.println("Bookings for Table " + tableNo + " (" + numOfSeats + " Max Seats)");
		System.out.println("--------------------");
		for (TimeSlot ts : timeSlots) {
			System.out.println(ts.getStartTime() + " to " + ts.getEndTime() + " is " + 
							  ( ts.getIsReserved() ? "Booked by " + ts.getCustomer().getName(): "Free"));
		}
		System.out.println("--------------------");
	}
	
	//book slot & free slot
	public boolean bookSlot(Reservation reservation) {
		
		//locate time slot and set as reserved
		for (TimeSlot ts : timeSlots) {
			if (ts.getStartTime() == reservation.getStartTime() && !ts.getIsReserved()) {
				ts.setIsReserved(true);
				ts.setCustomer(reservation.getCustomer());
				System.out.println("Table " + tableNo + " booked successfully");
				System.out.println("--------------------");
				System.out.println("Customer Name: " + reservation.getCustomer().getName());
				System.out.println("Contact Number: " + reservation.getCustomer().getContactNo());
				System.out.println("Booked Time: " + reservation.getStartTime());
				System.out.println("Number of Pax: " + reservation.getNumOfPax());
				System.out.println("--------------------");
				return true;
			}
		}
		return false;
	}
	
	public void freeSlot(LocalTime startTime) {
		for (TimeSlot ts : timeSlots) {
			if (ts.getStartTime() == startTime) {
				if (ts.getIsReserved()) {
					System.out.println("Free time slot successfully");
					ts.setIsReserved(false);
					ts.setCustomer(null);
				} else System.out.println("Time slot is already free");
			}
		}
	}
}
