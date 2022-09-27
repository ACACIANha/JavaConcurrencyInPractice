package concurrency.exercise.ch5.ev.exercise2;

import java.util.concurrent.*;

public class TestMain {

	public static void main( String[] args ) {

		ScheduledExecutorService service = Executors.newScheduledThreadPool( 4 );

		BlockingQueue< Passenger > passengers = new ArrayBlockingQueue<>( 20 );

		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
		passengers.add( Passenger.instance( 1, 10 ) );
//		passengers.add( Passenger.instance( 8, 2 ) );
//		passengers.add( Passenger.instance( 1, 9 ) );
//		passengers.add( Passenger.instance( 10, 2 ) );

		Elevator elevator1 = Elevator.instance( 1, passengers );
		Elevator elevator2 = Elevator.instance( 10, passengers );
		Elevator elevator3 = Elevator.instance( 5, passengers );

		Monitor monitor = new Monitor( elevator1, elevator2, elevator3, passengers );

		int timePeriod = 1;
		service.scheduleAtFixedRate( monitor, 0, timePeriod, TimeUnit.SECONDS );
		service.scheduleAtFixedRate( elevator1, 1, timePeriod, TimeUnit.SECONDS );
		service.scheduleAtFixedRate( elevator2, 1, timePeriod, TimeUnit.SECONDS );
		service.scheduleAtFixedRate( elevator3, 1, timePeriod, TimeUnit.SECONDS );
	}
}
