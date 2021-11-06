package OODPg5;
import java.util.ArrayList;

public class Set implements MenuItem {
	private String name;
	private double price;
	private String description;
	private TypeOfItem item;
	
	private ArrayList<MenuItem> menuItem;
	
	public Set (String name, double price, String description,ArrayList<MenuItem> mi) {
			this.name = name;
			this.price = price;
			this.description = description;
			menuItem = mi;
			item = TypeOfItem.SET;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}
	public void addItem(MenuItem mi) {
		menuItem.add(mi);
	}
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
	public void setPrice(double price) {
		this.price = price;
	}

	public void printDescription() {
		System.out.println(description);
		int j=0;
		System.out.println("Promotional set consists of: ");
		TypeOfItem item = TypeOfItem.values()[j];
		for(int i=0;i<menuItem.size();i++) {
			System.out.printf("%s, : ",menuItem.get(i).getName());
			menuItem.get(i).printDescription();
		}
	}

	public TypeOfItem getItemType() {
		return item;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
