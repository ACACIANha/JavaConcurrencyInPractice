package concurrency.exercise.ch2.house;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class House extends Thread {

	private final double sleepTimeSeconds = 0.2;
	private final long sleepTimeMilli = ( long ) ( 1000 * sleepTimeSeconds );

	private final Queue< Person > personQueue;
	private final int roomSize;

	private List< Room > roomTasks;

	private ExecutorService service;

	AtomicReference< String > printString = new AtomicReference<>( StringUtils.EMPTY );

	public House( ConcurrentLinkedQueue< Person > personQueue, int roomSize ) {
		this.personQueue = personQueue;
		this.roomSize = roomSize;

		this.init();
	}

	public static House newInstance( ConcurrentLinkedQueue< Person > personQueue, int roomSize ) {
		return new House( personQueue, roomSize );
	}

	void init() {

		service = Executors.newFixedThreadPool( roomSize );

		roomTasks = new ArrayList<>( roomSize );
		for ( int i = 0; i < roomSize; ++i ) {
			Room room = Room.newInstance( i );
			room.setPerson( personQueue.poll() );
			this.roomTasks.add( room );
		}
	}

	@SneakyThrows
	@Override
	public void run() {

		while ( !allPersonIsEmpty() ) {
//			log.info( "size : {}, personQueue : {} ", personQueue.size(), personQueue );
			roomProcess();
			Thread.currentThread().sleep( sleepTimeMilli );
		}
		log.info( "all person work is done" );
	}

	void roomProcess() {
		List< Future< String > > futures = new ArrayList<>( roomSize );

		for ( var roomTask : roomTasks ) {

			futures.add( service.submit( roomTask ) );
		}

		boolean allWorkIsDone = false;
		while ( !allWorkIsDone ) {
			allWorkIsDone = true;
			for ( int i = 0; i < futures.size(); ++i ) {
				if ( !futures.get( i ).isDone() ) {
					allWorkIsDone = false;
				}
			}
		}

		addPersonToTask();

		futures.forEach( future -> {
			try {
//				log.info( "room : {} ", future.get() );
				String temp = printString.get() + future.get();
				printString.set( temp );
			} catch ( InterruptedException | ExecutionException e ) {
				log.error( "error : ", e );
			}
		} );

		log.info( "\n\t\t\t\t {}", printString );
		printString.set( StringUtils.EMPTY );

	}

	void addPersonToTask() {

		roomTasks.stream()
				.filter( Room::isEmpty )
				.forEach( room -> room.setPerson( personQueue.poll() ) );
	}

	boolean allPersonIsEmpty() {
		return roomTasks.stream().allMatch( Room::isEmpty );
	}

}
