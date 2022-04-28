package concurrency.exercise.ch0.ch0_0_interface;

import lombok.SneakyThrows;

public class CustomThread extends Thread {

	@SneakyThrows
	@Override
	public void run() {

		while ( true ) {
			Thread.sleep( 600 );
			System.out.println( "run thread " + Thread.currentThread() );
		}
	}
}
