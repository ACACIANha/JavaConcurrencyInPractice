package concurrency.exercise.ch0.ch0_3_exercise;

public class RunnableDemo implements Runnable {

	private Thread t;
	private String threadName;

	public RunnableDemo( String name ) {
		this.threadName = name;
		System.out.println( "creating " + threadName );
	}

	@Override
	public void run() {
		System.out.println( "running " + threadName );
		try {
			for ( int i = 4; i > 0; --i ) {
				System.out.println( "thread: " + threadName + ", " + i );
				Thread.sleep( 50 );
			}
		} catch ( InterruptedException e ) {
			System.out.println( "Thread " + threadName + " interrupted. " );
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
		RunnableDemo r1 = new RunnableDemo( "Thread-1" );
		r1.start();

		RunnableDemo r2 = new RunnableDemo( "Thread-2" );
		r2.start();

	}
}
