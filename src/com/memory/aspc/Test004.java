package com.memory.aspc;

/**
 * synchronized 34
 * 
 * @author ma_qz
 * @date 2014年3月19日下午3:43:32
 * @version v
 */
public class Test004 {

	public synchronized void tm1() {
		// public void tm1() {
		for (int i = 0; i < 5; i++) {
			System.out.println("tm1 " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void tm2() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println("tm2 " + i);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		final Test004 t4 = new Test004();
		// final Test004 t42 = new Test004();

		new Thread(new Runnable() {
			public void run() {
				t4.tm1();
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				t4.tm2();
			}
		}).start();
	}
}
