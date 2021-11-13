package OODPg5;
/**
 * Represents a discount given to customer
 * @author Group 5
 *
 */
public class Discount{	
	/**
	 * Discount given to customer
	 */
	private Double discount;
	
	//constructors
	
	/**
	 * Creates a discount of 0.01
	 */
	public Discount() {
		discount = 0.01;
	}
	
	/**
	 * Creates a discount to be given to customer
	 * @param discount Discount to be given to customer
	 */
	public Discount(double discount) {
		this.discount = discount;
	}
	
	//getters
	
	/**
	 * Get the discount to be given to customer
	 * @return Discount to be given to customer
	 */
	public double getDiscount() {
		return discount;
	}
	
	//setters
	
	/**
	 * Set the discount to be given to customer
	 * @param discount Discount to be given to customer
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
