package OODPg5;

import java.util.ArrayList;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaleRevenue {

	ArrayList<MenuItem> menuItem;

	Calendar c = Calendar.getInstance();

	private Double totalRevenue;
	private Order orders[];

	private String checkToday;
	private String checkMonth;

	public SaleRevenue(double totalRevenue, Order orders[]) {
		this.totalRevenue = totalRevenue;
		this.orders[] = orders[];
	}

	public Double calculateSaleRevenue(boolean isToday) { //int check to see if Today or Month, 1 for Today, 0 for Month

		//For checking Today's sale, match all 3 (date,month,year)
		Date dateToday = c.getTime();
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy"); //Eg 13 April 2015
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM"); //Eg 13 April
		DateFormat dateFormatToday = new SimpleDateFormat("dd-M-yyyy); //Eg 13-4-2015
		this.checkToday = dateFormatToday.format(dateToday);

		//For checking Month's sale, match all 2 (month,year)
		Date dateMonth = c.getTime();
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy"); //Eg 13 April 2015
		//DateFormat dateFormat = new SimpleDateFormat("dd MMMM"); //Eg 13 April
		DateFormat dateFormatMonth = new SimpleDateFormat("M-yyyy); //Eg 4-2015
		this.checkMonth = dateFormatMonth.format(dateMonth);


		if(isToday == true){ //boolean check for today or month

			//Revenue for the Today
			Double sum = 0;

			for(int i=0;i<orders.length;i++) {
				if(orders.strDate == checkToday) { // if date and month match, print and sum the orders
					sum = sum + orders[i].orderedItem.getPrice();
				}
			}

			return sum;
		}else {

			String formatMonth = orders.strDate.substring(3,9); //Eg Cut out "13-" from "13-4-2015"

			//Revenue for the Month
			Double sum = 0;

			for(int i=0;i<orders.length;i++) {
				if(formatMonth == checkMonth) { // if date and month match, print and sum the orders
					sum = sum + orders[i].orderedItem.getPrice();
				}
			}

			return sum;
		}

	}

}
