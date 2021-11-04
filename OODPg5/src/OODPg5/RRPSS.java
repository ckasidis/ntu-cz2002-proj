package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {

	public static void main(String[] args) {
		
		//assign tables
		ArrayList<Table> tableList = new ArrayList<Table>();
		ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
		
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
			System.out.println("1: check time slots");
			System.out.println("2: book reservation");
			System.out.println("3: remove reservation");
			System.out.println("4: print reservation");
			c = sc.nextInt();
			switch(c) {
				case 1: //check time slots
					for (Table table: tableList)
						table.printTimeSlots();
					break;
				case 2: //book reservation
					Customer tmp2Cus;
					int tmp2Pax;
					LocalTime tmp2StartTime;
					
					System.out.println("Enter customer name");
					String tmp2CusName = sc.next();
					System.out.println("Enter contact naumber");
					int tmp2CusContactNo = sc.nextInt();
					tmp2Cus = new Customer(tmp2CusName, tmp2CusContactNo);
					
					System.out.println("Select a booking time (enter 11-21)");
					int tmpInt2 = sc.nextInt();
					if (tmpInt2 < 11 || tmpInt2 > 21) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}
					tmp2StartTime = LocalTime.of(tmpInt2, 0);
					
					System.out.println("Enter number of person per table");
					tmp2Pax = sc.nextInt();
					if (tmp2Pax > 10 || tmp2Pax < 1) {
						System.out.println("Please enter 1-10 people");
						break;
					}
					else {
						//find a table with enough seats and is not reserved
						Reservation tmp2reservation = new Reservation(tmp2Cus, tmp2StartTime, tmp2Pax);
						boolean booked = false;
						for (Table table: tableList) {
							if (tmp2Pax <= table.getNumOfSeats()) {
								//booked return false if reservation failed
								booked = table.bookSlot(tmp2reservation);
								if (booked) {
									reservationList.add(tmp2reservation);
									break;
								}
							}
						}
						if (!booked) System.out.println("reservation failed, please try another time slot");
					}
					break;
				case 3: //remove reservation
					int tmp3TableNo;
					LocalTime tmp3StartTime;
					
					System.out.println("Enter table number (1-10)");
					tmp3TableNo = sc.nextInt();
					if (tmp3TableNo < 1 || tmp3TableNo > 10) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}

					System.out.println("Select a booking time (11-21)");
					int tmpInt3 = sc.nextInt();
					if (tmpInt3 < 11 || tmpInt3 > 21) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}		
					tmp3StartTime = LocalTime.of(tmpInt3, 0);
					
					//find the table and free slot
					for (Table table: tableList) {
						if (table.getTableNo() == tmp3TableNo) {
							table.freeSlot(tmp3StartTime);
							break;
						}
					}
					break;
				default: System.exit(0);
			}
		}
		
	}

}
