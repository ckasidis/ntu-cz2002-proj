
public interface MenuItem {
	
	public enum TypeOfItem {DRINK , MAINCOURSE, DESSERT , SET};
	public String getName();
	public void setName(String name);
	public double getPrice();
	public void setPrice(double price);
	public TypeOfItem getItemType();
	public void printDescription();
	public void setDescription(String description);
}
