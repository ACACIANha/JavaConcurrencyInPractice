package concurrency.exercise.ch3.lru.exercise;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashSet;
import java.util.Optional;

@Slf4j
public class LRUcache {

	private final LinkedHashSet< Integer > caches;

	private final int cacheSize = 5;

	public LRUcache() {
		this.caches = new LinkedHashSet<>( cacheSize );
	}

	public String add( Integer value ) {

		if ( caches.contains( value ) ) {
			log.info( "HIT : {}", value );

			caches.remove( value );
			this.addAndPrint( value );

			return "HIT";
		}
		log.info( "FAULT : {}", value );

		if ( caches.size() >= cacheSize ) {
			this.findFirstElement().ifPresent( caches::remove );
		}
		this.addAndPrint( value );

		return "FAULT!";
	}

	private void addAndPrint( Integer value ) {
		this.caches.add( value );
		this.printCache();
	}

	public void printCache() {
		log.info( "caches : {}", caches );
	}

	private Optional< Integer > findFirstElement() {

		return caches.stream().findFirst();
	}


}
