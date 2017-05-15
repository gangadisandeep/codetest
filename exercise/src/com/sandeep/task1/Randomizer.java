package com.sandeep.task1;

import java.util.Random;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author Sandeep
 * Class to generate Random number and store in a Queue. 
 * Print a message in console by taking from a queue
 */
public class Randomizer implements Runnable{
  
	private final BlockingQueue<Integer> sharedQueue1;

	private final BlockingQueue<String> sharedQueue2;

	/**
	 * 
	 * @param sharedQueue1
	 * @param sharedQueue2
	 * Initializing queues with Prime instantiation
	 */
    public Randomizer(BlockingQueue<Integer> sharedQueue1,BlockingQueue<String> sharedQueue2) {
        this.sharedQueue1 = sharedQueue1;
        this.sharedQueue2 = sharedQueue2;
    }

    /**
     * Method to generate Random number using Java API
     * @return
     */
	public  int generateRandomNumber() {
		Random rand = new Random();
		return ( 1 + rand.nextInt((1000 - 1) + 1));
	}

	@Override
	public void run() {
		
		try {
			int number = generateRandomNumber();
			sharedQueue1.put(number);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(null != sharedQueue2 && sharedQueue2.size() > 0){
			try {
				String result = sharedQueue2.take();
				String[] arr = result.split(",");
				if(null != arr && arr.length >0){
					System.out.println("Number : "+arr[0]+" Is A Prime Number: "+arr[1]);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}

		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			run();
		}
	}
}
