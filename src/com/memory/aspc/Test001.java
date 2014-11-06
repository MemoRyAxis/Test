package com.memory.aspc;

/**
 * ÂòÆ»¹û
 */
class Product implements Runnable {
	String pName;
	int pCount;
	Store ps;

	public Product(String pName, int pCount, Store ps) {
		this.pName = pName;
		this.pCount = pCount;
		this.ps = ps;
	}

	public void run() {
		for (int i = 0; i < pCount; i++) {
			try {
				ps.buy(this.pName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * ³ÔÆ»¹û
 */
class Consumer implements Runnable {
	String cName;
	int cCount;
	Store cs;

	public Consumer(String cName, int cCount, Store cs) {
		this.cName = cName;
		this.cCount = cCount;
		this.cs = cs;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			try {
				cs.eat(this.cName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

/**
 * ±ùÏä
 */
class Store {
	public static byte[] signal = new byte[0];
	int limit;
	int current;

	public Store(int limit, int current) {
		this.limit = limit;
		this.current = current;
	}

	public void eat(String name) throws Exception {
		synchronized (signal) {
			if (current <= 0) {
				signal.wait();
			} else {
				this.current--;
				System.out.println("eat by " + name);
				System.out.println("\t\tcurr :" + this.current);
			}
			signal.notify();
		}
	}

	public void buy(String name) throws Exception {
		synchronized (signal) {
			if (current < limit) {
				this.current++;
				System.out.println("buy by " + name);
				System.out.println("\t\tcurr : " + this.current);
				signal.notify();
			}
			signal.wait();
		}
	}
}

public class Test001 {
	public static void main(String[] args) {
		Store s = new Store(10, 5);
		Product p1 = new Product("p1", 10, s);
		// Product p2 = new Product("p2", 10, s);
		Consumer c1 = new Consumer("c1", 10, s);
		Consumer c2 = new Consumer("c2", 10, s);

		new Thread(p1).start();
		// new Thread(p2).start();
		new Thread(c1).start();
		// new Thread(c2).start();
	}
}
