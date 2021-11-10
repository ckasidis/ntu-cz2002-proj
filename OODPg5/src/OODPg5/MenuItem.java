package OODPg5;
/**
 * Interface for different type of Menu Items
 * @author Group 5
 *
 */
public interface MenuItem{
	/**
	 * Get the name of the menu item
	 * @return Name of the menu item
	 */
	public String getName();
	/**
	 * Set the name of the menu item
	 * @param name Name of the menu item
	 */
	public void setName(String name);
	/**
	 * Get the price of the menu item
	 * @return Price of the menu item
	 */
	public double getPrice();
	/**
	 * Set the price of the menu item
	 * @param price Price of the menu item
	 */
	public void setPrice(double price);
	/**
	 * Get the type of menu item 
	 * @return Type of the menu item
	 */
	public TypeOfItem getItemType();
	/**
	 * Print the description of the menu item
	 */
	public void printDescription();
	/**
	 * Set the description of the menu item
	 * @param description Description of the menu item
	 */
	public void setDescription(String description);
	/**
	 * Creates a menu item to be sold
	 * @return Menu item sold
	 */
	public MenuItem toOrder();
}
