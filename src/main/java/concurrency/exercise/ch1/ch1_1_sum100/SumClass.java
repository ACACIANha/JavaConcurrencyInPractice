package concurrency.exercise.ch1.ch1_1_sum100;

import lombok.Getter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@ToString
public class SumClass {

	private final int threadNum;

	private final int maxCount;

	private List< Thread > threads = new LinkedList<>();

	private AtomicInteger num = new AtomicInteger();


	public SumClass( int threadNum, int maxCount ) {

		this.threadNum = threadNum;
		this.maxCount = maxCount;

		init();
	}

	public void init() {

		for ( int i = 0; i < threadNum; ++i ) {
			SumThread sumThread = new SumThread( num, maxCount );
//			sumThread.start();
			threads.add( sumThread );
		}
		threads.stream().map( SumThread.class::cast ).forEach( SumThread::start );
	}

	public void print() {
		threads.forEach( System.out::println );
	}
}
