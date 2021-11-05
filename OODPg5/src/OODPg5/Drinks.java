package OODPg5;

public class Drinks implements MenuItem{
	private TypeOfItem item;
	private String name;
	private double price;
	private String description;
	
	public Drinks (String name, double price, String description ) {
			this.name = name;
			this.price = price;
			this.description = description;
			item = TypeOfItem.DRINK;
	}
	
	public void printDescription() {
		System.out.println(this.description);
	}
	
	//getters
	public String getName() {
		return name;
	}
	public double getPrice() {
		return price;
	}
	
	public TypeOfItem getItemType() {
		return item;
	}
	
	//setters
	public void setName(String name) {
		this.name = name;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
