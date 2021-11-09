package OODPg5;
//package rrps;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Represents the Restaurant Reservation and Point of Sale System of the restaurant
 * @author Group 5
 *
 */
public class RRPSS {
	static Scanner s = new Scanner(System.in);
	static final boolean showSet = true;
	/**
	 * Array of tables in the restaurant
	 */
	private Table[] table;
	/**
	 * Create, display and edit the menu of the restaurant
	 * @param menu Menu of the restaurant
	 */
	private static void showMenu(Menu menu) {
		int c;
		while(true) {
			menu.showMenuItems(showSet);
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
	/**
	 * Update the order placed by the customer in the restaurant
	 * @param menu Menu of the restaurant
	 * @param order Order placed by the customer
	 */
	public static void  updateOrder(Menu menu,Order order) {
		menu.showMenuItems(showSet);
		while(true) {
			order.viewOrder();
			System.out.println("======= SELECT CHOICE =======\n(1)add item to order (2)Remove item from order (3)Return");
			int c = s.nextInt();
			switch(c) {
				case 1:
						menu.showMenuItems(showSet);
						System.out.println("Choose item to add to order");
						order.addOrderItem(menu.getMenuItem());
						break;
				case 2:
						menu.showMenuItems(showSet);
						System.out.println("Choose item to remove from order");
						order.removeOrderItem();
						break;
				case 3:return;
				default:System.out.println("Invalid option!");
			}
		}
	}
	
	/**
	 * Order invoice of order for a particular table 
	 * @param tableNumber Table number of the table
	 */
	public static void orderInvoice(int tableNumber) {
		
	}
	/**
	 * Print the report for sales revenue
	 */
	public static void printSalesRevenueReport() {
		
	}
	
	/**
	 * Allows user to operate the RRPSS of the restaurant
	 * @param menu Menu of the restaurant
	 * @param tables Tables in the restaurant
	 * @param SalesRecord Array list of orders placed in the restaurant
	 * @param staffs Array list of staffs working at the restaurant
	 */
	public static void start(Menu menu, TableManager tables, ArrayList<Order> SalesRecord, ArrayList<Staff> staffs) {
		int choice;
		
		ArrayList<Order> table_orders = new ArrayList<Order>();
		for(int i=0;i<10;i++){
			table_orders.add(new Order(i+1));} // assuming order(TableNumber, date)
		try {
			while(true) {
				LocalDateTime date = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E dd/MM/yyyy HH:mm:ss");
				System.out.println("Welcome to Restaurant Reservation and Point of Sale System");
				System.out.println("Date:" + date.format(dtf));
				System.out.println("1: Open Menu");
				System.out.println("2: Table Manager");
				System.out.println("3: assign table");
				System.out.println("4: unAssign table");
				System.out.println("5: Update order");
				System.out.println("6: Print Sales Revenue Report");
				System.out.println("7: Shut down! <all data will be lost>");
				choice = s.nextInt();
				switch(choice) {
					case 1: 
						showMenu(menu);
						break;
					case 2:
						tables.bootTableManager();
						break;
					case 3:
						Customer cus = new Customer();
						int tb = tables.assignTable(cus);
						System.out.println("Enter Staff ID");
						int ID = s.nextInt();
						s.nextLine();
						if(tb!=-1) {
							table_orders.set(tb-1, new Order(cus,staffs.get(ID),tb));
						}
						break;
					case 4:
						int tb1 = tables.unAssignTable();
						if(table_orders.get(tb1-1).getCheckNo() !=0) {
							table_orders.get(tb1-1).printOrderInvoice();
							SalesRecord.add(table_orders.get(tb1-1));
							table_orders.set(tb1-1, new Order(tb1-1));
						}
						//Total_order.get(tn1-1).addMenuItem(menuItem));
						break;
					case 5:
						System.out.println("Enter table number (1-10)");
						int tn2;
						while((tn2 = s.nextInt())<1 || tn2>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						updateOrder(menu, table_orders.get(tn2-1));
						break;
//					case 6:
//						System.out.println("Select option(1-2)");
//						int checkToday;
//						while((checkToday = s.nextInt())<1 || checkToday>2) {
//						System.out.println("1:print Sales Revenue for today");
//						System.out.println("2:print Sales Revenue for the month");
//						}
//						if(checkToday == 1) {
//							SaleRevenue.calculateSaleRevenue(true);
//						}else {
//							SaleRevenue.calculateSaleRevenue(false);
//						}
//						break;
					case 7: System.out.println("Shutting down..");return;
						default:System.out.println("Invalid entry!");
				}
			}
		}
		catch (NumberFormatException e){
			System.out.println("Invalid integer! Try again.");
			String clear = s.nextLine();
		}
		catch(Exception e) {
			String clear = s.nextLine();
			System.out.println("Invalid input! Try again.");
		}
		finally {
			start(menu, tables, SalesRecord,staffs);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		while(s.nextInt()!=-1) {
			s.nextLine();
			staffs.add(new Staff());
			System.out.println("Enter -1 to stop adding staff to roster.");
		}
		Menu menu = new  Menu();
		ArrayList<Table> tableList = new ArrayList<Table>();
		ArrayList<Order> salesRecord = new ArrayList<Order>();
		TableManager tables = new TableManager(tableList);
		start(menu, tables, salesRecord,staffs);
	}
}
