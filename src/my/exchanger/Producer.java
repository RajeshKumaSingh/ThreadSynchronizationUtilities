package my.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

public class Producer implements Runnable {
	private List<String> buffer;
	private final Exchanger<List<String>> exchanger;

	public Producer(List<String> buffer, Exchanger<List<String>> exchanger) {
		super();
		this.buffer = buffer;
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		int cycle = 1;

		for (int i = 0; i < 10; i++) {
			System.out.println("Producer: Cycle: " + cycle);
			for (int j = 0; j < 10; j++) {
				String message = "Event " + ((i * 10) + j);
				System.out.println("Producer: Message: " + message);
				buffer.add(message);
			}

			try {
				buffer = exchanger.exchange(buffer);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Producer: buffersize = " + buffer.size());
			cycle++;
		}
	}
}
