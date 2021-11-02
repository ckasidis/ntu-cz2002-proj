package rrps;
import java.util.ArrayList;

public class Customer {
	private String name;
	private ArrayList<Discount> discount = new ArrayList<Discount>();
	Customer(String name){
		name = this.name;
	}
	public void setDiscount(Discount discount) {
		discount.add(discount);
	}
	public double getDiscount(double price) {
		if(discount.size()==0) return 0;
		double highest_discount=0;
		for(int i=0;i<discount.size();i++) {
			if(discount.get(i).getDiscount()>highest_discount)
				highest_discount = discount.get(i).getDiscount();
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
