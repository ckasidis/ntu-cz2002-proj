package OODPg5;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class RRPSS {
	static Scanner s = new Scanner(System.in);
	static final boolean showSet = true;
	private Table[] table;
	
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
	public static void  order(Staff staff) {
		
	}

	public static void orderInvoice(int tableNumber) {
		
	}
	public static void printSalesRevenueReport() {
		
	}
	public static void start(Menu menu, TableManager tables) {
		int choice;
		
		ArrayList<Order> Total_orders = new ArrayList<Order>();
	///	for(int i=0;i<10<i++){
	//		Total_orders.add(new Order(i+1, date))} // assuming order(TableNumber, date)
		ArrayList<Order> finished_orders = new ArrayList<Order>();
		try {
			while(true) {
				LocalDateTime date = LocalDateTime.now();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E dd/MM/yyyy HH:mm:ss");
				System.out.println("Welcome to Restaurant Reservation and Point of Sale System");
				System.out.println("Date:" + date.format(dtf));
				System.out.println("1: Open Menu");
				System.out.println("2: Table Manager");
				System.out.println("3: Take Order");
				System.out.println("4: Update order");
				System.out.println("5: Print order invoice");
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
						System.out.println("Enter table number (1-10)");
						int tn;
						while((tn = s.nextInt())<1 || tn>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						//Total_order.get(tn-1).setdetails(date,customer);
						break;
					case 4:
						System.out.println("Enter table number (1-10)");
						int tn1;
						while((tn1 = s.nextInt())<1 || tn1>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						//Total_order.get(tn1-1).addMenuItem(menuItem));
						break;
					case 5:
						System.out.println("Enter table number (1-10)");
						int tn2;
						while((tn2 = s.nextInt())<1 || tn2>10) {
							System.out.println("Please enter an integer between 1-10");
						}
						//finished_orders.add(Total_order.get(tn2-1));
						//Total_order.printSalesInvoice();
						//Total_order.remove(tn2-1);
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
			start(menu, tables);
		}
	}
	public static void main(String[] args) {
		Menu menu = new  Menu();
		ArrayList<Table> tableList = new ArrayList<Table>();
		
		TableManager tables = new TableManager(tableList);
		start(menu, tables);
	}
}
