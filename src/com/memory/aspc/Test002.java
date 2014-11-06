package com.memory.aspc;

/**
 * synchronized 1
 * 
 * @author ma_qz
 * @date 2014年3月19日下午2:19:42
 * @version v
 */
public class Test002 implements Runnable {

	public void run() {
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()
						+ " : synchornized loop " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		Test002 test002 = new Test002();
		new Thread(test002, "t1").start();
		new Thread(test002, "t2").start();
	}
}
