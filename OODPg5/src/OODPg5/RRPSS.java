package OODPg5;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

public class RRPSS {
	static Scanner s = new Scanner(System.in);
	
	private Table[] table;
	
	private static void showMenu(Menu menu) {
		int c;
		menu.showMenuItems();
		System.out.println("1: Create menu item");
		System.out.println("2: Create promotion set");
		System.out.println("3: Edit menu item");
		System.out.println("4: return");
		while(true) {
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
	public static void start() {
		int choice;
		Menu menu = new  Menu();
		//ArrayList<Order> Total_orders = new ArrayList<Order>();
		
		ArrayList<Table> tableList = new ArrayList<Table>();
		
		TableManager tables = new TableManager(tableList);
		
		System.out.println("Welcome to Restaurant Reservation and Point of Sale System");
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
						break;
				case 5:
						break;
				case 6:
						break;
				case 7: System.out.println("Shutting down..");return;
					default:System.out.println("Invalid entry!");
			}
		}
	}
	public static void main(String[] args) {
		start();
	}
}
