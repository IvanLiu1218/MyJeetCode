package com.ivanliu.interview;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyConcurrency {

	public static void test001() {
		
		Thread t = new Thread(new Runnable() {

			private BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>();
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				int x = 0;
				
				while (true) {
					
					/*
					try {
						
						Integer i = queue.take();
						System.out.println(i);
					} 
					catch (InterruptedException e) {
						
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
					System.out.println(x++);
					*/
					
					System.out.println(x++);
					try {
						
						Thread.sleep(1000);
					} 
					catch (InterruptedException e) {
						
						Thread.currentThread().interrupt();
						e.printStackTrace();
					}
				}
			}
		});
		t.start();
		
		try {
			
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t.interrupt();
		
		System.out.println("end");
	}
}
