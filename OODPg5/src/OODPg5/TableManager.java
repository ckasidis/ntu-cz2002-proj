package OODPg5;

import java.time.LocalTime;
import java.time.Duration;
import java.time.LocalDate; //edited
import java.util.ArrayList;
import java.util.Scanner;

/**
 * System to manage assignment of table
 * @author Group 5
 *
 */
public class TableManager {
	Scanner sc = new Scanner(System.in);
	
	/**
	 * A list of all the tables in the restaurant
	 */
	private ArrayList<Table> tableList;
	
	/**
	 * Creates a system to manage the assignment of tables in the restaurant.
	 * There are 2 tables each with 2,4,6,8 and 10 seats respectively.
	 * 
	 * @param tm This is the list of Tables
	 */
	public TableManager(ArrayList<Table> tableList) {
		this.tableList = tableList;
		int tableNo = 1;
		int tableSize = 2;
		
		//2 tables for each table size 2,4,6,8,10
		while (tableSize <= 10) {
			for (int j = 0; j < 2; j++) {
				tableList.add(new Table(tableNo, tableSize));
				tableNo++;
			}
			tableSize += 2;
		}
	}
	
	//getters
	
	public ArrayList<Table> getTableList() {
		return tableList;
	}
	
	//methods
	
	public void checkTableStatus() {
		int c;
		
		while(true) {
			System.out.println("Check Table Availability and Reservations");
			System.out.println("1: check all table status");
			System.out.println("2: check table status");
			System.out.println("3: return");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			c = sc.nextInt();
			switch(c) {
				case 1: //check all table status
					checkAllTable();
					break;
				case 2: //check table status				
					checkTable();
					break;
				case 3: return;
				default: System.out.println("invalid entry!");
			}
		}	
	}
	 
	/**
	 * Allows selection of all operations to manage the tables: 
	 * Check table status, Book reservation, Remove reservation
	 */
	public void manageReservations() {
		int c;
		
		while(true) {
			System.out.println("Add or Remove Reservation");
			System.out.println("1: book reservation");
			System.out.println("2: remove reservation");
			System.out.println("3: return");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			c = sc.nextInt();
			switch(c) {
				case 1: // book reservation
					bookReservation();
					break;
				case 2:
					removeReservation();
					break;
				case 3: return;
				default: System.out.println("invalid entry!");
			}
		}	
	}
	
	/**
	 * Assigns free Table with sufficient capacity to incoming customer with input number of persons
	 * Customer not assigned a table 40 minutes before closing time
	 */
	public int assignTable(Customer cus) {
		
		//cannot assign if came after 40 minutes before closing time
		if (Duration.between(LocalTime.now(), LocalTime.parse("22:00:00")).toMinutes() < 40) {
			System.out.println("Sorry! we are closing");
			return -1;
		}
		
		System.out.println("Enter number of person per table");
		int pax;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((pax = sc.nextInt()) > 10 || pax < 1) {
			System.out.println("Please enter 1-10 people");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}

		//find a table with enough seats
		boolean assigned = false;
		for (Table table: tableList) {
			if (pax <= table.getNumOfSeats()) {
				//assigned return false if assigning failed
				assigned = table.assign(cus);
				if (assigned) {
					return table.getTableNo();
				}
			}
		}
		System.out.println("Assigning failed");
		return -1;
	}
	
	/**
	 * Unassigns a Table with input table number 
	 * and sets table free 
	 */
	public int unAssignTable() {
		System.out.println("Enter table number (1-10)");
		int tableNo;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((tableNo = sc.nextInt()) < 1 || tableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}
		//find the table and unAssign
		for (Table table : tableList) {
			if (table.getTableNo() == tableNo) {
				table.unAssign();
				return table.getTableNo();
			}
		}
		return -1;
	}

	private void checkAllTable() {
		System.out.println("Select month (1-12)"); //edited
		int month;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((month = sc.nextInt()) < 1 || month > 12) {
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			System.out.println("Please enter an integer between 1-12");
		}	
		System.out.println("Select day (1-31)"); //edited
		int day;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((day = sc.nextInt()) < 1 || day> 31) {
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			System.out.println("Please enter an integer between 1-31");
		}	
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day); 
		for (Table table: tableList)
			table.printTableStatus(date);
	}
	
	private void checkTable() {
		 System.out.println("Enter table number (1-10)");
			int tableNo;
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			while ((tableNo = sc.nextInt()) < 1 || tableNo > 10) {
				System.out.println("Please enter an integer between 1-10");
				while(!sc.hasNextInt()){
					System.out.println("Enter an integer!!!");
					sc.next();
				}
			}
			System.out.println("Select month (1-12)"); //edited
			int month;
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			while ((month = sc.nextInt()) < 1 || month > 12) {
				System.out.println("Please enter an integer between 1-12");
				while(!sc.hasNextInt()){
					System.out.println("Enter an integer!!!");
					sc.next();
				}
			}	
			System.out.println("Select day (1-31)"); //edited
			int day;
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			while ((day = sc.nextInt()) < 1 || day> 31) {
				System.out.println("Please enter an integer between 1-31");
				while(!sc.hasNextInt()){
					System.out.println("Enter an integer!!!");
					sc.next();
				}
			}	
			LocalDate date = LocalDate.of(LocalDate.now().getYear(), month, day); 
			//find table and print status
			for (Table table: tableList) {
				if (table.getTableNo() == tableNo) {
					table.printTableStatus(date);
					break;
				}
			}
	 }
	
	/**
	 * Books a reservation according to input date, time and number of persons.
	 * Booking must be made 2 hours in advance.
	 * 
	 * A free table with sufficient seats will be assigned.
	 */
	private void bookReservation() {
		Customer cus = new Customer();
		
		System.out.println("Select a booking month (1-12)"); //edited
		int mon;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((mon = sc.nextInt()) < 1 || mon > 12) {
			System.out.println("Please enter an integer between 1-12");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int day;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((day = sc.nextInt()) < 1 || day> 31) {
			System.out.println("Please enter an integer between 1-31");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}	
		
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), mon, day); //edited
		
		//cannot reserve before current date
		if (LocalDate.now().compareTo(date) > 0) {
			System.out.println("Cannot Reserve!, please choose current or later date to make a reservation");
			System.out.println("Your Date: " + date + ", Current Date: " + LocalDate.now());
			return;
		} 
		
		System.out.println("Select a booking time (enter 11-21)");
		int tmp;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((tmp = sc.nextInt()) < 11 || tmp > 21) {
			System.out.println("Please enter an integer between 11-21");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}
		LocalTime startTime = LocalTime.of(tmp, 0);
		
		//can only book >2 hour in advance for same day reservation
//		if (LocalDate.now().equals(date)) {
//			if (Duration.between(LocalTime.now(), startTime).toHours() < 2) { // 2 hours in advance
//				System.out.println("Cannot Reserve!, Please reserve your time slot at least 2 hours in advance!");
//				System.out.println("Your Date: " + date + ", Your Time: " + startTime);
//				System.out.println("Current Date: " + LocalDate.now() + ", Current Time: " + LocalTime.now());
//				return;
//			}
//		}
		
		System.out.println("Enter number of person per table");
		int pax;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while((pax = sc.nextInt()) > 10 || pax < 1) {
			System.out.println("Please enter 1-10 people");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}

		//find a table with enough seats
		Reservation reservation = new Reservation(cus, startTime, pax, date); //edited
		boolean booked = false;
		for (Table table: tableList) {
			if (pax <= table.getNumOfSeats()) {
				//booked return false if reservation failed
				booked = table.bookSlot(reservation);
				if (booked) break;
			}
		}
		if (!booked) System.out.println("Reservation failed, please try another time slot");
	
	}
	
	/**
	 * Removes a reservation according to input table number, date and time
	 * Table booked for the reservation will be freed
	 *  
	 */
	
	private void removeReservation() {
		System.out.println("Enter table number (1-10)");
		int tableNo;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while((tableNo = sc.nextInt()) < 1 || tableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}
		
		System.out.println("Select a booking month (1-12)"); //edited
		int mon;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((mon = sc.nextInt()) < 1 || mon > 12) {
			System.out.println("Please enter an integer between 1-12");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int day;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((day = sc.nextInt()) < 1 || day> 31) {
			System.out.println("Please enter an integer between 1-31");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}	
		
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), mon, day); //edited

		System.out.println("Select a booking time (11-21)");
		int tmp;
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		while ((tmp = sc.nextInt()) < 11 || tmp > 21) {
			System.out.println("Please enter an integer between 11-21");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}		
		LocalTime startTime = LocalTime.of(tmp, 0);
		
		//find the table and free slot
		for (Table table: tableList) {
			if (table.getTableNo() == tableNo) {
				table.freeSlot(startTime, date); //edited
				break;
			}
		}
	}
}

