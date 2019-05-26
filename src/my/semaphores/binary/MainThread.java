package my.semaphores.binary;

public class MainThread {

	public static void main(String[] args) {
		
		// Semaphore -  Controlling access to a resource, permit machine
		
		PrintQueue printQueue = new PrintQueue();
		Thread[] threads = new Thread[10];
		for(int i=0;i<10;i++) {
			threads[i] = new Thread(new Job(printQueue),"Thread-"+i);
		}
		for(int i=0;i<10;i++) {
			threads[i].start();
		}
	}

}
