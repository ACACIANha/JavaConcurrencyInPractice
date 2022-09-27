package concurrency.exercise.ch5.ev.exercise2;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.BlockingQueue;

@Slf4j
public class Monitor implements Runnable {

	private final List< Elevator > elevators;
	private final BlockingQueue< Passenger > passengers;

	public static Monitor instance( List< Elevator > elevators, BlockingQueue< Passenger > passengers ) {

		return new Monitor( elevators, passengers );
	}

	private Monitor( List< Elevator > elevators, BlockingQueue< Passenger > passengers ) {
		this.elevators = elevators;
		this.passengers = passengers;
	}

	private void printFloor() {

		for ( int i = 10; i > 0; --i ) {

			String prefix = "{} 층 | ";
			for ( int j = 0; j < elevators.size(); ++j ) {
				prefix += " [ {} ] ";
			}

			log.info( prefix, String.format( "%2d", i ),
					i == elevators.get( 0 ).getCurrentFloor() ? elevators.get( 0 ).getCurrentDirection() : "    ",
					i == elevators.get( 1 ).getCurrentFloor() ? elevators.get( 1 ).getCurrentDirection() : "    ",
					i == elevators.get( 2 ).getCurrentFloor() ? elevators.get( 2 ).getCurrentDirection() : "    " );
		}
	}

	private void printElevator() {

		for ( int i = 0; i < elevators.size(); ++i ) {
			log.info( " {}호기 : {}", i + 1, elevators.get( i ).customString() );
		}
//		log.info( " elevator1 : {} |  elevator2 : {} |  elevator3 : {}",
//				elevators.get( 0 ).customString(), elevators.get( 1 ).customString(), elevators.get( 2 ).customString() );
	}

	private void printPassengers() {

		log.info( " passengers : {}", passengers.toString() );
	}

	private void printLine() {
		log.info( "=================================================" );
	}

	@Override
	public void run() {
		printLine();
		printFloor();
		printLine();
		printElevator();
		printPassengers();
		printLine();
	}
}
