package OODPg5;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * Represents a customer to the restaurant
 * @author Group 5
 *
 */
public class Customer {
	/**
	 * Name of the customer
	 */
	private String name;
	
	/**
	 * 8-digit contact number of customer
	 */
	private long contact;
	/**
	 * Array list of entitled discounts
	 */
	private ArrayList<Discount> discount = new ArrayList<Discount>();
	
	/**
	 * Creates a customer with user input of name, contact number and discount
	 */
	Customer(){//constructor
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter name of customer:");//get name and contacts for customer
		name = sc.nextLine();
		System.out.println("Enter contact number:");
		while(!sc.hasNextLong()){
			System.out.println("Enter a number!!!");
			sc.next();
		}
		while((contact = sc.nextLong())<9999999 || contact >99999999) {
			System.out.println("Enter an 8 digit number!");
			while(!sc.hasNextLong()){
				System.out.println("Enter a number!!!");
				sc.next();
			}
		}
		System.out.println("Enter 'Y' if customer has discount.(Enter any other input if no discount)");
		sc.nextLine();
		char c;
		if((c = sc.nextLine().charAt(0)) == 'y' || c == 'Y') {
			System.out.println("Enter discount amount");
			double d;
			while(!sc.hasNextDouble()){
				System.out.println("Enter a decimal between (0-1)!");
				sc.next();
			}
			while(( d= sc.nextDouble())<0 || d >1) {
				System.out.println("Enter a decimal between (0-1)!");
				while(!sc.hasNextDouble()){
					System.out.println("Enter a decimal between (0-1)!");
					sc.next();
				}
			}
			discount.add(new Discount(d));
		}
		
	}
	
	/**
	 * Creates a customer
	 * @param n Name of the customer
	 * @param c 8-digit contact number of customer
	 */
	Customer(String n,long c){
		name = n;
		contact = c;
	}
	
	/**
	 * Set the discount entitled to customer
	 * @param d Discount entitled to customer
	 */
	public void setDiscount(Discount d) {
		discount.add(d);
	}
	
	/**
	 * Get the discount entitled to customer
	 * @param price Original price
	 * @return Discount entitled to customer
	 */
	public double getDiscount(double price) {
		if(discount.size()==0) {
			System.out.printf("         <NO MEMBER DISCOUNTS>\n");
			return 0;
		}
		double highest_discount=0;
		for(int i=0;i<discount.size();i++) {
			if(discount.get(i).getDiscount()>highest_discount)
				highest_discount = discount.get(i).getDiscount();
		}
		return highest_discount*price;
	}
	
	/**
	 * Get the name of the customer
	 * @return Name of the customer
	 */
	public String getName() {
		return name;
	}
	/**
	 * Set the name of the customer
	 * @param n Name of the customer
	 */
	public void setName(String n) {
		name = n;
	}
	
	/**
	 * Get the 8-digit contact number of customer
	 * @return 8-digit contact number of customer
	 */
	public long getContactNo() {
		return contact;
	}
	
	/**
	 * Set the 8-digit contact number of customer
	 * @param c 8-digit contact number of customer
	 */
	public void setContact(long c) {
		if(c<10000000 && c >99999999)
			contact = c;
		else
			System.out.println("invalid contacts!");
	}
}
