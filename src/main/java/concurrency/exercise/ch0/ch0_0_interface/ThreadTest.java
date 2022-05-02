package concurrency.exercise.ch0.ch0_0_interface;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadTest {


	public static void main( String[] args ) {

		log.info( "log test !!!!!" );

		System.out.println( "Hello World! start" );

		Runnable threadLambda = () -> {
			while ( true ) {
				try {
					Thread.sleep( 1000 );
				} catch ( InterruptedException e ) {
					e.printStackTrace();
				}
//				System.out.println( "run thread " + Thread.currentThread() );
			}
		};

		new Thread( threadLambda ).start();
		new CustomThread().start();

	}
}
