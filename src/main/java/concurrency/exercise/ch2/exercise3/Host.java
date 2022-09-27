package concurrency.exercise.ch2.exercise3;

import java.util.List;
import java.util.Queue;

public class Host implements Runnable {

	private final Queue< Person > personQueue;
	private final List< Room > rooms;

	private Host( Queue< Person > personQueue, List< Room > roomTasks ) {
		this.personQueue = personQueue;
		this.rooms = roomTasks;

		rooms.forEach( room -> room.enterPerson( personQueue.poll() ) );
	}

	public static Host newInstance( Queue< Person > personQueue, List< Room > roomTasks ) {
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
