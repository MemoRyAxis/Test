package test.test.test.test.test;

import java.io.IOException;
import java.math.BigDecimal;

public class MrNumber {

	public static void main(String[] args) throws IOException {
		System.out.println(BigDecimal.ROUND_HALF_UP);
		System.out.println(Math.rint(123.1233));
		System.out.println("四舍五入取整:(2)=" + new BigDecimal("2").setScale(0, BigDecimal.ROUND_HALF_UP)); 
		System.out.println("四舍五入取整:(2.1)=" + new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP)); 
		System.out.println("四舍五入取整:(2.5)=" + new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP)); 
		System.out.println("四舍五入取整:(2.9)=" + new BigDecimal("2.9").setScale(0, BigDecimal.ROUND_HALF_UP));
		
		char[] nums = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < 300; j ++) {
				System.out.print(nums[(int) (Math.random() * nums.length)]);
			}
			System.out.println();
		}
		
		double d1 = 123.0000;
		System.out.println(d1 == (int) d1);
	}
}
