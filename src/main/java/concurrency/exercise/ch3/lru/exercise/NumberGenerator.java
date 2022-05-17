package concurrency.exercise.ch3.lru.exercise;

import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.TimeUnit;

public class NumberGenerator implements Runnable {

	private final LRUcache cache;

	public NumberGenerator( LRUcache cache ) {
		this.cache = cache;
	}

	@SneakyThrows
	@Override
	public void run() {

		while ( !Thread.currentThread().isInterrupted() ) {

			TimeUnit.SECONDS.sleep( 1 );
			cache.add( RandomUtils.nextInt( 0, 10 ) );
			System.out.println();
		}
	}
}
