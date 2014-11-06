package com.memory.aspc;

class P implements Runnable {
	S s;

	public P(S s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			try {
				s.add();
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

class C implements Runnable {
	S s;

	public C(S s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			try {
				s.dec();
				Thread.sleep(100);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
}

class S {
	private static byte[] lock = new byte[0];
	private int capacity;
	private int curr;

	public S(int capacity) {
		this.capacity = capacity;
	}

	public void add() throws Exception {
		synchronized (lock) {
			if (curr == capacity) {
				lock.wait();
			} else {
				this.curr++;
			}
			lock.notify();
			System.out.println("add to " + this.curr);
		}
	}

	public void dec() throws Exception {
		synchronized (lock) {
			if (curr <= 0) {
				lock.wait();
			}
			this.curr--;
			lock.notify();
			System.out.println("dec to " + this.curr);
		}
	}
}

public class Test007 {

	public static void main(String[] args) {
		final S s = new S(10);

		System.out.println(System.currentTimeMillis());
		new Thread(new P(s)).start();
		new Thread(new P(s)).start();
		new Thread(new C(s)).start();
	}
}
