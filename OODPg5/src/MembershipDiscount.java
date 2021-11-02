package rrps;

public class MembershipDiscount {
	private Double discount;
	public MembershipDiscount() {
		discount = 0.01;
	}
	public MembershipDiscount(double d) {
		discount = d;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double dis) {
		discount = dis;
	}
}
