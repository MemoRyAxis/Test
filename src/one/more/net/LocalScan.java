package one.more.net;

import java.net.ServerSocket;

public class LocalScan {

	public static void main(String[] args) {

		for (int i = 0; i < 1023; i++) {
			testPort(i);
		}
		System.out.println("\t\t\tend");
	}

	private static void testPort(int i) {
		try {
			ServerSocket sock = new ServerSocket(i);
		} catch (Exception e) {
			System.out.println("Port " + i + " in use");
		}
	}
}
