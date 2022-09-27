package concurrency.exercise.ch5.ev.exercise2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestMain {

	public static void main( String[] args ) {

		int elevatorCount = 3;

		ScheduledExecutorService service = Executors.newScheduledThreadPool( elevatorCount + 1 );

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

		List< Elevator > elevators = new ArrayList<>();
		for ( int i = 0; i < elevatorCount; ++i ) {
			elevators.add( Elevator.instance( ( int ) ( Math.random() * 10 ), passengers ) );
		}

		Monitor monitor = Monitor.instance( elevators, passengers );

		int timePeriod = 1;
		service.scheduleAtFixedRate( monitor, 0, timePeriod, TimeUnit.SECONDS );

		for ( Elevator elevator : elevators ) {
			service.scheduleAtFixedRate( elevator, 1, timePeriod, TimeUnit.SECONDS );
		}
	}
}
