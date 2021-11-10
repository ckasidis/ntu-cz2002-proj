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
	static int checkNo =1;
	/**
	 * Array list of items ordered
	 */
	private ArrayList<MenuItem> orderItem;
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
	 * Total to be paid by customer
	 */
	private double finalPrice;
	private LocalDate date;
//An order should indicate the staff who created the order.
//Order invoice can be printed to list the order details (eg, table number, timestamp)
//	and a complete breakdown of order items details with taxes details.
	/**
	 * Create an order
	 * @param customer Customer that placed the order
	 * @param staff Staff facilitating the order
	 * @param tableNo Table number of table assigned for the order
	 */
	public Order(int tableNo) {
		customer =new Customer("Empty",9999999);
		table_no = tableNo;
		cNumber = 0;
		finalPrice =0;
		date = LocalDate.now();
	}
	public Order(Customer customer, Staff staff, int tableNo) {
		this.customer = customer;
		this.staff = staff;
		table_no = tableNo;
		cNumber = checkNo++;
		finalPrice =0;
	}
	/**
	 * Add item to the list of order items
	 * @param mi Item to be added into the order
	 */
	public void addOrder(MenuItem mi) {
		orderItem.add(mi);
		sort(orderItem);		
	}
	public LocalDate getDate() {
		return date;
	}
	public int getCheckNo() {
		return cNumber;
	}
	public double getfinalPrice() {
		return finalPrice;
	}
	public void addOrderItem(MenuItem mi) {
		orderItem.add(mi);
		sort(orderItem);		
	}

	/**
	 * Remove item from the list of order items
	 * @param mi Item to be removed from the order
	 */
	public void removeOrderItem() {
		Scanner s = new Scanner(System.in);
		int temp =1;
		for(MenuItem oi: orderItem) {
			System.out.printf("Order #%d:  %s \t %.2f",temp++,oi.getName(),oi.getPrice());
		}
		System.out.println("Enter order number of item to remove");
		if(s.hasNextInt()) {
			int itemIndex = s.nextInt();
			if(itemIndex < 1 || itemIndex > orderItem.size()) {
				System.out.println("Item not in menu.");
				return;
			}
			orderItem.remove(itemIndex-1);
		}
		else {
			System.out.println("Enter an integer!!!");
			s.next();
			return;
		}
	}
	
	/**
	 * View all the items ordered
	 */
	public void viewOrder() {
		int temp = 1, count=0;
		MenuItem tm = orderItem.get(0);
		for (int i=1;i<orderItem.size();i++){
			if(tm != orderItem.get(i)) {
				System.out.printf("Order #%d: %d %s \t %.2f",temp++,count,orderItem.get(i).getName(),orderItem.get(i).getPrice());
				count =0;
				tm = orderItem.get(i);
			}
			count++;
		}
	}
	
	/**
	 * Get the table number of table assigned for the order
	 * @return Table number of table assigned for the order
	 */
	public int getTableNum() {
		return table_no;
	}
	
	/**
	 * Sort the items in the list 
	 * @param menuItemList List of items
	 */
	private void sort(ArrayList<MenuItem> menuItemList) {
		int prev;
		if(menuItemList.size()<=1) return;
		for (int i=0;i<orderItem.size()-1;i++) {
			prev = i+1;
			for (int j=i+1; j<orderItem.size();j++) {
				if(orderItem.get(i) == orderItem.get(j)) {
					Collections.swap(orderItem, prev, j);
				}
			  }
		}
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
		System.out.printf("                 Subtotal:    $%.2f SGD\n", temp = sum);
		System.out.printf("         10% SERVICE CHRG:    $%.2f SGD\n", sum*0.1);
		temp = temp + sum*0.1;
		System.out.printf("         MEMBER DISCOUNTS:    $%.2f SGD\n", customer.getDiscount(sum));
		System.out.println("-----------------------------------------");
		finalPrice = temp-customer.getDiscount(sum);
		System.out.printf("                    TOTAL:    $%.2f SGD\n", finalPrice);
		System.out.println("-----------------------------------------");
		System.out.println("*****************************************");
		System.out.println("**    Thank you for dining with us!    **");
		System.out.println("*****************************************");

	}

}
