package com.memory.aspc;

public class Test006 {

	public static void main(String[] args) {
		TxtThread tt = new TxtThread();
		new Thread(tt, "1 : ").start();
		new Thread(tt, "2 : ").start();
		new Thread(tt, "3 : ").start();
		new Thread(tt, "4 : ").start();
	}
}

class TxtThread implements Runnable {
	int num = 100;
	String str = new String();

	public void run() {
		synchronized (str) {
			while (num > 0) {

				try {
					Thread.sleep(1);
					if (num == 50) {
						num--;
						str.wait();
					}
					if(num == 48) {
						str.notify();
					}
				} catch (Exception e) {
					e.getMessage();
				}
				System.out.println(Thread.currentThread().getName()
						+ "this is " + num--);
			}
		}
	}
}