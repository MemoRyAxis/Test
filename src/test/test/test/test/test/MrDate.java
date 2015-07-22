package test.test.test.test.test;

import java.util.Calendar;

public class MrDate {

	public static void main(String[] args) {
		System.out.println("1432137600000");
		System.out.println(System.currentTimeMillis());
		Calendar cd = Calendar.getInstance();
		int th = cd.get(Calendar.DAY_OF_WEEK);
		
//		System.out.println("今天是周" + Week.);
		System.out.println(MrWeek.MONDAY.toString());
		System.out.println(MrWeek.MONDAY.ordinal());
		for (MrWeek week : MrWeek.values()) {
			System.out.println(week.getIndex() + week.getName());
		}
		System.out.println(getNowStartMonth());
		
		
		
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		System.out.println(cal.getTime());
	}

	public static String getNowStartMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -0);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if (day == 1) {
			return getBeforeMonth(1);
		}
		return year + (month < 10 ? "0" + month : month).toString() + "01";
	}
	public static String getBeforeMonth(int b_month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -b_month);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return year + (month < 10 ? "0" + month : month).toString() + "01";
	}
}

enum MrWeek{
	SUNDAY		("星期日", Calendar.SUNDAY),
	MONDAY		("星期一", Calendar.MONDAY),
	TUESDAY		("星期二", Calendar.TUESDAY),
	WEDNESDAY	("星期三", Calendar.WEDNESDAY),
	THURSDAY	("星期四", Calendar.THURSDAY),
	FRIDAY		("星期五", Calendar.FRIDAY),
	SATURDAY	("星期六", Calendar.SATURDAY);
	
	private String name;
	private int index;
	
	private MrWeek(String name, int index) {
		this.name = name;
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getChineseName(int index) {
		return this.getName();
	}
}
