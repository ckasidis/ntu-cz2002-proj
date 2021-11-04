package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {

	public static void main(String[] args) {
		
		//assign tables
		ArrayList<Table> tableList = new ArrayList<Table>();
		
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
		
		int c;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Choose your method");
			System.out.println("1: check all table status");
			System.out.println("2: check table status");
			System.out.println("3: book reservation");
			System.out.println("4: remove reservation");
			System.out.println("5: assign table");
			System.out.println("6: unAssign table");
			c = sc.nextInt();
			switch(c) {
				case 1: //check all table status
					for (Table table: tableList)
						table.printTableStatus();
					break;
				case 2: //check table status
					System.out.println("Enter table number (1-10)");
					int tmp2TableNo = sc.nextInt();
					if (tmp2TableNo < 1 || tmp2TableNo > 10) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}
					
					//find table and print status
					for (Table table: tableList) {
						if (table.getTableNo() == tmp2TableNo) {
							table.printTableStatus();
							break;
						}
					}
					break;
				case 3: //book reservation			
					System.out.println("Enter customer name");
					String tmp3CusName = sc.next();
					System.out.println("Enter contact number");
					int tmp3CusContactNo = sc.nextInt();
					Customer tmp3Cus = new Customer(tmp3CusName, tmp3CusContactNo);
					
					System.out.println("Select a booking time (enter 11-21)");
					int tmp3Int = sc.nextInt();
					if (tmp3Int < 11 || tmp3Int > 21) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}
					LocalTime tmp3StartTime = LocalTime.of(tmp3Int, 0);
					
					System.out.println("Enter number of person per table");
					int tmp3Pax = sc.nextInt();
					if (tmp3Pax > 10 || tmp3Pax < 1) {
						System.out.println("Please enter 1-10 people");
						break;
					}
					else {
						//find a table with enough seats
						Reservation tmp3reservation = new Reservation(tmp3Cus, tmp3StartTime, tmp3Pax);
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
					break;
				case 4: //remove reservation
					System.out.println("Enter table number (1-10)");
					int tmp4TableNo = sc.nextInt();
					if (tmp4TableNo < 1 || tmp4TableNo > 10) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}

					System.out.println("Select a booking time (11-21)");
					int tmp4Int = sc.nextInt();
					if (tmp4Int < 11 || tmp4Int > 21) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}		
					LocalTime tmp4StartTime = LocalTime.of(tmp4Int, 0);
					
					//find the table and free slot
					for (Table table: tableList) {
						if (table.getTableNo() == tmp4TableNo) {
							table.freeSlot(tmp4StartTime);
							break;
						}
					}
					break;
				case 5: //assign table
					System.out.println("Enter customer name");
					String tmp5CusName = sc.next();
					System.out.println("Enter contact number");
					int tmp5CusContactNo = sc.nextInt();
					Customer tmp5Cus = new Customer(tmp5CusName, tmp5CusContactNo);
					
					System.out.println("Enter number of person per table");
					int tmp5Pax = sc.nextInt();
					if (tmp5Pax > 10 || tmp5Pax < 1) {
						System.out.println("Please enter 1-10 people");
						break;
					}
					else {
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
					break;
				case 6: //unAssign table
					System.out.println("Enter table number (1-10)");
					int tmp6TableNo = sc.nextInt();
					if (tmp6TableNo < 1 || tmp6TableNo > 10) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}
					
					//find the table and unAssign
					for (Table table: tableList) {
						if (table.getTableNo() == tmp6TableNo) {
							table.unAssign();
							break;
						}
					}
					break;
				default: System.exit(0);
			}
		}
		
	}

}
