package concurrency.exercise.ch4.blockingqueue;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class MainTest {

	public static void main( String[] args ) throws InterruptedException, ExecutionException {

		BlockingQueue< Object > queue = new ArrayBlockingQueue<>( 3 );

		ExecutorService service = Executors.newFixedThreadPool( 2 );

		Future< Object > submit1 = service.submit( () -> {
			Object poll = queue.take();
			log.info( "1 : {}", poll );
			return poll;
		} );

		Future< Object > submit2 = service.submit( () -> {
			Object poll = queue.take();
			log.info( "2 : {}", poll );
			return poll;
		} );

		Future< Object > submit3 = service.submit( () -> {
			Object poll = queue.take();
			log.info( "3 : {}", poll );
			return poll;
		} );

		TimeUnit.MILLISECONDS.sleep( 100 );
		queue.add( "test" );
		queue.add( "test" );
		TimeUnit.MILLISECONDS.sleep( 100 );

		log.info( "1 : {}", submit1 );
		log.info( "2 : {}", submit2 );
		log.info( "3 : {}", submit3 );

		System.exit( 0 );
	}
}
