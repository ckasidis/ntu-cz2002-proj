package rrps;
import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Discount> discounts = new ArrayList<Discount>();
	Customer(String name){
		name = this.name;
	}
	public void setDiscount(Discount d) {
		discounts.add(d);
	}
	public double getDiscounts(double price) {
		if(discounts.size()==0) return 0;
		double highest_discount=0;
		for(int i=0;i<discounts.size();i++) {
			if(discounts.get(i).getDiscount()>highest_discount)
				highest_discount = discounts.get(i).getDiscount();
		}
		return highest_discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		name = this.name;
	}
}
