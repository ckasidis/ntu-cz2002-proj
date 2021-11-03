

public class Dessert implements MenuItem {
	
	private TypeOfItem item;
	private String name;
	private double price;
	private String description;
	
	public Dessert(String name, double price, String description) {
		this.name = name;
		this.price = price;
		this.description = description;
		item = TypeOfItem.DESSERT;	
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
		System.out.println(this.description);
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public TypeOfItem getItemType() {
		return item;
	}

}

