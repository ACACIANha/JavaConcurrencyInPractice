package concurrency.exercise.ch0_1_exercise;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorThreadTest {


	public static void main( String[] args ) throws ExecutionException, InterruptedException {

		System.out.println( "Hello World! start" );

//		ExecutorService service = Executors.newFixedThreadPool( 5 );
		ExecutorService service = Executors.newSingleThreadExecutor();

		Future< CustomThread > future = service.submit( CustomThread::new );
//		Future< CustomThread > future = service.execute( );

		CustomThread customThread = future.get();

		if ( future.isDone() ) {
			System.out.println( "done" );
		} else {
			System.out.println( "!isDone" );
		}


		System.out.println( "end main method " );

	}
}
