package concurrency.exercise.ch1_1_sum100;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class SumThread extends Thread {

	int sum;
	AtomicInteger calcInteger;
	int maxCount;

	int num = 0;

	public SumThread( AtomicInteger calcInteger, int maxCount ) {
		this.calcInteger = calcInteger;
		this.maxCount = maxCount;
	}

	@Override
	public void run() {
		while ( true ) {
			System.out.println( "" + Thread.currentThread().getName() + " num:" + num + " sum : " + sum +  ": running" );
			if ( maxCount <= calcInteger.get() ) {
//				System.out.println( "" + Thread.currentThread().getName() + sum + ": stop" );
				System.out.println( "" + Thread.currentThread().getName() + " num:" + num + " sum : " + sum +  ": running" );
				return;
			}
			num = calcInteger.getAndIncrement();
			sum += num;
		}
	}
}
