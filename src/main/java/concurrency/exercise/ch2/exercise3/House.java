package concurrency.exercise.ch2.exercise3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class House extends Thread {

	private final Queue< Person > personQueue;
	private final int roomSize;

	private ExecutorService service;

	private List< Room > rooms;
	private Monitor monitor;
	private Host host;
	private PersonGenerator generator;


	private House( Queue< Person > personQueue, int roomSize ) {
		this.personQueue = personQueue;
		this.roomSize = roomSize;

		this.init();
	}

	private void init() {

		// 3 대신 좋은 방법 찾아보기
		service = Executors.newFixedThreadPool( roomSize + 3 ); // + monitor, inputer + generator

		rooms = new ArrayList<>( roomSize );
		for ( int i = 0; i < roomSize; ++i ) {
			rooms.add( Room.newInstance( i ) );
		}

		monitor = Monitor.newInstance( personQueue, rooms );
		host = Host.newInstance( personQueue, rooms );
		generator = PersonGenerator.newInstance( personQueue );
	}

	public static House newInstance( Queue< Person > personQueue, int roomSize ) {
		return new House( personQueue, roomSize );
	}

	@SneakyThrows
	@Override
	public void run() {

		rooms.forEach( room -> service.submit( room ) );

		service.submit( this.generator );
		service.submit( this.host );
		service.submit( this.monitor ).get();

		log.info( "House task is done" );
		service.shutdownNow();
		log.info( "shutdownNow" );

	}

}
