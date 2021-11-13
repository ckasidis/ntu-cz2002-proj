package OODPg5;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
/**
 * Represents the Restaurant Reservation and Point of Sale System of the restaurant
 * @author Group 5
 *
 */
public class RestaurantManager {
	static Scanner sc = new Scanner(System.in);
	
	private static final boolean showSet = true;
	
	//methods
	
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
				System.out.println("2: Check Table Availability and Reservations");
				System.out.println("3: Add or Remove Reservation");
				System.out.println("4: Assign table");
				System.out.println("5: Unassign table");
				System.out.println("6: Update order");
				System.out.println("7: Print Sales Revenue Report");
				System.out.println("8: Shut down! <all data will be lost>");
				if(sc.hasNextInt()) {
					choice = sc.nextInt();
					switch(choice) {
						case 1: 
							showMenu(menu);
							break;
						case 2:
							tables.checkTableStatus();
							break;
						case 3:
							tables.manageReservations();
							break;
						case 4:
							Customer cus = new Customer();
							int tb = tables.assignTable(cus);
							if (tb == -1) break;
							System.out.printf("\n");
							while (true) {
								System.out.println("Staff on duty today:");
								for(int i=0;i<staffs.size();i++) {
									System.out.println("->" + staffs.get(i).getName() + ", ID = " + staffs.get(i).getEmployeeID() + ", " + staffs.get(i).getJobTitle());
								}
								System.out.println("Enter Staff ID");
								if(sc.hasNextInt()) {
									int ID = sc.nextInt();
									if(tb!=-1 && ID < staffs.size() && ID>-1) {
										table_orders.set(tb-1, new Order(cus,staffs.get(ID),tb));
										System.out.printf("Table %d assigned successfully.\n",tb);
										break;
									}
									else {
										System.out.println("Invalid input! Try again");
										sc.next();
									}
								}
								else {
									System.out.println("Enter an integer!!! Try again");
									sc.next();
								}
							}
							break;
						case 5:
							System.out.println("Occupied Tables:");
							for(Order o: table_orders) {
								if(o.getCheckNo()!=0) {
									System.out.printf("Table number: %d, Customer name: %s \n",o.getTableNum(),o.getCustomer().getName());
								}
							}
							int tb1 = tables.unAssignTable();
							if(table_orders.get(tb1-1).getCheckNo() !=0) {
								table_orders.get(tb1-1).printOrderInvoice();
								sales.addOrder(table_orders.get(tb1-1));
								table_orders.set(tb1-1, new Order(tb1-1));
							}
							break;
						case 6:
							System.out.println("Occupied Tables:");
							for(Order o: table_orders) {
								if(o.getCheckNo()!=0) {
									System.out.printf("Table number: %d, Customer name: %s \n",o.getTableNum(),o.getCustomer().getName());
								}
							}
							System.out.println("Enter table number (1-10)");
							int tn2=0;
							do{
								if(sc.hasNextInt()) {
									tn2 = sc.nextInt();
									if(tn2<1 || tn2>10) {
										System.out.println("Please enter an integer between 1-10");
									}
								}
								else {
									System.out.println("Enter an integer!!!");
									sc.next();
								}
							} while (tn2<1 || tn2>10);
							
							if (tables.getTableList().get(tn2-1).getCustomer() == null) {
								System.out.println("No customer on this table!");
								break;
							}

							updateOrder(menu, table_orders.get(tn2-1));
							break;
						case 7:		
							printSalesRevenue(sales);
							break;
						case 8: System.out.println("Shutting down..");return;
							default:System.out.println("Invalid entry!");
					}
				}
				else {
					System.out.println("Enter an integer!!!");
					sc.next();
				}
			}
		}
		catch(Exception e) {
			System.out.println("Invalid input! Try again.");
			System.out.println("Rebooting..");
			start(menu, tables, sales, staffs);
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
				if(sc.hasNextInt()) {
					int c = sc.nextInt();
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
				sc.next();
			}
		}
	}
	
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
				if(sc.hasNextInt()) {
					c = sc.nextInt();
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
					sc.next();
				}
		}

	}
	
	/**
	 * Print sales revenue of the restaurant for a day, a month or a year
	 * @param sales Sale revenue record of the restaurant
	 */
	private static void printSalesRevenue(SaleRevenue sales) {

		int c;

		LocalDate ld;
		DateTimeFormatter f = DateTimeFormatter.ofPattern("dd/MMM/yyyy"); ;
		String inputDate;
		int inputMnth;
		
			while(true) {
				System.out.printf("========= SELECT OPTION =========\n(1)Print today  (2)Print this month  (3)Print whole year  (4)Select a day  (5)Select a Month  (6)Quit\n");
				System.out.println("Records up to 1 year");
				if(sc.hasNextInt()) {
					c = sc.nextInt();
					switch(c) {
						case 1: sales.printSalesRevenueDay(LocalDate.now());
								return;
						case 2:	sales.printSalesRevenueMonth(LocalDate.now().getMonthValue());
								return;
						case 3:	sales.printSalesRevenueYear();
								return;
						case 4:	
								sc.nextLine();
								System.out.println("Enter Date (eg 11/Jan/2001):");
								inputDate = sc.nextLine();
								try 
									{
									    ld = LocalDate.parse(inputDate,f) ;
									    sales.printSalesRevenueDay(ld);
									}
								catch ( DateTimeParseException e )
								{
								   System.out.println("Enter a valid date(eg 11/Jan/2001)!!");
								}
								return;
						case 5:
								System.out.println("Enter Month (1-12):");
								while(!sc.hasNextInt()){
									System.out.println("Enter an integer!!!");
									sc.next();
								}
								while ((inputMnth = sc.nextInt()) < 1 || inputMnth > 12) {
									System.out.println("Please enter an integer between 1-12");
									while(!sc.hasNextInt()){
										System.out.println("Enter an integer!!!");
										sc.next();
									}
								}
								try 
									{
									    ;
									    sales.printSalesRevenueMonth(inputMnth);
									}
								catch ( DateTimeParseException e )
								{
								   System.out.println("Enter Date (eg 11/Jan/2001):");
								}
								return;
						case 6: return;
						default: System.out.println("Invalid Input!");
					}
				}
				else {
					System.out.println("Enter an integer!!!");
					sc.next();
				}
			}
		}
	
}
