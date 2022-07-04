package concurrency.exercise.ch5.ev.exercise1;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.IntStream;

@Slf4j
@ToString
public class Passengers {

	Map< Integer, LinkedList< Passenger > > passengers;

	Passengers() {
		passengers = new HashMap<>();
		IntStream.rangeClosed( 1, 10 ).forEach(
				destFloor -> passengers.put( destFloor, new LinkedList<>() )
		);
	}

	public void add( Passenger passenger ) {

		passengers.get( passenger.getDestinationFloor() ).add( passenger );
	}

	public Passenger pop( int floor ) {
		return passengers.get( floor ).pop();
	}

	@Deprecated
	public void add( int destinationFloor, Passenger passenger ) {

		passengers.get( destinationFloor ).add( passenger );
	}

	public boolean isEmpty() {

		return count() == 0;
	}

	public int count() {

		return passengers.values().stream()
				.mapToInt( List::size )
				.reduce( 0, Integer::sum );
	}

	public boolean ableDropPassenger( int floor ) {

		return !passengers.get( floor ).isEmpty();
	}

}
