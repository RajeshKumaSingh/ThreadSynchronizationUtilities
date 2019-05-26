package my.countdownlatch;

public class MainThread {
	
	public static void main(String[] args) {
		
		// Waiting for multiple concurrent events
		
		VideoConference videoConference = new VideoConference(10);
		new Thread(videoConference).start();
		
		for(int i=0;i<10;i++) {
			Participant p = new Participant("Participant_"+i, videoConference);
			new Thread(p).start();
		}
		
	}
}
