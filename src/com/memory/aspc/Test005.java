package com.memory.aspc;

public class Test005 {

	int hight;
	public static Object O = new Object();

	public Test005(int hight) {
		this.hight = hight;
	}

	public void rise() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				this.hight++;
				System.out.println("+" + this.hight);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("end");
		}
	}

	public synchronized void p() {
		System.out.println("\t\t\t123");
	}
	
	public void decline() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				this.hight--;
				System.out.println("-" + this.hight);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		final Test005 t5 = new Test005(100);
		new Thread(new Runnable() {
			public void run() {
				t5.rise();
				System.out.println("p");
				t5.p();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				t5.p();
			}
		}).start();
		
		new Thread(new Runnable() {
			public void run() {
				t5.decline();
			}
		}).start();
	}
}
