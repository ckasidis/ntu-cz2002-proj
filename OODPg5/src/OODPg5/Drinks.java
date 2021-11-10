package OODPg5;
/**
 * Represents a drink sold in the restaurant
 * 
 * @author Group 5
 *
 */
public class Drinks implements MenuItem{
	/**
	 * Type of the item (Drinks)
	 */
	private TypeOfItem item;
	/**
	 * Name of the drink
	 */
	private String name;
	/**
	 * Price of the drink
	 */
	private double price;
	/**
	 * Description of the drink
	 */
	private String description;
	
	/**
	 * Creates a drink to be sold
	 * @param name Name of the drink
	 * @param price Price of the drink
	 * @param description Description of the drink
	 */
	public Drinks (String name, double price, String description ) {
			this.name = name;
			this.price = price;
			this.description = description;
			item = TypeOfItem.DRINK;
	}
	/**
	 * Creates a dessert to be sold
	 */
	public MenuItem toOrder() {
		Drinks mi= new Drinks(name,price,description);
		return mi;
	}
	/**
	 * Print description of the drink
	 */
	public void printDescription() {
		System.out.println(this.description);
	}
	
	//getters
	/**
	 * Get the name of the drink
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the price of the drink
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Get the type of item (Drink)
	 */
	public TypeOfItem getItemType() {
		return item;
	}
	
	//setters
	/**
	 * Set the name of the drink
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the price of the drink
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Set the description of the drink
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
