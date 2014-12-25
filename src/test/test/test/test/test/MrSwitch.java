package test.test.test.test.test;

/**
 * @TODO 
 * @author ma_qz
 */
public class MrSwitch {

	public static void main(String[] args) {
		String lock = "1-1";
//		String lock = "lock?";
//		String key = "key!";
		boolean key = false;
		String str;
		System.out.println((str = lock).hashCode());
		switch (lock) {
		case "ac":
			key = true;
		case "1-1":
			System.out.println(1);
			if (key) 
				lock = "1-2";
		case "1-2":
			System.out.println(2);
			if (key) 
				lock = "1-3";
		case "1-3":
			System.out.println(3);
		}
		
		System.out.println("---");
		
		int intLock = 1;
		key = false;
		switch (intLock) {
		case 0:
			key = true;
		case 1:
			System.out.println(1);
		case 2:
			System.out.println(2);
		case 3:
			System.out.println(3);
		}
	}
}
