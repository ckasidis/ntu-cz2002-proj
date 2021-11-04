package OODPg5;

import java.util.ArrayList;

public class Customer {
	private String name;
	private int contactNo;
	private ArrayList<Discount> discount = new ArrayList<Discount>();
	
	//constructor
	Customer(String name, int contactNo) {
		this.name = name;
		this.contactNo = contactNo;
	}
	
	//getters
	public String getName() {return name;}
	public int getContactNo() {return contactNo;}
	public double getDiscount(double price) {
		if(discount.size()==0) return 0;
		double highest_discount=0;
		for(int i=0;i<discount.size();i++) {
			if(discount.get(i).getDiscount()>highest_discount)
				highest_discount = discount.get(i).getDiscount();
		}
		return highest_discount;
	}
	
	//setters
	public void setName(String name) {this.name = name;}
	public void setDiscount(Discount disct) {discount.add(disct);}

}
