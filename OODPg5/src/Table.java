package RRPSS;

import java.time.LocalTime;

public class Table implements ITable{
	private int TableNo;
	private int numOfSeats;
	private boolean isOccupied;
	
	TimeSlot[] slots  = new TimeSlot[7]; {
		int start = 930;
		int end = 1130;
		for (int i=0; i<7; i++) {
			slots[i] = new TimeSlot(start, end);
			start = start + 200;
			end = end + 200;
		}
	}
	
	
	//constructor
	public Table() {isOccupied = false;}
	
	public Table(int TableNo, int numOfSeats, boolean isOccupied) {
		this.TableNo = TableNo;
		this.numOfSeats = numOfSeats;
		this.isOccupied = isOccupied;
	}
	
	//getter
	public int getNumOfSeats() {return numOfSeats;}
	public boolean getIsOccupied() {return isOccupied;}
	
	//schedule
	public void printSchedule() {
		System.out.println("Bookings for Table " + TableNo);
		System.out.println("--------------------");
		for (int i=0; i<7; i++) {
			System.out.println(slots[i].getStartTime() + " to " + slots[i].getEndTime() + " is " + 
							  ( (slots[i].getOccupied()) ? "Booked" : "Free"));
		}
		System.out.println("--------------------");
	}
	//assign & unassign
	public void Assign() {
		isOccupied = true;
		//get current time
		LocalTime time = LocalTime.now();
		int timeInt = time.getHour()*100 + time.getMinute();
		//if current timeslot is occupied set isOccupied
		for (int i=0; i<7; i++) {
			if (slots[i].getStartTime() <= timeInt && slots[i].getEndTime() >= timeInt)
				if (slots[i].getOccupied()) {
					System.out.println("Table has been reserved");
					isOccupied = false;
				}
		}
	}
	
	public void unAssign() {
		isOccupied = false;
	}
	
	//book slots & free slot
	public boolean book(int startTime) {
		
		//locate timeslot and set as occupied
		for (int i=0; i<7; i++) {
			if (slots[i].getStartTime() == startTime) {
				if (! slots[i].getOccupied()) {
					slots[i].setOccupied(true);
					return true;
				}
			}
		}
		return false;
	}
	
	public void freeSlot (int startTime) {
		for (int i=0; i<7; i++) {
			if (slots[i].getStartTime() == startTime) 
				slots[i].setOccupied(false);
		}
	}
}
