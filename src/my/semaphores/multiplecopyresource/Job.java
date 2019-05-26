package my.semaphores.multiplecopyresource;

public class Job implements Runnable {

	private PrintQueue printQueue;

	public Job(PrintQueue printQueue) {
		this.printQueue = printQueue;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+" Going to print a job");
		printQueue.printJob(new Object());
		System.out.println(Thread.currentThread().getName()+" The document has been printed");
	}

}
