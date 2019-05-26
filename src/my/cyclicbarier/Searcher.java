package my.cyclicbarier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Searcher implements Runnable {
	
	private int firstRow;
	private int lastRow;
	private MatrixMock matrixMock;
	private Results results;
	private int number;
	
	private final CyclicBarrier barrier;
	
	
	
	public Searcher(int firstRow, int lastRow, MatrixMock matrixMock, Results results, int number,
			CyclicBarrier barrier) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.matrixMock = matrixMock;
		this.results = results;
		this.number = number;
		this.barrier = barrier;
	}



	@Override
	public void run() {
		int counter;
		System.out.println(Thread.currentThread().getName()+": Procesing lines from "+firstRow+" to "+lastRow);
		for(int i=firstRow;i<lastRow;i++) {
			int row[] = matrixMock.getRow(i);
			counter = 0;
			if(matrixMock.getRow(i)!=null) {
				for(int j=0;j<row.length;j++) {
					if(row[j]==number) {
						counter++;
					}
				}
				results.setData(i, counter);
			}
			
		}
		System.out.println(Thread.currentThread().getName()+": Lines processed.");
		
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}
