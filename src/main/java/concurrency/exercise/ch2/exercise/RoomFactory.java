package concurrency.exercise.ch2.exercise;

import java.util.concurrent.ThreadFactory;

@Deprecated
public class RoomFactory implements ThreadFactory {

	int count;

	public RoomFactory() {
		System.out.println( "RoomFactory constructor" );
	}

	@Override
	public Thread newThread( Runnable r ) {

		return new Thread( RunnableRoom.newInstance( count++ ) );
	}
}
