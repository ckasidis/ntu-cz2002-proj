package RRPSS;

public class TimeSlot {
	private int startTime;
	private int endTime;
	private boolean occupied;
	
	//constructor
	public TimeSlot() {}
	public TimeSlot(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
		occupied = false;
	}
	
	//setters
	public void setStartTime(int startTime) {this.startTime = startTime;}
	public void setEndTime (int endTime) {this.endTime = endTime;}
	public void setOccupied (boolean occupied) {this.occupied = occupied;}
	
	//getters
	public int getStartTime () {return startTime;}
	public int getEndTime () {return endTime;}
	public boolean getOccupied() {return occupied;}
	
	
}
