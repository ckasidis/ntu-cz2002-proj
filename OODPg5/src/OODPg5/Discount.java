package OODPg5;

public class Discount{
	private Double discount;
	public Discount() {
		discount = 0.01;
	}
	public Discount(double discount) {
		this.discount = discount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
}
