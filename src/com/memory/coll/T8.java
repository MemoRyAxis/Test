package com.memory.coll;

import java.util.Scanner;

public class T8 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int a = 3;
		int[] t = new int[a];
		for (int i = 0; i < t.length; i++) {
			int temp = (int) (Math.random() * 499 + 1) * 2;
			t[i] = temp;
		}

	}
}
