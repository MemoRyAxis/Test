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

	}
}
