package concurrency.exercise.ch2.exercise3;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

@Slf4j
public class MainTest {

	public static void main( String[] args ) throws InterruptedException {

		final int personSize = 20;
		final int minPoint = 3;
		final int maxPoint = 10;

		final int roomSize = 5;

		ConcurrentLinkedQueue< Person > personQueue = new ConcurrentLinkedQueue<>();

		IntStream.range( 0, personSize ).forEach( i -> {
			personQueue.add( Person.newInstance( RandomUtils.nextInt( minPoint, maxPoint ) ) );
		} );

		House house = House.newInstance( personQueue, roomSize );
		house.start();
		house.join();

		log.info( "main exit" );
	}
}
