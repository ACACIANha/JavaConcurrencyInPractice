package concurrency.exercise.ch5.ev.exercise1;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestMain {

	public static void main( String[] args ) {

		List< Passenger > passengers = new ArrayList<>();
		passengers.add( new Passenger( 1, 3 ) );
		passengers.add( new Passenger( 1, 3 ) );
		passengers.add( new Passenger( 1, 3 ) );
		passengers.add( new Passenger( 1, 2 ) );
		passengers.add( new Passenger( 1, 2 ) );
		passengers.add( new Passenger( 1, 6 ) );
		passengers.add( new Passenger( 1, 6 ) );
		passengers.add( new Passenger( 1, 10 ) );
		passengers.add( new Passenger( 1, 10 ) );
		passengers.add( new Passenger( 1, 10 ) );

		ScheduledExecutorService service = Executors.newScheduledThreadPool( 2 );

		Elevator elevator = new Elevator();
		Monitor monitor = new Monitor( elevator );

		passengers.forEach( elevator::liftPassenger );
		elevator.eventButton( 10 );

		service.scheduleAtFixedRate( monitor, 0, 2, TimeUnit.SECONDS );
		service.scheduleAtFixedRate( elevator, 1, 2, TimeUnit.SECONDS );
//		service.scheduleWithFixedDelay( monitor, 0, 2, TimeUnit.SECONDS );
//		service.scheduleWithFixedDelay( elevator, 1, 2, TimeUnit.SECONDS );
	}

}
