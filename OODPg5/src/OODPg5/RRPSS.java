package OODPg5;

import java.util.Scanner;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class RRPSS {
	static Scanner s = new Scanner(System.in);
	
	private Table[] table;
	
	private static void showMenu(Menu menu) {
		int c;
		while(true) {
		menu.showMenuItems();
		System.out.println("1: Create menu item");
		System.out.println("2: Create promotion set");
		System.out.println("3: Edit menu item");
		System.out.println("4: return");
			c = s.nextInt();
			switch(c){
				case 1:	
						menu.createMenuItem(); 
						break;
				case 2: 
						menu.createPromotionSet();
						break;
				case 3:
						menu.editMenu();
						break;
				case 4:return;
						
				default: System.out.println("invalid entry");break;
			}

		}

	}
	public static void  order(Staff staff) {
		
	}

	public static void orderInvoice(int tableNumber) {
		
	}
	public static void printSalesRevenueReport() {
		
	}
	public static void start(Calendar date) {
		int choice;
		Menu menu = new  Menu();
		//ArrayList<Order> Total_orders = new ArrayList<Order>();
		//for(int i=0;i<10<i++){
		//	Total_orders.add(new Order(i+1, date))} // assuming order(TableNumber, date)
		//ArrayList<Order> finished_orders = new ArrayList<Order>();
		
		ArrayList<Table> tableList = new ArrayList<Table>();
		
		TableManager tables = new TableManager(tableList);
		
		System.out.println("Welcome to Restaurant Reservation and Point of Sale System");
		System.out.println("Today's Date is:" + date.getTime());
		while(true) {
			System.out.println("1: Open Menu");
			System.out.println("2: Boot table manager");
			System.out.println("3: Book/Remove a reservation");
			System.out.println("4: Take Order");
			System.out.println("5: Update order");
			System.out.println("6: Print order invoice");
			System.out.println("7: Print Sales Revenue Report");
			System.out.println("8: Shut down! <all data will be lost>");
			choice = s.nextInt();
			switch(choice) {
				case 1: 
						showMenu(menu);break;
				case 2:
						tables.bootTableManager();break;
				case 3:
						tables.reservation();break;
				case 4:
						System.out.println("Enter table number (1-10)");
						int tn;
						while((tn = s.nextInt())<1 || tn>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						Total_order.get(tn-1).setdetails(date,customer);
						break;
				case 5:
						System.out.println("Enter table number (1-10)");
						int tn;
						while((tn = s.nextInt())<1 || tn>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						Total_order.get(tn-1).addMenuItem(menuItem));
						break;
				case 6:
						System.out.println("Enter table number (1-10)");
						int tn;
						while((tn = s.nextInt())<1 || tn>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						finished_orders.add(Total_order.get(tn-1));
						Total_order.printSalesInvoice();
						Total_order.remove(tn-1);
						break;
				case 7:
						System.out.println("Select option(1-2)");
						System.out.println("1:print Sales Revenue for today");
						System.out.println("1:print Sales Revenue for the month");
						break;
				case 8: System.out.println("Shutting down..");return;
					default:System.out.println("Invalid entry!");
			}
		}
	}
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		start(calendar);
	}
}

