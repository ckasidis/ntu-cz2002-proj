package OODPg5;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class SaleRevenue {

	private ArrayList<ArrayList<Order>> year = new ArrayList<ArrayList<Order>>();
	

	private Double totalRevenue;

	private String checkToday;
	private String checkMonth;


	public SaleRevenue() {
		System.out.println("Record up to 1 year of Sales");
		for(int i=0;i<12;i++) {
			year.add(new ArrayList<Order>());
		}
	}
	public void addOrder(Order order) {
		int mnth = order.getDate().getMonthValue()-1;
		year.get(mnth).add(order);
	}
	public void printSalesRevenueDay(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Date:" + date.format(dtf));
		
		int mnth = date.getMonthValue()-1;
		double totalRevenue =0;
		for(Order o: year.get(mnth)) {
			if(o.getDate().compareTo(date)==0)
				o.viewOrder();
				totalRevenue += o.getfinalPrice();
		}
		System.out.println("Total Revenue for the day:"+totalRevenue);
	}
	public void printSalesRevenueMonth(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/yyyy");
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM");
		System.out.println("Date:" + date.format(dtf));
		
		LocalDate tempDate = date.withDayOfMonth(1);
		int mnth = date.getMonthValue()-1;
		double totalRevenue =0;
		for(Order o: year.get(mnth-1)) {
			if(o.getDate().compareTo(tempDate)!=0) {
				System.out.println("Date:" + date.format(dtf1));
				tempDate = o.getDate();
			}
			o.viewOrder();
			totalRevenue += o.getfinalPrice();
		}
		System.out.println("Total Revenue for the Month:"+totalRevenue);
	}
	public void printSalesRevenueYear() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MM/yyyy");
		LocalDate tempDate = LocalDate.now();
		 LocalDate tempDate1= tempDate.minusYears(1);
		 double totalRevenue = 0;
		 System.out.println("Printing Sales Record from :"+ tempDate1.format(dtf)+ "to " + tempDate.format(dtf));
		 int i=0;
		for(ArrayList<Order> mnth : year) {
			if(mnth.size()!=0) {
				System.out.println("Month: " +  mnth.get(0).getDate().format(dtf1));
				for(Order o: mnth) {
					tempDate = o.getDate().withDayOfMonth(1);
					if(o.getDate().compareTo(tempDate)!=0) {
						System.out.println("Date:" + o.getDate().format(dtf1));
						tempDate = o.getDate();
					}
					o.viewOrder();
					totalRevenue += o.getfinalPrice();
				}
			}
		}
	}
}
