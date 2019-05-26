package my.cyclicbarier;

import java.util.concurrent.CyclicBarrier;

public class MainThread {
	
	// Synchronizing tasks in a common point

	public static void main(String[] args) {
		final int ROWS = 1000;
		final int NUMBERS = 1000;
		final int SEARCH = 5;
		final int PARTICIPANTS = 5;
		final int LINE_PARTICIPANT = 2000;
		
		MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);
		Results results = new Results(ROWS);
		Grouper grouper = new Grouper(results);
		CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS,grouper);
		Searcher searchres[] = new Searcher[PARTICIPANTS];
		
		for(int i=0;i<PARTICIPANTS;i++) {
			searchres[i]=new Searcher(i*LINE_PARTICIPANT, 
					(i*LINE_PARTICIPANT)+LINE_PARTICIPANT, mock, results, 5,barrier);
			new Thread(searchres[i]).start();
		}
		System.out.println("Main method has finished.");
		
	}

}
