package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {

	public static void main(String[] args) {
		
		//assign tables
		ArrayList<Table> tableList = new ArrayList<Table>();
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		int tableNo = 1;
		
		//5 tables with 2 seats
		for (int i = 0; i < 5; i++) {
			tableList.add(new Table(tableNo, 2));
			tableNo++;
		}
		//3 tables with 4 seats
		for (int i = 0; i < 3; i++) {
			tableList.add(new Table(tableNo, 4));
			tableNo++;
		}
		
		//2 tables with 6 seats
		for (int i = 0; i < 2; i++) {
			tableList.add(new Table(tableNo, 6));
			tableNo++;
		}
		
		int c;
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("Choose your method");
			System.out.println("1: check time slots");
			System.out.println("2: book reservation");
			c = sc.nextInt();
			switch(c) {
				case 1: 
					for (Table table: tableList)
						table.printTimeSlots();
					break;
				case 2:
					Customer tmp2Cus;
					int tmp2Pax;
					LocalTime tmp2StartTime;
					
					System.out.println("Enter customer name");
					tmp2Cus = new Customer(sc.next());
					customerList.add(tmp2Cus);
					
					System.out.println("Select a booking time (enter 11-21)");
					int tmpInt = sc.nextInt();
					if (tmpInt < 11 || tmpInt > 21) {
						System.out.println("Please enter an integer between 11-21");
						break;
					}
					tmp2StartTime = LocalTime.of(tmpInt, 0);
					
					System.out.println("Enter number of person per table");
					tmp2Pax = sc.nextInt();
					if (tmp2Pax > 6 || tmp2Pax < 1) {
						System.out.println("Please enter 1-6 people");
						break;
					}
					else {
						Reservation tmp2reservation = new Reservation(tmp2Cus, tmp2StartTime, tmp2Pax);
						boolean booked = false;
						for (Table table: tableList) {
							if (tmp2Pax <= table.getNumOfSeats()) {
								booked = table.bookSlot(tmp2reservation);
								if (booked) break;
							}
						}
						if (!booked) System.out.println("reservation failed");
					}
					break;
				default: System.exit(0);
			}
		}
		
	}

}
