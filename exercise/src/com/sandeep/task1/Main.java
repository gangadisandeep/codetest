package com.sandeep.task1;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	/**
	 * Main method to start the application
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		BlockingQueue<Integer> sharedQueue1 = new LinkedBlockingQueue<Integer>(); 
	     BlockingQueue<String> sharedQueue2 = new LinkedBlockingQueue<String>(); 
	     Prime prime = new Prime(sharedQueue1,sharedQueue2); 
	     Randomizer randomizer = new Randomizer(sharedQueue1,sharedQueue2);

	        new Thread(randomizer).start();
	        new Thread(prime).start();

	        Thread.sleep(5000); 
	 


	}

}
