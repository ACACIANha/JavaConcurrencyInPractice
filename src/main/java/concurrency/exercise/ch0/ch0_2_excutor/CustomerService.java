package concurrency.exercise.ch0.ch0_2_excutor;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerService {

	private ExecutorService executorService;

	public CustomerService() {
		this.executorService = Executors.newFixedThreadPool( 4 );
	}

	public void startWork() {

		int threadIdSeq = 0;

		while ( true ) {

			Random random = new Random();

			Job job = new Job();
			job.setFirst( random.nextInt() );
			job.setSecond( random.nextInt() );

			Worker worker = new Worker( threadIdSeq, job );

			executorService.submit( worker );

			threadIdSeq++;

			try {
				Thread.sleep( 1000 );
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}

	public static void main( String[] args ) {

		CustomerService customerService = new CustomerService();
		customerService.startWork();
	}


}
