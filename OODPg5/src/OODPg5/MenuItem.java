package OODPg5;
/**
 * Interface for the different types of menu items
 * @author Group 5
 *
 */
public interface MenuItem{
	
	//getters
	
	/**
	 * Get the name of the menu item
	 * @return Name of the menu item
	 */
	public String getName();
	
	/**
	 * Get the price of the menu item
	 * @return Price of the menu item
	 */
	public double getPrice();
	
	/**
	 * Get the type of menu item 
	 * @return Type of the menu item
	 */
	public TypeOfItem getItemType();
	
	//setters
	
	/**
	 * Set the name of the menu item
	 * @param name Name of the menu item
	 */
	public void setName(String name);
	
	/**
	 * Set the price of the menu item
	 * @param price Price of the menu item
	 */
	public void setPrice(double price);

	/**
	 * Set the description of the menu item
	 * @param description Description of the menu item
	 */
	public void setDescription(String description);
	
	//methods
	
	/**
	 * Print the description of the menu item
	 */
	public void printDescription();
	
	/**
	 * Creates a menu item to be sold
	 * @return Menu item sold
	 */
	public MenuItem toOrder();
}
