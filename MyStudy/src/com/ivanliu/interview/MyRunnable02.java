package com.ivanliu.interview;

public class MyRunnable02 implements Runnable {

	private Thread other = null;
	
	public MyRunnable02(Thread other) {
		
		this.other = other;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		try {
			
			synchronized(other) {
				other.wait();
				for (int i = 0; i < 10; ++i) {
					
					System.out.println("Thread02: " + i);
					Thread.sleep(500);
				}
				other.notify();
			}
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		/*
		try {
			
			other.interrupt();
			for (int j = 0; j < 10; ++j) {
				
				System.out.println("Thread02: " + j);
				Thread.sleep(1000);
			}
		} 
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}
