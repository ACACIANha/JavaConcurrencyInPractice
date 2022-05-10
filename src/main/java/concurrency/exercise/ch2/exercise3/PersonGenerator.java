package concurrency.exercise.ch2.exercise3;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

public class PersonGenerator implements Runnable {

	private final ConcurrentLinkedQueue< Person > personQueue;

	private PersonGenerator( ConcurrentLinkedQueue< Person > personQueue ) {
		this.personQueue = personQueue;
	}

	public static PersonGenerator newInstance( ConcurrentLinkedQueue< Person > personQueue ) {

		return new PersonGenerator( personQueue );
	}

	@SneakyThrows
	@Override
	public void run() {

		while ( !Thread.currentThread().isInterrupted() ) {
			personQueue.add( Person.newInstance( RandomUtils.nextInt( 1, 3 ) ) );
			TimeUnit.SECONDS.sleep( 3 );
		}
	}
}
