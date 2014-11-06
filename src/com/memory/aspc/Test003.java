package com.memory.aspc;

/**
 * synchronized 2
 * 
 * @author ma_qz
 * @date 2014年3月19日下午2:44:43
 * @version v
 */
public class Test003 {

	public void tm1() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println("this synchronized block");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void tm2() {
		for (int i = 0; i < 5; i++) {
			System.out.println("normal block");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		final Test003 t3 = new Test003();
		new Thread(new Runnable() {
			public void run() {
				t3.tm1();
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				t3.tm2();
			}
		}).start();
	}
}
