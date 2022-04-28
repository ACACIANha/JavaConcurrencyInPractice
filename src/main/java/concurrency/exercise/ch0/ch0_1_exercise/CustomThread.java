package concurrency.exercise.ch0.ch0_1_exercise;

public class CustomThread implements Runnable {


	@Override
	public void run() {

//		while ( true ) {
//			System.out.println( "Thread : " + Thread.currentThread().getName() );
//		}

		System.out.println( "!!!");
		System.out.println( "Thread : " + Thread.currentThread().getName() );
	}
}
