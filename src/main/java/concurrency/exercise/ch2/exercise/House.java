package concurrency.exercise.ch2.exercise;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class House extends Thread {

	private long roomTime = ( long ) 1.1;
	private int roomCount;
	private AtomicInteger humanCount;
	private ExecutorService service;
	private List< CallableRoom > rooms;

	private House( int roomCount ) {

		this.roomCount = roomCount;
		this.rooms = new ArrayList<>( roomCount );
		this.service = Executors.newFixedThreadPool( roomCount );
	}

	private void roomSetting() {

		System.out.println( "roomSetting " );
		for ( int i = 0; i < roomCount; ++i ) {
			rooms.add( CallableRoom.newInstance( i ) );
		}
	}

	public static House newInstance( int roomCount ) {

		House house = new House( roomCount );
		house.roomSetting();
		return house;
	}


	@SneakyThrows
	public void process() {
		System.out.println( "process " );

		for ( var room : rooms ) {

			Future< Integer > submit = service.submit( room );

			Integer result = submit.get();
			if ( result == 0 ) {

				room.setHuman( new Human() );
			}
		}
	}

	@SneakyThrows
	@Override
	public void run() {
		super.run();
		System.out.println( "run call " );
		while ( true ) {
			System.out.println( "run method " );
			process();
			Thread.sleep( 1000 * roomTime );
		}
	}
}
