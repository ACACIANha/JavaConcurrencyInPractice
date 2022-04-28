package concurrency.exercise.ch1.ch1_2_sum100;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SumService {

	private final ExecutorService executorService;
	private final AtomicInteger atomicNum;
	private final AtomicInteger atomicSum;
	private final int goalNum;

	public SumService( int threadNum, int goalNum ) {
		this.executorService = Executors.newFixedThreadPool( threadNum );
		atomicNum = new AtomicInteger();
		atomicSum = new AtomicInteger();
		this.goalNum = goalNum;
	}

	public void work() {

		while ( true ) {

			if ( atomicNum.get() > goalNum ) {
				return;
			}

			SumWorker worker = new SumWorker( atomicNum, atomicSum );
			executorService.submit( worker );

			try {
				Thread.sleep( 1 );
//				wait();
			} catch ( InterruptedException e ) {
				e.printStackTrace();
			}

		}
	}


	public static void main( String[] args ) {

		SumService service = new SumService( 4, 100 );
		service.work();
	}


}
