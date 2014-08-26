package com.ivanliu.interview;

public class MyRunnable03 implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int i = 0;
		while (true) {
			
			System.out.println("Thread03: " + i++);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
