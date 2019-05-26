package my.semaphores.binary;

import java.util.concurrent.Semaphore;

public class PrintQueue {
	
	private final Semaphore semaphore;

	public PrintQueue() {
		this.semaphore = new Semaphore(1);  // binary, counter having 2 values: 0 and 1
	}

	public void printJob(Object document) {
		try {
			semaphore.acquire();
			long duration = (long) (Math.random() * 10);
			System.out.println(
					Thread.currentThread().getName() + ": PrintQueue: Printing job duration: " + duration + " seconds");
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

}
