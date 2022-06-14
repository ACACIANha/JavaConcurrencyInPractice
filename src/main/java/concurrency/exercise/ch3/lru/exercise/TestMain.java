package concurrency.exercise.ch3.lru.exercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TestMain {

	public static void main( String[] args ) {

//		LRUcache lrUcache = new LRUcache();
//		IntStream.range( 0, 5 ).forEach( lrUcache::process );
//		IntStream.range( 2, 4 ).forEach( lrUcache::process );
//		IntStream.range( 0, 3 ).forEach( lrUcache::process );
//		IntStream.range( 3, 6 ).forEach( lrUcache::process );

		LRUcache lrUcache = new LRUcache();
		Executors.newSingleThreadExecutor().submit( new NumberGenerator( lrUcache ) );

//		NumberGenerator task1 = new NumberGenerator( lrUcache );
//		NumberGenerator task2 = new NumberGenerator( lrUcache );
//		ExecutorService service = Executors.newFixedThreadPool( 2 );
//		service.submit( task1 );
//		service.submit( task2 );
	}
}
