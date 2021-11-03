
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void printDescription() {
		System.out.printf(description);
	}
	public TypeOfItem getItemType() {
		return item;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}

