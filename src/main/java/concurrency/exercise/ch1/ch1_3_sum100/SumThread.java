package concurrency.exercise.ch1.ch1_3_sum100;

import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@ToString
public class SumThread implements Runnable {

	private final int instanceNum;

	private final AtomicInteger atomicNum;
	private final AtomicInteger atomicSum;
	private final int goalNum;

	public SumThread( int instanceNum, AtomicInteger atomicNum, AtomicInteger atomicSum, int goalNum ) {
		this.instanceNum = instanceNum;
		this.atomicNum = atomicNum;
		this.atomicSum = atomicSum;
		this.goalNum = goalNum;
	}

	@Override
	public void run() {

		while ( true ) {

			if ( atomicNum.get() > goalNum ) {

				return;
			}

			int num = atomicNum.getAndIncrement();
			int sum = atomicSum.addAndGet( num );

			System.out.println( instanceNum + " " + Thread.currentThread().getName() + " num : " + num + " sum : " + sum );

		}

	}
}
