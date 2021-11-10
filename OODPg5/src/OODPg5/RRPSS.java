package OODPg5;
//package rrps;
import java.util.Scanner;
import java.time.format.DateTimeParseException;
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
				if(s.hasNextInt()) {
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
				else {
					System.out.println("Enter an integer!!!");
					s.next();
				}
		}

	}
	/**
	 * Update the order placed by the customer in the restaurant
	 * @param menu Menu of the restaurant
	 * @param order Order placed by the customer
	 */
	public static void  updateOrder(Menu menu,Order order) {
			while(true) {
				order.viewOrder();
				System.out.println("======= SELECT CHOICE =======\n(1)add item to order (2)Remove item from order (3)Return");
				if(s.hasNextInt()) {
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
			else {
				System.out.println("Enter an integer!!!");
				s.next();
			}
		}
	}
		/**
		 * Print sales revenue of the restaurant for a day, a month or a year
		 * @param sales Sale revenue record of the restaurant
		 */
		public static void printSalesrevenue(SaleRevenue sales) {
		int c, mon,day;
		LocalDate date = LocalDate.now(),ld;
		DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM" ) ;
		String input;
		
			while(true) {
				System.out.printf("========= SELECT OPTION =========\n(1)Print today  (2)Print this month  (3)Print whole year  (4)Select a day  (5)Select a Month  (6)Quit\n");
				if(s.hasNextInt()) {
				c = s.nextInt();
				switch(c) {
					case 1: sales.printSalesRevenueDay(date);
							return;
					case 2:	sales.printSalesRevenueMonth(date);
							return;
					case 3:	sales.printSalesRevenueYear();
							return;
					case 4:	
							s.nextLine();
							System.out.println("Enter Date (dd/MM):");
							input = s.nextLine();
							try 
								{
								    ld = LocalDate.parse( input , f ) ;
								    sales.printSalesRevenueDay(ld);
								}
							catch ( DateTimeParseException e )
							{
							   System.out.println("Enter a valid date(dd/MM)!!");
							}
							return;
					case 5:
							s.nextLine();
							System.out.println("Enter Date (dd/MM):");
							input = s.nextLine();
							try 
								{
								    ld = LocalDate.parse( input , f ) ;
								    sales.printSalesRevenueMonth(ld);
								}
							catch ( DateTimeParseException e )
							{
							   System.out.println("Enter a valid date(dd/MM)!!");
							}
							return;
					case 6: return;
					default: System.out.println("Invalid Input!");
						
				}
			}
			else {
					System.out.println("Enter an integer!!!");
					s.next();
			}
		}
	}
	
	/**
	 * Allows user to operate the RRPSS of the restaurant
	 * @param menu Menu of the restaurant
	 * @param tables Tables in the restaurant
	 * @param SalesRecord Array list of orders placed in the restaurant
	 * @param staffs Array list of staffs working at the restaurant
	 */
	public static void start(Menu menu, TableManager tables, SaleRevenue sales, ArrayList<Staff> staffs) {
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
				if(s.hasNextInt()) {
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
							System.out.println("Table assigned:"+ tb);
							System.out.println("Enter Staff ID");
							if(s.hasNextInt()) {
								int ID = s.nextInt();
								s.nextLine();
								if(tb!=-1 && ID < staffs.size()) {
									table_orders.set(tb-1, new Order(cus,staffs.get(ID),tb));
								}
								else {
									System.out.println("Invalid input!");
								}
							}
							else {
								System.out.println("Enter an integer!!!");
								s.next();
							}
							break;
						case 4:
							int tb1 = tables.unAssignTable();
							if(table_orders.get(tb1-1).getCheckNo() !=0) {
								table_orders.get(tb1-1).printOrderInvoice();
								sales.addOrder(table_orders.get(tb1-1));
								table_orders.set(tb1-1, new Order(tb1-1));
							}
							break;
						case 5:
							System.out.println("Enter table number (1-10)");
							int tn2=0;
							do{
								if(s.hasNextInt()) {
									tn2 = s.nextInt();
									if(tn2<1 || tn2>10) {
										System.out.println("Please enter an integer between 1-10");
									}
								}
								else {
									System.out.println("Enter an integer!!!");
									s.next();
								}
							}while(tn2<1 || tn2>10);
							updateOrder(menu, table_orders.get(tn2-1));
							break;
						case 6:		
							printSalesrevenue(sales);
							break;
						case 7: System.out.println("Shutting down..");return;
							default:System.out.println("Invalid entry!");
					}
				}
				else {
					System.out.println("Enter an integer!!!");
					s.next();
				}
			}
		}

		catch(Exception e) {
			s.nextLine();
			System.out.println("Invalid input! Try again.");
		}
		finally {
			start(menu, tables, sales,staffs);
		}
	}
	/**
	 * User interface for RRPSS
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<Staff> staffs = new ArrayList<Staff>();
		int c=0;
		System.out.println("Input Staff members to roster!");
		do {
			staffs.add(new Staff());
			System.out.println("Enter -1 to stop adding staff to roster.");
			if(s.hasNextInt()) {
				c =s.nextInt();
			}
			else {
				System.out.println("Enter an integer!!!");
				s.next();
			}
		}while(c!=-1);
		Menu menu = new  Menu();
		ArrayList<Table> tableList = new ArrayList<Table>();
		SaleRevenue sales = new SaleRevenue();
		TableManager tables = new TableManager(tableList);
		start(menu, tables, sales,staffs);
	}
}
