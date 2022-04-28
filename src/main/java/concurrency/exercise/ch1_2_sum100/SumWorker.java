package concurrency.exercise.ch1_2_sum100;

import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class SumWorker implements Runnable {

	private final AtomicInteger atomicNum;
	private final AtomicInteger atomicSum;

//	int num;
//	int sum;

	public SumWorker( AtomicInteger atomicInteger, AtomicInteger atomicSum ) {
		this.atomicNum = atomicInteger;
		this.atomicSum = atomicSum;
	}

	@Override
	public void run() {

		atomicSum.addAndGet( atomicNum.getAndIncrement() );

//		num = atomicNum.getAndIncrement();
//		sum += num;
		System.out.println( " " + Thread.currentThread().getName() + " " + this.toString() );
	}
}
