package concurrency.exercise.ch5.ev.exercise1;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Monitor implements Runnable {

	private final Elevator elevator;

	public Monitor( Elevator elevator ) {
		this.elevator = elevator;
	}

	private void printFloor() {

		for ( int i = 10; i > 0; --i ) {
			if ( i == elevator.getCurrentFloor() ) {
				log.info( "{} 층 | {}", String.format( "%2d", i ), elevator.getCurrentDirection() );
			} else {
				log.info( "{} 층 | {}", String.format( "%2d", i ), "" );
			}
		}
		printLine();
	}

	private void printElevator() {

		log.info( " elevator : {}", elevator.customString() );
		printLine();
	}

	private void printLine() {
		log.info( "=================================================" );
	}

	@Override
	public void run() {

		printFloor();
		printElevator();
	}
}
