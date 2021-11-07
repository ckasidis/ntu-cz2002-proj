package OODPg5;
/**
 * Represents a main course item sold in the restaurant
 * 
 * @author Group 5
 *
 */
public class MainCourse implements MenuItem {
	/**
	 * Name of the main course
	 */
	private String name;
	/**
	 * Price of the main course
	 */
	private double price;
	/**
	 * Description of the main course
	 */
	private String description;
	/**
	 * Type of the item (Main Course)
	 */
	private TypeOfItem item;
	
	/**
	 * Creates a main course item to be sold
	 * @param name Name of the main course
	 * @param price Price of the main course
	 * @param description Description of the main course
	 */
	public MainCourse (String name, double price, String description ) {
			this.name = name;
			this.price = price;
			this.description = description;
			item = TypeOfItem.MAINCOURSE;
	}
	
	/**
	 * Print the description of the main course
	 */
	public void printDescription() {
		System.out.println(this.description);
	}
	
	//getters
	/**
	 * Get the name of the main course
	 */
	public String getName() {
		return name;
	}
	/**
	 * Get the price of the main course
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Get the type of item (Main Course)
	 */
	public TypeOfItem getItemType() {
		return item;
	}
	
	//setters
	/**
	 * Set the name of the main course
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the price of the main course
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Set the description of the main course
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
