package OODPg5;
/**
 * Represents a discount rate given to customer
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
	 * Creates a discount rate of 0.01
	 */
	public Discount() {
		discount = 0.01;
	}
	
	/**
	 * Creates a discount rate to be given to customer
	 * @param discount Discount rate to be given to customer
	 */
	public Discount(double discount) {
		this.discount = discount;
	}
	
	//getters
	
	/**
	 * Get the discount rate to be given to customer
	 * @return Discount rate to be given to customer
	 */
	public double getDiscount() {
		return discount;
	}
	
	//setters
	
	/**
	 * Set the discount rate to be given to customer
	 * @param discount Discount rate to be given to customer
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
