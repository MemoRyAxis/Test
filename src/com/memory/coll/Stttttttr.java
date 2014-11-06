package com.memory.coll;

public class Stttttttr {

	public static void main(String[] args) {

		String A, B, C;

		// A = "^__^";
		// B = "T.T";
		A = "1111";
		B = "000";
		
		C = A + B;
		System.out.println(C);

		for (int i = 0; i < 10; i++) {
			A = B;
			B = C;
			C = A + B;

//			String tempA = A;
//			String tempB = B;
//			A = tempB;
//			B = tempA + tempB;
//			C = tempB + tempA + tempB;
			
			System.out.println(C);
		}

		System.out.println(Integer.MAX_VALUE);

	}
}
