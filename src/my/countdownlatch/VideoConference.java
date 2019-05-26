package my.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class VideoConference implements Runnable {
	
	private final CountDownLatch controller;
	
	public VideoConference(int number) {
		controller = new CountDownLatch(number);
	}
	
	public void arrive(String name) {
		System.out.println(name+" has arrived");
		controller.countDown();
		System.out.println("VideoConference: Waiting for "+controller.getCount());
	}

	@Override
	public void run() {
		System.out.println("VideoConference: Intilalization: "+controller.getCount()+" paticipation");
		
		try {
			controller.await();
			System.out.println("VideoConference: All participants have come");
			System.out.println("VideoConference: Lets starts....");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
