package com.sandeep.task1;

import java.util.concurrent.BlockingQueue;

/**
 * 
 * @author sandeep
 * Class to verify a number from Queue Prime or not and store the result in another queue.
 */
public class Prime implements Runnable{

	private final BlockingQueue<Integer> sharedQueue1; 
	private final BlockingQueue<String> sharedQueue2; 

	/**
	 * 
	 * @param sharedQueue1
	 * @param sharedQueue2
	 * Initializing queues with Prime instantiation
	 */
    public Prime(BlockingQueue<Integer> sharedQueue1,BlockingQueue<String> sharedQueue2) {
        this.sharedQueue1 = sharedQueue1; 
        this.sharedQueue2 = sharedQueue2;
    }
    
    /**
     * Method to verify , whether passed number is a prime or not
     * @param n
     * @return
     */
    public boolean isPrime(int n) {

        if (n%2==0) return false;
        for(int i=3;i*i<=n;i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }

	@Override
	public void run() {
		if(sharedQueue1 != null && sharedQueue1.size() >0){
			try {
				int number =sharedQueue1.take(); 
				if(isPrime(number)){
					sharedQueue2.put(number+","+true);
				}else{
					sharedQueue2.put(number+","+false);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while(true){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();	}
			run();
		}
	}
    
}
