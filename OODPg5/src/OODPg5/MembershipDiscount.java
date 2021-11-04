package OODPg5;

public class MembershipDiscount {
	private Double discount;
	public MembershipDiscount() {
		discount = 0.01;
	}
	public MembershipDiscount(double discount) {
		this.discount = discount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
