package one.more.net;

import java.net.InetAddress;

public class GetIP {

	public static void main(String[] args) {

		InetAddress address = null;
		if (args.length == 0) {
			System.out.println("usage: GetIP host");
			System.exit(1);
		}
		try {
			address = InetAddress.getByName(args[0]);
		} catch (Exception e) {
			System.out.println("i can t find  " + args[0]);
			System.exit(2);
		}
		System.out.println(address.getHostName() + " "
				+ address.getHostAddress());
		System.exit(0);
	}
}
