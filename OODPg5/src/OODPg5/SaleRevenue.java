package OODPg5;
import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
/**
 * Represents the sales revenue of the restaurant for up to a year
 * @author Group 5
 *
 */
public class SaleRevenue {
	/**
	 * Nested array list of orders placed in a year, 
	 * Contains array list of orders placed in each month of the year
	 */
	private ArrayList<ArrayList<Order>> year = new ArrayList<ArrayList<Order>>();
	
	//constructors
	
	/**
	 * Creates a nested array list to record sales revenue of restaurant for up to a year
	 * Initialises 12 array list of order for the 12 months in a year
	 */
	public SaleRevenue() {
		System.out.println("Record up to 1 year of Sales");
		for(int i=0;i<12;i++) {
			year.add(new ArrayList<Order>());
		}
	}

	//methods
	
	/**
	 * Record an order in corresponding month in the year
	 * @param order Order to be recorded
	 */
	public void addOrder(Order order) {
		int mnth = order.getDate().getMonthValue()-1;
		year.get(mnth).add(order);
	}
	
	/**
	 * Print all the sales revenue for the stated date
	 * @param date Date of the recorded sales revenue needed
	 */
	public void printSalesRevenueDay(LocalDate date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
		int mnthIndex = date.getMonthValue()-1;
		double totalRevenue =0;
		for(Order o: year.get(mnthIndex)) {
			if(o.getDate().compareTo(date)==0) {
				System.out.println("");
				System.out.println("Date:" + o.getDate().format(dtf));
				o.viewOrder();
				totalRevenue += o.getfinalPrice();
			}
		}
		System.out.println("");
		System.out.printf("Total Revenue for the day: %.2f\n",totalRevenue);
	}
	
	/**
	 * Print all the sales revenue for the stated month
	 * @param mnth Month of the recorded sales revenue needed
	 */
	public void printSalesRevenueMonth(int mnth) {
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("dd/MM");
		
		int mnthIndex = mnth - 1;
		double totalRevenue =0;
		for(Order o: year.get(mnthIndex)) {
			System.out.println("");
			System.out.println("Date:" + o.getDate().format(dtf1));
			o.viewOrder();
			totalRevenue += o.getfinalPrice();
		}
		System.out.println("");
		System.out.printf("Total Revenue for the Month: %.2f\n",totalRevenue);
	}
	
	/**
	 * Print all the recorded sales revenue up to a year
	 */
	public void printSalesRevenueYear() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("MMM");
		LocalDate tempDate = LocalDate.now();
		LocalDate tempDate1= tempDate.minusYears(1);
		double totalRevenue = 0;
		System.out.println("Printing Sales Record from :"+ tempDate1.format(dtf)+ "to " + tempDate.format(dtf));
		for(ArrayList<Order> mnth : year) {
			if(mnth.size()!=0) {
				System.out.println("");
				System.out.println("Month: " +  mnth.get(0).getDate().format(dtf1));
				for(Order o: mnth) {
					tempDate = o.getDate().withDayOfMonth(1);
					if(o.getDate().compareTo(tempDate)!=0) {
						System.out.println("");
						System.out.println("Date:" + o.getDate().format(dtf));
						tempDate = o.getDate();
					}
					o.viewOrder();
					totalRevenue += o.getfinalPrice();
				}
			}
		}
		System.out.println("");
		System.out.printf("Total Revenue for the Year: %.2f\n",totalRevenue);
	}
}
