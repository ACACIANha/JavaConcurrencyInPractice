package concurrency.exercise.ch2.exercise3;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Host implements Runnable {

	private final ConcurrentLinkedQueue< Person > personQueue;
	private final List< Room > rooms;

	private Host( ConcurrentLinkedQueue< Person > personQueue, List< Room > roomTasks ) {
		this.personQueue = personQueue;
		this.rooms = roomTasks;

		rooms.forEach( room -> {
			room.enterPerson( personQueue.poll() );
		} );
	}

	public static Host newInstance( ConcurrentLinkedQueue< Person > personQueue, List< Room > roomTasks ) {
		return new Host( personQueue, roomTasks );
	}

	@Override
	public void run() {

		while ( !Thread.currentThread().isInterrupted() ) {
			rooms.stream().filter( Room::isWaiting )
					.forEach( room -> room.enterPerson( personQueue.poll() ) );
		}
	}
}
