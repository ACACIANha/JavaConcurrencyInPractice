package concurrency.exercise.ch5.ev.exercise2;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
public class Monitor implements Runnable {

	private final Elevator elevator1;
	private final Elevator elevator2;
	private final Elevator elevator3;
	private final BlockingQueue< Passenger > passengers;

	public Monitor( Elevator elevator1, Elevator elevator2, Elevator elevator3, BlockingQueue< Passenger > passengers ) {
		this.elevator1 = elevator1;
		this.elevator2 = elevator2;
		this.elevator3 = elevator3;
		this.passengers = passengers;
	}

	private void printFloor() {

		for ( int i = 10; i > 0; --i ) {
			log.info( "{} 층 | {}  {}  {}",
					String.format( "%2d", i ),
					i == elevator1.getCurrentFloor() ? elevator1.getCurrentDirection() : "    ",
					i == elevator2.getCurrentFloor() ? elevator2.getCurrentDirection() : "    ",
					i == elevator3.getCurrentFloor() ? elevator3.getCurrentDirection() : "    " );
		}
	}

	private void printElevator() {

		log.info( " elevator1 : {} |  elevator2 : {} |  elevator1 : {}", elevator1.customString(), elevator2.customString(), elevator3.customString() );
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
