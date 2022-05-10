package concurrency.exercise.ch2.exercise3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Monitor implements Runnable {

	private final ConcurrentLinkedQueue< Person > personQueue;
	private final List< Room > roomTasks;

	private Monitor( ConcurrentLinkedQueue< Person > personQueue, List< Room > roomTasks ) {
		this.personQueue = personQueue;
		this.roomTasks = roomTasks;
	}

	public static Monitor newInstance( ConcurrentLinkedQueue< Person > personQueue, List< Room > roomTasks ) {

		return new Monitor( personQueue, roomTasks );
	}


	@SneakyThrows
	@Override
	public void run() {

		while ( hasWorkingTask() ) {

			log.info( "{}|persons: {}", personQueue.size(), personQueue );
			StringBuilder temp = new StringBuilder();
			roomTasks.forEach( room -> temp.append( room.toString() ).append( " " ) );
			log.info( "{} ", temp );

			int sleepTerm = 1;
			TimeUnit.MILLISECONDS.sleep( 100 * sleepTerm );
		}
	}

	public boolean hasWorkingTask() {

		return !personQueue.isEmpty() || roomTasks.stream().anyMatch( Room::isWorking );
	}

}
