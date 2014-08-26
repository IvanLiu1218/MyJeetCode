package com.ivanliu.interview;

public class MyRunnable01 implements Runnable {

	@Override
	public void run() {

		
		while (true) {
			
			synchronized(this) {
				
				try {
					
					this.wait();
				}
				catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 0; i < 10; ++i) {
				
				try {
					
					System.out.println("Thread01: " + i);
					Thread.sleep(500);
				} 
				catch (InterruptedException e) {
					
					System.out.println("InterruptedException received");
				}
			}
			
		}
	}


}
