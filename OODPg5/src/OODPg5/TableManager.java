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
	/**
	 * A list of all the tables in the restaurant
	 */
	private static ArrayList<Table> tableList;
	
	static Scanner sc = new Scanner(System.in);
	/**
	 * Creates a system to manage the assignment of tables in the restaurant.
	 * There are 2 tables each with 2,4,6,8 and 10 seats respectively.
	 * 
	 * @param tm This is the list of Tables
	 */
	public TableManager(ArrayList<Table> tm) {
		tableList = tm;
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
	
	/**
	 * Books a reservation according to input date, time and number of persons.
	 * Booking must be made 2 hours in advance.
	 * 
	 * A free table with sufficient seats will be assigned.
	 */

	private static void bookReservation() {
		Customer cus = new Customer();
		
		System.out.println("Select a booking month (1-12)"); //edited
		int mon;
		while ((mon = sc.nextInt()) < 1 || mon > 12) {
			System.out.println("Please enter an integer between 1-12");
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int day;
		while ((day = sc.nextInt()) < 1 || day> 31) {
			System.out.println("Please enter an integer between 1-31");
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
		while ((tmp = sc.nextInt()) < 11 || tmp > 21) {
			System.out.println("Please enter an integer between 11-21");
		}
		LocalTime startTime = LocalTime.of(tmp, 0);
		
		//can only book 3 time slots (> 2 hour) in advance for same day reservation
		if (LocalDate.now().equals(date)) {
			if (Duration.between(LocalTime.now(), startTime).toHours() <= 2) {
				System.out.println("Cannot Reserve!, Please reserve a time slot more than 2 hours in advance!");
				System.out.println("Your Date: " + date + ", Your Time: " + startTime);
				System.out.println("Current Date: " + LocalDate.now() + ", Current Time: " + LocalTime.now());
				return;
			}
		}
		
		System.out.println("Enter number of person per table");
		int pax;
		while((pax = sc.nextInt()) > 10 || pax < 1) {
			System.out.println("Please enter 1-10 people");
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
	
	private static void removeReservation() {
		System.out.println("Enter table number (1-10)");
		int tableNo;
		while((tableNo = sc.nextInt()) < 1 || tableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
		}
		
		System.out.println("Select a booking month (1-12)"); //edited
		int mon;
		while ((mon = sc.nextInt()) < 1 || mon > 12) {
			System.out.println("Please enter an integer between 1-12");
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int day;
		while ((day = sc.nextInt()) < 1 || day> 31) {
			System.out.println("Please enter an integer between 1-31");
		}	
		
		LocalDate date = LocalDate.of(LocalDate.now().getYear(), mon, day); //edited

		System.out.println("Select a booking time (11-21)");
		int tmp;
		while ((tmp = sc.nextInt()) < 11 || tmp > 21) {
			System.out.println("Please enter an integer between 11-21");
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
	
	/**
	 * Assigns free Table with sufficient capacity to incoming customer with input number of persons
	 * Customer not assigned a table 40 minutes before closing time
	 */
	
	private void assignTable() {
		Customer cus = new Customer();
		
		//cannot assign if came after 40 minutes before closing time
		if (Duration.between(LocalTime.now(), LocalTime.parse("22:00:00")).toMinutes() < 40) {
			System.out.println("Sorry! we are closing");
			return;
		}
		
		System.out.println("Enter number of person per table");
		int pax;
		while ((pax = sc.nextInt()) > 10 || pax < 1) {
			System.out.println("Please enter 1-10 people");
		}

		//find a table with enough seats
		boolean assigned = false;
		for (Table table: tableList) {
			if (pax <= table.getNumOfSeats()) {
				//assigned return false if assigning failed
				assigned = table.assign(cus);
				if (assigned) {
					//get staff
					//replaceTableOrder(table.getCustomer(), <Staff Object> ,table.getTableNo());
					return;
				}
			}
		}
		System.out.println("Assigning failed");
		return;
	}
	
	/**
	 * Unassigns a Table with input table number 
	 * and sets table free 
	 */
	private void unAssignTable() {
		System.out.println("Enter table number (1-10)");
		int tableNo;
		while ((tableNo = sc.nextInt()) < 1 || tableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
		}
		
		//find the table and unAssign
		for (Table table : tableList) {
			if (table.getTableNo() == tableNo) {
				table.unAssign();
				break;
			}
		}
	}
	
	/**
	 * Allows selection of all operations to manage the tables 
	 * 
	 */

	public void bootTableManager() {
		int c;
		
		while(true) {
			System.out.println("Table Manager");
			System.out.println("1: check all table status");
			System.out.println("2: check table status");
			System.out.println("3: book reservation");
			System.out.println("4: remove reservation");
			System.out.println("5: assign table");
			System.out.println("6: unAssign table");
			System.out.println("7: return");
			c = sc.nextInt();
			switch(c) {
				case 1: //check all table status
					System.out.println("Select month (1-12)"); //edited
					int tmp1Mon;
					while ((tmp1Mon = sc.nextInt()) < 1 || tmp1Mon > 12) {
						System.out.println("Please enter an integer between 1-12");
					}	
					System.out.println("Select day (1-31)"); //edited
					int tmp1Day;
					while ((tmp1Day = sc.nextInt()) < 1 || tmp1Day> 31) {
						System.out.println("Please enter an integer between 1-31");
					}	
					LocalDate tmp1Date = LocalDate.of(LocalDate.now().getYear(), tmp1Mon, tmp1Day); 
					for (Table table: tableList)
						table.printTableStatus(tmp1Date);
					break;
				case 2: //check table status				
					System.out.println("Enter table number (1-10)");
					int tmp2TableNo;
					while ((tmp2TableNo = sc.nextInt()) < 1 || tmp2TableNo > 10) {
						System.out.println("Please enter an integer between 1-10");
					}
					System.out.println("Select month (1-12)"); //edited
					int tmp2Mon;
					while ((tmp2Mon = sc.nextInt()) < 1 || tmp2Mon > 12) {
						System.out.println("Please enter an integer between 1-12");
					}	
					System.out.println("Select day (1-31)"); //edited
					int tmp2Day;
					while ((tmp2Day = sc.nextInt()) < 1 || tmp2Day> 31) {
						System.out.println("Please enter an integer between 1-31");
					}	
					LocalDate tmp2Date = LocalDate.of(LocalDate.now().getYear(), tmp2Mon, tmp2Day); 
					//find table and print status
					for (Table table: tableList) {
						if (table.getTableNo() == tmp2TableNo) {
							table.printTableStatus(tmp2Date);
							break;
						}
					}
					break;
				case 3: // book reservation
					bookReservation();
					break;
				case 4:
					removeReservation();
					break;
				case 5: //assign table
					assignTable();
					break;
				case 6: //unAssign table	
					unAssignTable();
					break;
				case 7: return;
				default: System.out.println("invalid entry!");
			}
		}
		
	}
}
