package concurrency.exercise.ch2.exercise3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Monitor implements Runnable {

	private final Queue< Person > personQueue;
	private final List< Room > roomTasks;

	private long elapseTime;
	private long prevTime;
	private long currentTime;

	private Monitor( Queue< Person > personQueue, List< Room > roomTasks ) {
		this.personQueue = personQueue;
		this.roomTasks = roomTasks;


		this.prevTime = System.currentTimeMillis();
		this.currentTime = System.currentTimeMillis();
	}

	public static Monitor newInstance( Queue< Person > personQueue, List< Room > roomTasks ) {

		return new Monitor( personQueue, roomTasks );
	}


	@SneakyThrows
	@Override
	public void run() {

		while ( hasWorkingTask() ) {

			timeProcess();
			log.info( "{}|persons: {}", personQueue.size(), personQueue );
			StringBuilder temp = new StringBuilder();
			roomTasks.forEach( room -> temp.append( room.toString() ).append( " " ) );
			log.info( "{} ", temp );

			int sleepTerm = 10;
			TimeUnit.MILLISECONDS.sleep( 100 * sleepTerm );
		}
	}

	private void timeProcess() {
		this.currentTime = System.currentTimeMillis();
		long deltaTime = this.currentTime - this.prevTime;
		this.elapseTime += deltaTime;
		log.info( "모니터 경과시간: {}", ( long ) ( this.elapseTime * 0.001 ) );
		this.prevTime = this.currentTime;
	}

	private boolean hasWorkingTask() {

		return !personQueue.isEmpty() || roomTasks.stream().anyMatch( Room::isWorking );
	}

}
