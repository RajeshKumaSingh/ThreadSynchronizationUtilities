package my.semaphores.multiplecopyresource;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {
	
	private final Semaphore semaphore;
	private boolean freePrinters[];
	private Lock printerLock;

	public PrintQueue() {
		this.semaphore = new Semaphore(3);
		freePrinters = new boolean[3];
		for(int i=0;i<3;i++) {
			freePrinters[i]=true;
		}
		printerLock = new ReentrantLock();
	}

	public void printJob(Object document) {
		try {
			semaphore.acquire();
			int assignedPrinter= getPrinter();
			long duration = (long) (Math.random() * 10);
			System.out.println(
					Thread.currentThread().getName() + ": PrintQueue: Printing job in Printer "+assignedPrinter+" for"
							+ " duration: " + duration + " seconds");
			TimeUnit.SECONDS.sleep(duration);
			freePrinters[assignedPrinter]= true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaphore.release();
		}
	}

	private int getPrinter() {
		
		int ret = -1;
		try {
			printerLock.lock();
			for(int i=0;i<freePrinters.length;i++) {
				if(freePrinters[i]) {
					ret = i;
					freePrinters[i]=false;
					break;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			printerLock.unlock();
		}
		return ret;
	}

}
