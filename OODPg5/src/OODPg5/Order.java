package OODPg5;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Represents an order in the restaurant
 * @author Group 5
 *
 */
public class Order {
	/**
	 * Scanner to scan user input
	 */
	Scanner sc = new Scanner(System.in);
	/**
	 * Initialized check number of the order
	 */
	static int checkNo =1;
	
	/**
	 * Array list of items ordered
	 */
	private ArrayList<MenuItem> orderItem = new ArrayList<MenuItem>();
	
	/**
	 * Customer that placed the order
	 */
	private Customer customer;
	
	/**
	 * Staff facilitating the order
	 */
	private Staff staff;
	
	/**
	 * Table number of table assigned for the order
	 */
	private int table_no;
	
	/**
	 * Check number of the order
	 */
	private int cNumber;
	
	/**
	 * Total price of order to be paid by customer
	 */
	private double finalPrice;
	
	/**
	 * Date of order placed
	 */
	private LocalDate date;
	
	//constructors
	
	/**
	 * Create an empty order.
	 *  <code>cNumber</code> = 0 identifies the order as a dummy order
	 * @param tableNo Table number of table assigned for the order
	 */
	public Order(int tableNo) {
		customer =new Customer("Empty",9999999);
		table_no = tableNo;
		cNumber = 0;
		finalPrice =0;
		date = LocalDate.now();
	}
	
	/**
	 * Create an order
	 * @param customer Customer that placed the order
	 * @param staff Staff facilitating the order
	 * @param tableNo Table number of table assigned for the order
	 */
	public Order(Customer customer, Staff staff, int tableNo) {
		this.customer = customer;
		this.staff = staff;
		table_no = tableNo;
		cNumber = checkNo++;
		finalPrice =0;
		date = LocalDate.now();
	}

	//getters

	/**
	 * Get the customer that placed the order
	 * @return Customer that placed the order
	 */
	public Customer getCustomer() {
		return customer;
	}
	
	/**
	 * Get the table number of table assigned for the order
	 * @return Table number of table assigned for the order
	 */
	public int getTableNum() {
		return table_no;
	}
	
	/**
	 * Get the check number of the order
	 * @return Check number of the order
	 */
	public int getCheckNo() {
		return cNumber;
	}
	
	/**
	 * Get the total to be paid by customer
	 * @return Total to be paid by customer
	 */
	public double getfinalPrice() {
		return finalPrice;
	}
	
	/**
	 * Get the date of order placed
	 * @return Date of order placed
	 */
	public LocalDate getDate() {
		return date;
	}
	
	//methods
	
	
	/**
	 * Add menu item to the order list
	 * @param mi Menu item to be added to the order
	 */
	public void addOrderItem(MenuItem mi) {
		orderItem.add(mi);
		sort(orderItem);		
	}

	/**
	 * Remove menu item from the order list 
	 * @param mi Menu item to be removed from the order
	 */
	public void removeOrderItem() {
		int temp =1;
		for(MenuItem oi: orderItem) {
			System.out.printf("Order #%d:  %s \t %.2f\n",temp++,oi.getName(),oi.getPrice());
		}
		System.out.println("Enter order number of item to remove");
		if(sc.hasNextInt()) {
			int itemIndex = sc.nextInt();
			if(itemIndex < 1 || itemIndex > orderItem.size()) {
				System.out.println("Item not in menu.");
				return;
			}
			orderItem.remove(itemIndex-1);
		}
		else {
			System.out.println("Enter an integer!!!");
			sc.next();
			return;
		}
	}
	
	/**
	 * View all the items ordered.
	 * Prints number of item ordered, item name and price of each item
	 */
	public void viewOrder() {
		int temp = 1, count=1,i;
		if(orderItem.size()==0) {
			System.out.println("Order empty");
			return;
		}
		double price=0;
		String tm = orderItem.get(0).getName();
		for (i=1;i<orderItem.size();i++){
			price = price+ orderItem.get(i-1).getPrice();
			if(tm != orderItem.get(i).getName()) {
				System.out.printf("Order #%d: %d %-15s    $%3.2f\n",temp++,count,orderItem.get(i-1).getName(),price);
				price =0;
				count =0;
				tm = orderItem.get(i).getName();
			}
			count++;
		}
		price = price+ orderItem.get(i-1).getPrice();
		System.out.printf("Order #%d: %d %-15s    $%3.2f\n",temp++,count,orderItem.get(i-1).getName(),price);
	}
	
	/**
	 * Print the order invoice
	 */
	public void printOrderInvoice() {
		LocalDateTime date = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("E dd/MM/yyyy HH:mm");
		double temp = 1;
		double sum = 0;
		System.out.println("            Restaurant RRPSS             ");
		System.out.println("*****************************************");
		System.out.println("check No." + cNumber);
		System.out.println("Server: " + staff.getName() + "        Table:" + table_no);
		System.out.println(date.format(dtf));
		System.out.println("-----------------------------------------");
		viewOrder();
		System.out.println("-----------------------------------------");
		for(MenuItem mi: orderItem) {
			sum = sum + mi.getPrice();
		}
		temp = sum;
		System.out.printf("                 Subtotal:    $%.2f SGD\n", sum);
		System.out.println("-----------------------------------------");
		double svc = temp*0.1;
		System.out.printf("         10%% SERVICE CHRG:    $%.2f SGD\n", svc);
		temp = temp + svc;
		System.out.printf("                              $%.2f SGD\n", temp);
		System.out.println("-----------------------------------------");
		double tax = temp*0.07;
		System.out.printf("                   7%% GST:    $%.2f SGD\n", tax);
		temp = temp + tax;
		System.out.printf("                              $%.2f SGD\n", temp);
		System.out.println("-----------------------------------------");
		double disc = customer.getDiscount(sum);
		System.out.printf("         MEMBER DISCOUNTS:    $%.2f SGD\n", disc);
		finalPrice = temp-disc;
		System.out.printf("                    TOTAL:    $%.2f SGD\n", finalPrice);
		System.out.println("-----------------------------------------");
		System.out.println("*****************************************");
		System.out.println("**    Thank you for dining with us!    **");
		System.out.println("*****************************************");
	}

	/**
	 * Sort the list of menu items and group identical menu items together
	 * @param menuItemList List of menu items
	 */
	private void sort(ArrayList<MenuItem> menuItemList) {
		int prev=0;
		if(menuItemList.size()<=1) return;
		String order;
		for (int i=0;i<menuItemList.size()-1;i=prev) {
			order = menuItemList.get(i).getName();
			prev = i+1;
			for (int j=i+1; j<menuItemList.size();j++) {
				if(order == menuItemList.get(j).getName()) {
					Collections.swap(menuItemList, prev, j);
					prev++;
				}
			  }
		}
	}
	
}
