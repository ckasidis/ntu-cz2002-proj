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
	
	//constructors
	
	/**
	 * Create a set of menu items to be sold 
	 * @param name Name of the set
	 * @param price Price of the set
	 * @param description Description of the set
	 * @param mi Array list of items in the set
	 */
	public Set(String name, double price, String description,ArrayList<MenuItem> mi) {
			this.name = name;
			this.price = price;
			this.description = description;
			menuItem = mi;
			item = TypeOfItem.SET;
	}

	//getters
	
	/**
	 * Get the name of the set 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get the price of the set
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Get the type of item (Set)
	 */
	public TypeOfItem getItemType() {
		return item;
	}

	//setters
	
	/**
	 * Set the name of the set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set the price of the set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Set the description of the set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	//methods
	
	/**
	 * Add a menu item to the set
	 * @param mi Menu item to be added in the set
	 */
	public void addItem(MenuItem mi) {
		menuItem.add(mi);
		sort(menuItem);
	}
	
	/**
	 * Remove a menu item from the set
	 * @param itemIndex Index of the item to be removed
	 * @return <code> true</code> if item removed from the set, <code>false</code> otherwise
	 */
	public boolean removeItem(int itemIndex) {
		if(menuItem.size() < 3) {
			System.out.println("Sets cannot have less than 2 items!");
			return false;
		}
		if(itemIndex < 0 || itemIndex > menuItem.size()-1) {
			System.out.println("Not from set");
			return false;
		}
		else {
			menuItem.remove(itemIndex);
			return true;
		}
	}
	
	/**
	 * Show the item number, name and price of items in the set
	 */
	public void showSet() {
		int i,j=0;
		System.out.println("\nSet:\n");
		System.out.print("DRINKS: \n");
		TypeOfItem item = TypeOfItem.values()[j];
		for(i=0;i<menuItem.size();i++) {
			
			while(menuItem.get(i).getItemType() != item && j<3) {
				item = TypeOfItem.values()[++j];
				System.out.printf("\n%sS: \n",item);
			}
			
			System.out.printf("Item %d: %s, $%.2f: ",i,menuItem.get(i).getName(),menuItem.get(i).getPrice());
			menuItem.get(i).printDescription();
			System.out.println();
		}
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
	 * Sorts the items in menu according to item enum type TypeOfItem
	 * @param menuItemList List of items in the menu
	 */
	private void sort(ArrayList<MenuItem> menuItemList) {
		int i, prev=0;
		if(menuItemList.size()<=1) return;
		for (TypeOfItem item : TypeOfItem.values()) {
			  for(i=0;i<menuItemList.size();i++) {
				  if(menuItemList.get(i).getItemType() == item) {
					  Collections.swap(menuItemList, i, prev);
					  prev++;
				  }
			  }
		}
	}
}
