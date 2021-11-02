package rrps;

public class MembershipDiscount {
	private Double discount;
	public MembershipDiscount() {
		discount = 0.01;
	}
	public MembershipDiscount(double discount) {
		discount = this.discount;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		discount = this.discount;
	}
}
