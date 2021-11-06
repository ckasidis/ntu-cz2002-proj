package OODPg5;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {

	ArrayList<MenuItem> menuItem;

	Calendar c = Calendar.getInstance();

	private String customerName;
	private Staff staff;
	private MenuItem[] orderedItem;
	private Table table;
	private String strDate;


	public Order(String customerName, Staff staff, MenuItem[] orderedItem, Table table, String strDate) {
		this.customerName = customerName;
		this.staff = staff;
		this.orderedItem = orderedItem;
		this.table = table;

		Date date = c.getTime();
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy"); //Eg 13 April 2015
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM"); //Eg 13 April
		DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy); //Eg 13-4-2015
		this.strDate = dateFormat.format(date);
	}


	public void addOrder(MenuItem) {
		menuItem.add(MenuItem);
	}

	public void removeOrder(MenuItem) {
		menuItem.remove(MenuItem);
	}

	public void viewOrder() {
		int temp = 1;
		for (int i=0;i<menuItem.size();i++) {
			System.out.println("Order #"+temp+" is "+menuItem.get(i).getName);
		}
	}

	public void printOrderInvoice() {
		int temp = 1;
		int sum = 0;

		System.out.println("Table No. "+ table);
		System.out.println("Time: "+ c.get(Calendar.DATE)+"/"+ c.get(Calendar.MONTH)+"/"+c.get(Calendar.YEAR)+" --- "+ c.get(Calendar.HOUR)+":"+c.get(Calendar.MINUTE))
		System.out.println("Your waiter for today is: "+ staff);

		System.out.println("");

		for (int i=0;i<menuItem.size();i++) {
			sum = sum + menuItem.get(i).getPrice;
			System.out.println("Order #" + temp + " is " + menuItem.get(i).getName() + " --- $"+ menuItem.get(i).getPrice());
			temp++;
		}

		System.out.println("");

		System.out.println("Total bill: $"+sum);
		System.out.println("Thank you, "+ customerName + ", for dining here.");

		/** Example Invoice
		Table no: xx
		Time: date/month/year --- hour/minutes
		Waiter: yyy

		Order #z abc ---$xx
		Order #z abc ---$xx
		Order #z abc ---$xx

		Total bill: $xxx
		Thank you, xyz, for dining here.
		**/

	}

	public int getTableNum() {
		return table;
	}

}
