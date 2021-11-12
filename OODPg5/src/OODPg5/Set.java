package OODPg5;
import java.util.ArrayList;

/**
 * Represents a set of items sold in the restaurant
 * @author Group 5
 *
 */
public class Set implements MenuItem {
	/**
	 * Name of the set
	 */
	private String name;
	/**
	 * Price of the set
	 */
	private double price;
	/**
	 * Description of the set
	 */
	private String description;
	/**
	 * Type of the item (Set)
	 */
	private TypeOfItem item;
	/**
	 * Array list of items in the set
	 */
	private ArrayList<MenuItem> menuItem;
	/**
	 * Create a set of menu items to be sold 
	 * @param name Name of the set
	 * @param price Price of the set
	 * @param description Description of the set
	 * @param mi Array list of items in the set
	 */
	public Set (String name, double price, String description,ArrayList<MenuItem> mi) {
			this.name = name;
			this.price = price;
			this.description = description;
			menuItem = mi;
			item = TypeOfItem.SET;
	}
	/**
	 * Creates a set of menu items to be sold
	 */
	public MenuItem toOrder() {
		 ArrayList<MenuItem> setcopy = new ArrayList<MenuItem>();
		for(MenuItem item: menuItem) {
			setcopy.add(item.toOrder());
		}
		Set mi= new Set(name,price,description,setcopy);
		return mi;
	}
	/**
	 * Get the name of the set 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the name of the set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the price of the set
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Add an item to the set
	 * @param mi Array list of items in the set
	 */
	public void addItem(MenuItem mi) {
		menuItem.add(mi);
	}
	
	/**
	 * Remove an item from the set
	 * @param itemIndex Index of the item to be removed
	 * @return TRUE if item removed from the set
	 */
	public boolean removeItem(int itemIndex) {
		if(menuItem.size() - 1 < 3) {
			System.out.println("Sets cannot have less than 2 items!");
			return false;
		}
		if(itemIndex < 0 || itemIndex > menuItem.size()) {
			System.out.println("Not from set");
			return false;
		}
		else {
			menuItem.remove(itemIndex);
			return true;
		}
	}
	
	/**
	 * Show the items of the set
	 */
	public void showSet() {
		int i,j=0;
		System.out.println("\nSet:\n");
		System.out.print("DRINKS: \n");
		TypeOfItem item = TypeOfItem.values()[j];
		for(i=0;i<menuItem.size();i++) {
			
			while(menuItem.get(i).getItemType() != item) {
				item = TypeOfItem.values()[++j];
				System.out.printf("\n%sS: \n",item);
			}
			
			System.out.printf("Item %d: %s, $%.2f: ",i,menuItem.get(i).getName(),menuItem.get(i).getPrice());
			menuItem.get(i).printDescription();
			System.out.println();
		}
	}
	
	/**
	 * Set the price of the set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Print the description of the set
	 */
	public void printDescription() {
		System.out.println(description);
		System.out.println("Promotional set consists of: ");
		for(int i=0;i<menuItem.size();i++) {
			System.out.printf("-> %s : ",menuItem.get(i).getName());
			menuItem.get(i).printDescription();
		}
		System.out.println("");
	}
	
	/**
	 * Get the type of item (Set)
	 */
	public TypeOfItem getItemType() {
		return item;
	}
	/**
	 * Set the description of the set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
