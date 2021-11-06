package OODPg5;

import java.time.LocalTime;
import java.time.LocalDate; //edited
import java.util.ArrayList;
import java.util.Scanner;

public class TableManager {
	private static ArrayList<Table> tableList;
	
	static Scanner sc = new Scanner(System.in);
	
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
	public void reservation()
	{
		
		int c = sc.nextInt();
		System.out.println("1: book reservation");
		System.out.println("2: remove reservation");
		switch(c){
				case 1: //book reservation			
					
					bookReservation();
					break;
				case 2: //remove reservation
					
					removeReservation();
					break;
		}
	}
	private static void bookReservation() {
		Customer tmp3Cus = new Customer();
		
		System.out.println("Select a booking month (1-12)"); //edited
		int tmp3Mon;
		while ((tmp3Mon = sc.nextInt()) < 1 || tmp3Mon > 12) {
			System.out.println("Please enter an integer between 1-12");
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int tmp3Day;
		while ((tmp3Day = sc.nextInt()) < 1 || tmp3Day> 31) {
			System.out.println("Please enter an integer between 1-31");
		}	
		
		LocalDate tmp3Date = LocalDate.of(LocalDate.now().getYear(), tmp3Mon, tmp3Day); //edited
		
		System.out.println("Select a booking time (enter 11-21)");
		int tmp3Int;
		while ((tmp3Int = sc.nextInt()) < 11 || tmp3Int > 21) {
			System.out.println("Please enter an integer between 11-21");
		}
		LocalTime tmp3StartTime = LocalTime.of(tmp3Int, 0);
		
		System.out.println("Enter number of person per table");
		int tmp3Pax;
		while((tmp3Pax = sc.nextInt()) > 10 || tmp3Pax < 1) {
			System.out.println("Please enter 1-10 people");
		}

		//find a table with enough seats
		Reservation tmp3reservation = new Reservation(tmp3Cus, tmp3StartTime, tmp3Pax, tmp3Date); //edited
		boolean booked = false;
		for (Table table: tableList) {
			if (tmp3Pax <= table.getNumOfSeats()) {
				//booked return false if reservation failed
				booked = table.bookSlot(tmp3reservation);
				if (booked) break;
			}
		}
		if (!booked) System.out.println("Reservation failed, please try another time slot");
	
	}
	private static void removeReservation() {
		System.out.println("Enter table number (1-10)");
		int tmp4TableNo;
		while((tmp4TableNo = sc.nextInt()) < 1 || tmp4TableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
		}
		
		System.out.println("Select a booking month (1-12)"); //edited
		int tmp4Mon;
		while ((tmp4Mon = sc.nextInt()) < 1 || tmp4Mon > 12) {
			System.out.println("Please enter an integer between 1-12");
		}	
		
		System.out.println("Select a booking day (1-31)"); //edited
		int tmp4Day;
		while ((tmp4Day = sc.nextInt()) < 1 || tmp4Day> 31) {
			System.out.println("Please enter an integer between 1-31");
		}	
		
		LocalDate tmp4Date = LocalDate.of(LocalDate.now().getYear(), tmp4Mon, tmp4Day); //edited

		System.out.println("Select a booking time (11-21)");
		int tmp4Int;
		while ((tmp4Int = sc.nextInt()) < 11 || tmp4Int > 21) {
			System.out.println("Please enter an integer between 11-21");
		}		
		LocalTime tmp4StartTime = LocalTime.of(tmp4Int, 0);
		
		//find the table and free slot
		for (Table table: tableList) {
			if (table.getTableNo() == tmp4TableNo) {
				table.freeSlot(tmp4StartTime, tmp4Date); //edited
				break;
			}
		}
	}
	private void assignTable() {
		Customer tmp5Cus = new Customer();
		
		System.out.println("Enter number of person per table");
		int tmp5Pax;
		while ((tmp5Pax = sc.nextInt()) > 10 || tmp5Pax < 1) {
			System.out.println("Please enter 1-10 people");
		}

		//find a table with enough seats
		boolean assigned = false;
		for (Table table: tableList) {
			if (tmp5Pax <= table.getNumOfSeats()) {
				//assigned return false if assigning failed
				assigned = table.assign(tmp5Cus);
				if (assigned) break;
			}
		}
		if (!assigned) System.out.println("Assigning failed");

	}
	private void unAssignTable() {
		System.out.println("Enter table number (1-10)");
		int tmp6TableNo = sc.nextInt();
		while ((tmp6TableNo = sc.nextInt()) < 1 || tmp6TableNo > 10) {
			System.out.println("Please enter an integer between 1-10");
		}
		
		//find the table and unAssign
		for (Table table: tableList) {
			if (table.getTableNo() == tmp6TableNo) {
				table.unAssign();
				break;
			}
		}
	}
	public void bootTableManager() {
		int c;
		
		while(true) {
			System.out.println("Choose your method");
			System.out.println("1: check all table status");
			System.out.println("2: check table status");
			System.out.println("3: assign table");
			System.out.println("4: unAssign table");
			System.out.println("5: return");
			c = sc.nextInt();
			switch(c) {
				case 1: //check all table status
						
						for (Table table: tableList)
							table.printTableStatus();
						break;
				case 2: //check table status
					
						System.out.println("Enter table number (1-10)");
						int tmp2TableNo;
						while ((tmp2TableNo = sc.nextInt()) < 1 || tmp2TableNo > 10) {
							System.out.println("Please enter an integer between 1-10");
						}
						
						//find table and print status
						for (Table table: tableList) {
							if (table.getTableNo() == tmp2TableNo) {
								table.printTableStatus();
								break;
							}
						}
						break;

				case 3: //assign table
						
						assignTable();
						break;
				case 4: //unAssign table
						
						unAssignTable();
						break;
				case 5: return;
				default: System.out.println("invalid entry!");
			}
		}
		
	}
}
