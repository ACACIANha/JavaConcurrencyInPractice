package concurrency.exercise.ch2.exercise;

@Deprecated
public class RunnableRoom implements Runnable {

	private final int number;

	private RunnableRoom( int number ) {
		this.number = number;
	}

	public static RunnableRoom newInstance( int number ) {

		RunnableRoom room = new RunnableRoom( number );

		return room;
	}

	@Override
	public void run() {

		System.out.println( "run Method call : " + number );

		System.out.println( "current thread : " + Thread.currentThread().getName() );

//		Thread.yield();
	}
}
