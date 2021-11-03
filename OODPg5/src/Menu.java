import java.util.ArrayList;
import java.util.Scanner;
public class Menu {
	
	ArrayList<MenuItem> menuItem;
	
	Scanner in = new Scanner(System.in);
	
	public Menu() {
		menuItem = new ArrayList<MenuItem>();
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
				return;
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
				return;
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
				return;
			default:
				System.out.println("Please enter appropraite choice.");
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
	}
	
	public void removePromotionSet(int itemIndex) {
		
		--itemIndex;
		if(itemIndex < 0 || itemIndex > menuItem.size() - 1) {
			System.out.println("Promotional set not in menu.");
			return;
		}
		else {
			menuItem.remove(itemIndex);
			System.out.println("Item is removed.");
		}
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
		for(i = 0; i < menuItem.size(); i++) {
			if(menuItem.get(i).getItemType() == MenuItem.TypeOfItem.DRINK) {
				System.out.print("Item " + (i+1) + ": " + menuItem.get(i).getName() + ", $" + menuItem.get(i).getPrice() + ": ");
				menuItem.get(i).printDescription();
				System.out.println();
			}
		}
		System.out.print("\nMAINCOURSES: \n");
		for(i = 0; i < menuItem.size(); i++) {
			if(menuItem.get(i).getItemType() == MenuItem.TypeOfItem.MAINCOURSE) {
				System.out.print("Item " + (i+1) + ": " + menuItem.get(i).getName() + ", $" + menuItem.get(i).getPrice() + ": ");
				menuItem.get(i).printDescription();
				System.out.println();
			}
		}
		System.out.print("\nDESSERTS: \n");
		for(i = 0; i < menuItem.size(); i++) {
			if(menuItem.get(i).getItemType() == MenuItem.TypeOfItem.DESSERT) {
				System.out.print("Item " + (i+1) + ": " + menuItem.get(i).getName() + ", $" + menuItem.get(i).getPrice() + ": ");
				menuItem.get(i).printDescription();
				System.out.println();
			}
		}
		System.out.printf("\nPROMOTIONAL SETS: \n");
		for(i = 0; i < menuItem.size(); i++) {
			if(menuItem.get(i).getItemType() == MenuItem.TypeOfItem.SET) {
				System.out.print("Item " + (i+1) + ": " + menuItem.get(i).getName() + ", $" + menuItem.get(i).getPrice() + ": ");
				menuItem.get(i).printDescription();
				System.out.println();
			}
		}
	}	
}

