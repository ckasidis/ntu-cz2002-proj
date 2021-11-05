package OODPg5;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
public class Menu {
	
	ArrayList<MenuItem> menuItem;
	
	Scanner in = new Scanner(System.in);
	
	public Menu() {
		menuItem = new ArrayList<MenuItem>();
	}
	private void sort() {
		int i, prev=0;
		if(menuItem.size()<=1) return;
		for (TypeOfItem item : TypeOfItem.values()) {
			  for(i=0;i<menuItem.size();i++) {
				  if(menuItem.get(i).getItemType() == item) {
					  Collections.swap(menuItem, i, prev);
					  prev++;
				  }
			  }
			}
	}// sort menuItem according to enum TypeOfItem
	public void editMenu() {
		System.out.println("Enter item number");
		int num = in.nextInt();
		System.out.println("Enter 1 to remove item");
		System.out.println("Enter 2 to update details");
		int choice = in.nextInt();
		switch(choice) {
				case 1:
						removeMenuItem(num);
						break;
				case 2:
					if(menuItem.get(num).getItemType() != TypeOfItem.SET) {
						updateMenuItem(num);
					}
					else {
						updatePromotionSet(num);
					}
					break;
				default: System.out.println("Invalid entry!");
					
		}
	}
	public void createMenuItem() { 
		String name;
		String description;
		double price;
		System.out.printf("====== SELECT ITEM TO CREATE ====== \n(1)Drink  (2)MainCourse  (3)Dessert\n");
		int choice = in.nextInt();
		in.nextLine();
		while(true) {
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
				System.out.println("Succesfully created!");
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
				System.out.println("Succesfully created!");
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
				System.out.println("Succesfully created!");
				break;
			default:
				System.out.println("Please enter appropraite choice.");
				break;
			}
			if(choice>0 && choice<4) {
				sort();
				System.out.println("New Menu:");
				showMenuItems();
				return;
			}
		}
			
	}
	public void removeMenuItem(int itemIndex) {
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Item not in menu.");
			return;
		}
		else {
			menuItem.remove(itemIndex);
			System.out.println("Item is removed.");
			System.out.println("New Menu:");
			showMenuItems();
		}
	}
	public void updateMenuItem (int itemIndex) {
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Promotional set not in menu.");
			return;
		}
		else {
			while(true) {
				System.out.printf("========= SELECT UPDATE =========\n(1)Name  (2)Price  (3)Description\n");
				int choice = in.nextInt();
				in.nextLine();
				switch(choice) {
				case 1:
					System.out.println("Enter new name: ");
					menuItem.get(itemIndex).setName(in.nextLine());
					System.out.println("Successful!");
					return;
				case 2:
					System.out.print("Enter new price: \n$");
					menuItem.get(itemIndex).setPrice(in.nextDouble());
					System.out.println("Successful!");
					return;
				case 3:
					System.out.println("Enter new description: ");
					menuItem.get(itemIndex).setDescription(in.nextLine());
					System.out.println("Successful!");
					return;
				default:
					System.out.println("Invalid choice entered");
				}
			}
		}
	}


	public void createPromotionSet() {
		System.out.println("====== CREATE PROMOTIONAL SET ======");
		System.out.println("Set name: ");
		String name = in.nextLine();
		System.out.print("Price of Set: \n$");
		double price = in.nextDouble();
		in.nextLine();
		System.out.println("Drinks for set: ");
		String drinks = in.nextLine();
		System.out.println("Mains for set: ");
		String mains = in.nextLine();
		System.out.println("Dessert for set: ");
		String dessert = in.nextLine();
		String description = "Promotional set consists of " + drinks + ", " + mains + ", " + dessert;
		menuItem.add(new Set(name, price, description));
		sort();
		System.out.println("New Menu:");
		showMenuItems();
	}
	
	public void updatePromotionSet(int itemIndex) {
		
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Promotional set not in menu.");
			return;
		}
		else {
			while (true) {
				System.out.printf("========= SELECT UPDATE =========\n(1)Name  (2)Price  (3)Description\n");
				int choice = in.nextInt();
				switch(choice) {
				case 1:
					in.nextLine();
					System.out.println("Enter new name: ");
					menuItem.get(itemIndex).setName(in.nextLine());
					System.out.println("Successful!");
					return;
				case 2:
					System.out.print("Enter new price: \n$");
					menuItem.get(itemIndex).setPrice(in.nextDouble());
					System.out.println("Successful!");
					return;
				case 3:
					in.nextLine();
					System.out.println("Drinks for set: ");
					String drinks = in.nextLine();
					System.out.println("Mains for set: ");
					String mains = in.nextLine();
					System.out.println("Dessert for set: ");
					String dessert = in.nextLine();
					String description = "Promotional set consists of " + drinks + ", " + mains + ", " + dessert;
					menuItem.get(itemIndex).setDescription(description);
					System.out.println("Successful!");
					return;
				default:
					System.out.println("Invalid choice entered");
				}
			}
		}	
}
	public void showMenuItems() {
		
		int i;
		System.out.println("\n\t======= RESTAURANT MENU =======\n");
		System.out.print("DRINKS: \n");
		TypeOfItem item = TypeOfItem.values()[j];
		for(i=0;i<menuItem.size();i++) {
			
			while(menuItem.get(i).getItemType() != item) {
				item = TypeOfItem.values()[++j];
				System.out.printf("\n%sS: \n",item);
			}// print enum TypeOfItem as header
			
			System.out.printf("Item %d: %s, $%.2f: ",i+1,menuItem.get(i).getName(),menuItem.get(i).getPrice());
			menuItem.get(i).printDescription();
			System.out.println();
		}//print corresponding menu items
		for (TypeOfItem item1 : TypeOfItem.values()) {
			item= item1;
		}
		while(item != TypeOfItem.values()[j]) {
			System.out.printf("\n%sS: \n",TypeOfItem.values()[++j]);
		}//print rest of enum TypeOfItem as header
		
	}	


