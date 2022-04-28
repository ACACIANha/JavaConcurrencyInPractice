package concurrency.exercise.ch1.ch1_4_sum100_loop;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnableDemo implements Runnable {

	private Thread t;
	private String threadName;
	private final AtomicInteger aNum;
	private final AtomicInteger aSum;
	private final AtomicInteger aGoal;

	public RunnableDemo( String name, AtomicInteger aNum, AtomicInteger aSum, AtomicInteger aGoal ) {
		this.threadName = name;
		this.aNum = aNum;
		this.aSum = aSum;
		this.aGoal = aGoal;
		System.out.println( "creating " + threadName );
	}

	@Override
	public void run() {
		System.out.println( "running " + threadName );
		while ( true ) {
			if ( aNum.get() <= aGoal.get() ) {

				int sum = aSum.addAndGet( aNum.get() );
				System.out.println( "thread: " + threadName + ", num: " + aNum.getAndIncrement() + ", sum: " + sum );
			}
			else {
				break;
			}
		}
		System.out.println( "Thread " + threadName + " exiting. " );
	}

	public void start() {
		System.out.println( "starting " + threadName );

		if ( null == t ) {
			t = new Thread( this, threadName );
			t.start();
		}
	}

	public static void main( String[] args ) {
		AtomicInteger aNum = new AtomicInteger();
		AtomicInteger aSum = new AtomicInteger();
		AtomicInteger aGoal = new AtomicInteger( 100 );

		new RunnableDemo( "Thread-1", aNum, aSum, aGoal ).start();
		new RunnableDemo( "Thread-2", aNum, aSum, aGoal ).start();
		new RunnableDemo( "Thread-3", aNum, aSum, aGoal ).start();
		new RunnableDemo( "Thread-4", aNum, aSum, aGoal ).start();
	}
}
