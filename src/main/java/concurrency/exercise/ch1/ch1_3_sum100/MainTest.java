package concurrency.exercise.ch1.ch1_3_sum100;

import java.util.concurrent.atomic.AtomicInteger;

public class MainTest {


	public static void main( String[] args ) {

		int threadCount = 10;
		int goalNum = 100;

		AtomicInteger atomicNum = new AtomicInteger();
		AtomicInteger atomicSum = new AtomicInteger();

		for ( int i = 0; i < threadCount; ++i ) {

			SumThread task = new SumThread( i, atomicNum, atomicSum, goalNum );
			Thread thread = new Thread( task );
			thread.start();
			System.out.println( "count : " + i );

		}

//		new SumThread( 0, atomicNum, atomicSum, goalNum ).run();
//		new SumThread( 1, atomicNum, atomicSum, goalNum ).run();
//		new SumThread( 2, atomicNum, atomicSum, goalNum ).run();
//		new SumThread( 3, atomicNum, atomicSum, goalNum ).run();

	}
}
