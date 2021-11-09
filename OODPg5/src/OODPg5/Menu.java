package OODPg5;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
/**
 * Represents the menu of the restaurant
 * 
 * @author Group 5
 *
 */
public class Menu {
	/**
	 * Array list of the items in the menu
	 */
	ArrayList<MenuItem> menuItem;
	Scanner in = new Scanner(System.in);
	/**
	 * Creates a menu
	 */
	public Menu() {
		menuItem = new ArrayList<MenuItem>();
	}
	
	/**
	 * Options to edit the menu:
	 * Remove or update menu item
	 */
	public void editMenu() {
		
		System.out.println("Enter item number:");
		int num = in.nextInt();
		System.out.println("======= SELECT CHOICE =======\n(1)Remove item  (2)Update details");
		int choice = in.nextInt();
		switch(choice) {
		case 1:
			removeMenuItem(num);
			return;
		case 2:
			if(menuItem.get(num-1).getItemType() != TypeOfItem.SET)
				updateMenuItem(num);
			else
			updatePromotionSet(num);
			return;
		case 3:
			break;
		default:
			System.out.println("Invalid entry!");
			break;		
		}

	}
	public MenuItem getMenuItem() {
		System.out.println("Enter item number:");
		int item = in.nextInt();
		while(item < 0 || item > menuItem.size() - 1) {
			System.out.println("Item not in menu.");
		}
		return menuItem.get(item-1).toOrder();
	}
	/**
	 * Create a menu item using user input of type, name, price and description
	 */
	public void createMenuItem() { 
		String name;
		String description;
		double price;
		System.out.printf("====== SELECT ITEM TO CREATE ====== \n(1)Drink  (2)MainCourse  (3)Dessert\n");
		int choice = in.nextInt();
		in.nextLine();
		switch(choice) {
		case 1:
			System.out.println("Drink name: ");
			name = in.nextLine();
			System.out.print("Price of drink: \n$");
			price = in.nextDouble();
			in.nextLine();
			System.out.println("Description of drink: ");
			description = in.nextLine();
			menuItem.add(new Drinks(name, price, description));
			break;
		case 2:
			System.out.println("MainCourse name: ");
			name = in.nextLine();
			System.out.print("Price of Maincourse: \n$");
			price = in.nextDouble();
			in.nextLine();
			System.out.println("Description of Maincourse: ");
			description = in.nextLine();
			menuItem.add(new MainCourse(name, price, description));
			break;
		case 3:
			System.out.println("Dessert name: ");
			name = in.nextLine();
			System.out.print("Price of Dessert: \n$");
			price = in.nextDouble();
			in.nextLine();
			System.out.println("Description of dessert: ");
			description = in.nextLine();
			menuItem.add(new Dessert(name, price, description));
			break;
		default:
			System.out.println("Inavlid choice entered.");
			break;
		}
		if(choice>0 && choice<4) {
			sort(menuItem);
			System.out.println("Item successfully created!");
		}
	}
	
	/**
	 * Remove menu item 
	 * @param itemIndex Index of the item in the menu to be removed
	 */
	private void removeMenuItem(int itemIndex) {
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Item not in menu.");
		}
		else {
			menuItem.remove(itemIndex);
			sort(menuItem);
			System.out.println("Item is removed.");
		}
	}
	
	/**
	 * Update name, price or description of the menu item
	 * @param itemIndex Index of the item in the menu to be updated
	 */
	private void updateMenuItem (int itemIndex) {
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Promotional set not in menu.");
			return;
		}
		else {
			System.out.printf("========= SELECT UPDATE =========\n(1)Name  (2)Price  (3)Description\n");
			int choice = in.nextInt();
			in.nextLine();
			switch(choice) {
			case 1:
				System.out.println("Enter new name: ");
				menuItem.get(itemIndex).setName(in.nextLine());
				System.out.println("Update successful!");
				break;
			case 2:
				System.out.print("Enter new price: \n$");
				menuItem.get(itemIndex).setPrice(in.nextDouble());
				System.out.println("Update successful!");
				break;
			case 3:
				System.out.println("Enter new description: ");
				menuItem.get(itemIndex).setDescription(in.nextLine());
				System.out.println("Update successful!");
				break;
			default:
				System.out.println("Invalid choice entered.");
				break;
				}
			}
	}

	/**
	 * Create a promotional set of existing menu items
	 */
	public void createPromotionSet() {
		int itemIndex;
		String description;
		boolean adding =true;
		System.out.println("====== CREATE PROMOTIONAL SET ======");
		System.out.println("Set name: ");
		String name = in.nextLine();
		System.out.print("Price of Set: \n$");
		double price = in.nextDouble();
		ArrayList<MenuItem> SetItem = new ArrayList<MenuItem>();
		showMenuItems(false);
		while(adding) {
			System.out.println("Enter item to add to set (Enter -1 to quit): ");
			if((itemIndex = in.nextInt())== -1) break;
			itemIndex--;
			while(itemIndex < 0 || itemIndex > menuItem.size() - 1 || menuItem.get(itemIndex).getItemType()== TypeOfItem.SET)
				System.out.print("Please choose from within menu.");
			SetItem.add(menuItem.get(itemIndex));
		}
		sort(SetItem);
		in.nextLine();
		System.out.println("Description of Promotional set: ");
		description = in.nextLine();
		menuItem.add(new Set(name, price, description,SetItem));
		System.out.println("Promotional Set succesfully created!");
	}
	
	/**
	 * Update name, price, description and items in the promotional set
	 * @param itemIndex
	 */
	private void updatePromotionSet(int itemIndex) {
		
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Promotional set not in menu.");
			return;
		}
		else {
			System.out.printf("========= SELECT UPDATE =========\n(1)Name  (2)Price  (3)Description  (4)Add Item  (5)Remove Item\n");
			int choice = in.nextInt();
			switch(choice) {
			case 1:
				in.nextLine();
				System.out.println("Enter new name: ");
				menuItem.get(itemIndex).setName(in.nextLine());
				System.out.println("Update successful!");
				break;
			case 2:
				System.out.print("Enter new price: \n$");
				menuItem.get(itemIndex).setPrice(in.nextDouble());
				System.out.println("Update successful!");
				break;
			case 3:
				in.nextLine();
				String description = in.nextLine();
				menuItem.get(itemIndex).setDescription(description);
				System.out.println("Update successful!");
				break;
			case 4:
				sort(menuItem);
				showMenuItems(false);
				System.out.println("Enter item to add from menu:");
				int add_itemIndex = in.nextInt() - 1;
				if(add_itemIndex < 0 || add_itemIndex > menuItem.size() || menuItem.get(add_itemIndex).getItemType() == TypeOfItem.SET) {
					System.out.println("Not from menu (Sets cannot be chosen).");
					System.out.println("Update unsuccessful!");
				}
				else if ( menuItem.get(itemIndex) instanceof Set) {
					Set sets = (Set)menuItem.get(itemIndex);
					sets.addItem(menuItem.get(add_itemIndex));
					System.out.println("Update successful!");
					}
				break;
			case 5:
				if( menuItem.get(itemIndex) instanceof Set) {
					Set sets = (Set)menuItem.get(itemIndex);
					sets.showSet();
					System.out.println("Enter item to remove from set:");
					if(sets.removeItem(in.nextInt() - 1)) 
						System.out.print("Update successful.");
					else 
						System.out.println("Update unsuccessful.");
				}
				break;
			default:
				System.out.println("Invalid choice entered.");
				break;
			}
		}	
}
	/**
	 * Show items in the menu
	 * @param showSets If TRUE, sets in the menu are shown
	 */
	public void showMenuItems(boolean showSets) {
		
		int i,j=0;
		System.out.println("\n\t======= RESTAURANT MENU =======\n");
		System.out.print("DRINKS: \n");
		TypeOfItem item = TypeOfItem.values()[j];
		for(i=0;i<menuItem.size();i++) {
			if(!showSets && menuItem.get(i).getItemType() == TypeOfItem.SET) continue;
			while(menuItem.get(i).getItemType() != item) {
				item = TypeOfItem.values()[++j];
				System.out.printf("\n%sS: \n",item);
			}
			
			System.out.printf("Item %d: %s, $%.2f: ",i+1,menuItem.get(i).getName(),menuItem.get(i).getPrice());
			menuItem.get(i).printDescription();
			System.out.println();
		}
		for (TypeOfItem item1 : TypeOfItem.values()) {
			item= item1;
		}
		while(item != TypeOfItem.values()[j]) {
			if(!showSets && menuItem.get(i).getItemType() == TypeOfItem.SET) {++j;continue;}
			System.out.printf("\n%sS: \n",TypeOfItem.values()[++j]);
		}
	}
	
	/**
	 * Sorts the items in menu according to item type
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

