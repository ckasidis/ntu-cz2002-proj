package OODPg5;

public class Set implements MenuItem {
	private String name;
	private double price;
	private String description;
	private TypeOfItem item;
	
	public Set (String name, double price, String description) {
			this.name = name;
			this.price = price;
			this.description = description;
			item = TypeOfItem.SET;
	}
	
	public void printDescription() {
		System.out.printf(description);
	}
	
	//getters
	public String getName() {
		return name;
	}
	public TypeOfItem getItemType() {
		return item;
	}
	public double getPrice() {
		return price;
	}
	
	//setters
	public void setPrice(double price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

