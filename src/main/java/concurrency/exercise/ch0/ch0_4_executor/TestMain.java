package concurrency.exercise.ch0.ch0_4_executor;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
@Getter
@Setter
@ToString
public class TestMain implements Runnable {

	private final int name;

	public TestMain( int name ) {
		this.name = name;
	}


	public static void main( String[] args ) throws ExecutionException, InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool( 2 );
//		ExecutorService service = Executors.newSingleThreadExecutor();

		TestMain testMain = new TestMain( 0 );

		service.submit( testMain );
		Future< ? > submit = service.submit( testMain );

		submit.get();
	}

	@Override
	public void run() {
		while ( true ) {
			log.info( "currentThread : {} ", Thread.currentThread().getName() );
		}
	}
}
