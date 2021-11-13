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
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Array list of the items in the menu
	 */
	ArrayList<MenuItem> menuItem;
	
	//constructors
	
	/**
	 * Creates a menu
	 */
	public Menu() {
		menuItem = new ArrayList<MenuItem>();
	}
	
	//methods
	
	public MenuItem getMenuItem() {
		System.out.println("Enter item number:");
		while(!sc.hasNextInt()){
			System.out.println("Enter an integer!!!");
			sc.next();
		}
		int item;
		while((item = sc.nextInt()) < 1 || item > menuItem.size()) {
			System.out.println("Item not in menu.");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
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
		if(sc.hasNextInt()) {
			int choice = sc.nextInt();
			sc.nextLine();
			switch(choice) {
			case 1:
				System.out.println("Drink name: ");
				name = sc.nextLine();
				System.out.print("Price of drink: \n$");
				while(!sc.hasNextDouble()){
					System.out.println("Enter a double!!!");
					sc.next();
				}
				price = sc.nextDouble();
				sc.nextLine();
				System.out.println("Description of drink: ");
				description = sc.nextLine();
				menuItem.add(new Drinks(name, price, description));
				break;
			case 2:
				System.out.println("MainCourse name: ");
				name = sc.nextLine();
				System.out.print("Price of Maincourse: \n$");
				while(!sc.hasNextDouble()){
					System.out.println("Enter a double!!!");
					sc.next();
				}
				price = sc.nextDouble();
				sc.nextLine();
				System.out.println("Description of Maincourse: ");
				description = sc.nextLine();
				menuItem.add(new MainCourse(name, price, description));
				break;
			case 3:
				System.out.println("Dessert name: ");
				name = sc.nextLine();
				System.out.print("Price of Dessert: \n$");
				while(!sc.hasNextDouble()){
					System.out.println("Enter a double!!!");
					sc.next();
				}
				price = sc.nextDouble();
				sc.nextLine();
				System.out.println("Description of dessert: ");
				description = sc.nextLine();
				menuItem.add(new Dessert(name, price, description));
				break;
			default:
				System.out.println("Invalid choice entered.");
				break;
			}
			if(choice>0 && choice<4) {
				sort(menuItem);
				System.out.println("Item successfully created!");
			}
		}
		else {
			System.out.println("Enter an integer!!!");
			sc.next();
		}
	}
	
	/**
	 * Create a promotional set of existing menu items
	 */
	public void createPromotionSet() {
		if (menuItem.size() < 1) {
			System.out.println("Menu Empty! Cannot create a set item");
			return;
		}
		int itemIndex;
		String description;
		boolean adding =true;
		System.out.println("====== CREATE PROMOTIONAL SET ======");
		System.out.println("Set name: ");
		String name = sc.nextLine();
		System.out.print("Price of Set: \n$");
		while(!sc.hasNextDouble()){
			System.out.println("Enter a double!!!");
			sc.next();
		}
		double price = sc.nextDouble();
		ArrayList<MenuItem> setItem = new ArrayList<MenuItem>();
		showMenuItems(false);
		while(adding) {
			System.out.println("Enter item to add to set (Enter -1 to quit): ");
			while(!sc.hasNextInt()){
				System.out.println("Enter an integer!!!");
				sc.next();
			}
			if((itemIndex = sc.nextInt())== -1) break;
			itemIndex--;
			if(itemIndex < 0 || itemIndex > menuItem.size() - 1 ) {
				System.out.println("Not added!Please choose from within menu.");
				continue;
			}
			 if(menuItem.get(itemIndex).getItemType()== TypeOfItem.SET){
				 System.out.println("Not added! Sets cannot be added to sets.");
				 continue;
			 }
			setItem.add(menuItem.get(itemIndex));
		}
		sort(setItem);
		sc.nextLine();
		if(setItem.size()<=1) {
			System.out.println("Set not created! Sets need more than 1 item.");
			return;
		}
		System.out.println("Description of Promotional set: ");
		description = sc.nextLine();
		menuItem.add(new Set(name, price, description,setItem));
		System.out.println("Promotional Set succesfully created!");
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
			if(!showSets && menuItem.get(i-1).getItemType() == TypeOfItem.SET) {++j;continue;}
			System.out.printf("\n%sS: \n",TypeOfItem.values()[++j]);
		}
		System.out.println("\n\t================================\n");
	}

	/**
	 * Options to edit the menu:
	 * Remove or update menu item
	 */
	public void editMenu() {
		System.out.println("Enter item number:");
		int num = sc.nextInt();
		if (num < 1 || num > menuItem.size()) {
			System.out.println("Item not found");
			return;
		}
		System.out.println("======= SELECT CHOICE =======\n(1)Remove item  (2)Update details");
		if(sc.hasNextInt()) {
			int choice = sc.nextInt();
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
				return;
			default:
				System.out.println("Invalid entry!");
				return;		
			}
		}
		else {
			System.out.println("Enter an integer!!!");
			sc.next();
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
			System.out.println("Item not in menu.");
			return;
		}
		else {
			System.out.printf("========= SELECT UPDATE =========\n(1)Name  (2)Price  (3)Description\n");
			if(sc.hasNextInt()) {
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						System.out.println("Enter new name: ");
						sc.nextLine();
						menuItem.get(itemIndex).setName(sc.nextLine());
						System.out.println("Update successful!");
						break;
					case 2:
						System.out.print("Enter new price: \n$");
						while(!sc.hasNextDouble()){
							System.out.println("Enter a double!!!");
							sc.next();
						}
						menuItem.get(itemIndex).setPrice(sc.nextDouble());
						System.out.println("Update successful!");
						break;
					case 3:
						System.out.println("Enter new description: ");
						sc.nextLine();
						menuItem.get(itemIndex).setDescription(sc.nextLine());
						System.out.println("Update successful!");
						break;
					default:
						System.out.println("Invalid choice entered.");
						break;
				}
			}
			else {
				System.out.println("Enter an integer!!!");
				sc.next();
			}
		}
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
			if(sc.hasNextInt()) {
				int choice = sc.nextInt();
				switch(choice) {
					case 1:
						sc.nextLine();
						System.out.println("Enter new name: ");
						menuItem.get(itemIndex).setName(sc.nextLine());
						System.out.println("Update successful!");
						break;
					case 2:
						System.out.print("Enter new price: \n$");
						while(!sc.hasNextDouble()){
							System.out.println("Enter a double!!!");
							sc.next();
						}
						menuItem.get(itemIndex).setPrice(sc.nextDouble());
						System.out.println("Update successful!");
						break;
					case 3:
						sc.nextLine();
						String description = sc.nextLine();
						menuItem.get(itemIndex).setDescription(description);
						System.out.println("Update successful!");
						break;
					case 4:
						sort(menuItem);
						showMenuItems(false);
						System.out.println("Enter item to add from menu:");
						while(!sc.hasNextInt()){
							System.out.println("Enter an integer!!!");
							sc.next();
						}
						int add_itemIndex = sc.nextInt() - 1;
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
							while(!sc.hasNextInt()){
								System.out.println("Enter an integer!!!");
								sc.next();
							}
							if(sets.removeItem(sc.nextInt() - 1)) 
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
			else {
				System.out.println("Enter an integer!!!");
				sc.next();
			}
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

