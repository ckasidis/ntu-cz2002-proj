package OODPg5;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
	static int checkNo =0;
	private ArrayList<MenuItem> orderItem;

	private Customer customer;
	private Staff staff;
	private int table_no;
	private int cNumber;
	private double finalCheck;

//An order should indicate the staff who created the order.
//Order invoice can be printed to list the order details (eg, table number, timestamp)
//	and a complete breakdown of order items details with taxes details.
	
	public Order(Customer customer, Staff staff, int tableNo) {
		this.customer = customer;
		this.staff = staff;
		table_no = tableNo;
		cNumber = checkNo++;
		finalCheck =0;
	}
	public void addOrder(MenuItem mi) {
		orderItem.add(mi);
		sort(orderItem);		
	}
	public void removeOrder(MenuItem mi) {
		orderItem.remove(mi);
	}
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
	public int getTableNum() {
		return table_no;
	}
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
		finalCheck = temp-customer.getDiscount(sum);
		System.out.printf("                    TOTAL:    $%.2f SGD\n", finalCheck);
		System.out.println("-----------------------------------------");
		System.out.println("*****************************************");
		System.out.println("**    Thank you for dining with us!    **");
		System.out.println("*****************************************");

	}

}
