package OODPg5;
/**
 * Represents a discount given to customer
 * @author Group 5
 *
 */
public class Discount{	
	/**
	 * Discount given 
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
	 * Creates a discount
	 * @param discount Discount to be given 
	 */
	public Discount(double discount) {
		this.discount = discount;
	}
	
	//getters
	
	/**
	 * Get the discount 
	 * @return Discount 
	 */
	public double getDiscount() {
		return discount;
	}
	
	//setters
	
	/**
	 * Set the discount
	 * @param discount Discount to be given
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
