package com.memory.coll;

public class T9 {

	public static void main(String[] args) {

		int param1 = 2;
		int param2 = 3;
		int param3 = 2;

		int[][] aa = { { 1, 2, 3 }, { 2, 1, 5 } };

		int count = jc(param1 + param2 - 2) / (jc(param1 - 1) * jc(param1 - 2));

		int[][] ways = new int[count][];

		for (int m = 0; m < count; m++) {
			for (int n = 0; n < param1 + param2 - 2; n++) {
				int temp1 = 0;
				int temp2 = 0;

				ways[m][n] = aa[temp1][temp2];

				if (temp1 < param1 && temp2 < param2) {
					temp1++;
				} else if (temp2 < param2) {
					temp2++;
				}
			}
		}
	}

	private static int jc(int n) {
		if (n < 0) {
			return -1;
		}
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else {
			return n * jc(n - 1);
		}
	}
}
