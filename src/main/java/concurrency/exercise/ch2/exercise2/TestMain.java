package concurrency.exercise.ch2.exercise2;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.*;

@Slf4j
public class TestMain {

	public static void main( String[] args ) throws ExecutionException, InterruptedException {

		final int personSize = 10;
		final int minPoint = 3;
		final int maxPoint = 10;

		final int roomSize = 5;

		ConcurrentLinkedQueue< Person > personQueue = new ConcurrentLinkedQueue<>();

		for ( int i = 0; i < personSize; ++i ) {
			personQueue.add( Person.newInstance( i, RandomUtils.nextInt( minPoint, maxPoint ) ) );
		}
//		personQueue.forEach( person -> log.info( "person : {} ", person ) );
		log.info( "size : {}, personQueue : {} ", personQueue.size(), personQueue );

		// runnable 로 만들었을때
//		House house = House.newInstance( personQueue, roomSize );
//		ExecutorService service = Executors.newSingleThreadExecutor();
//		Future< ? > submit = service.submit( house );
//		log.info( "main exit!" );

//		Object o = submit.get();
//		log.info( "service exit : {} ", o );
//		service.shutdown();

		// thread 로 만들었을때
		House house = House.newInstance( personQueue, roomSize );
		house.start();
		log.info( "main exit!" );
		house.join();
		log.info( "service exit!" );

	}

}
