package concurrency.exercise.ch5.ev.exercise2;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class Passenger {

	static int staticIndex = 0;

	int uniqueIndex;
	//	String name;
	int startFloor;
	int destinationFloor;


	public static Passenger instance( int startFloor, int destinationFloor ) {

		return new Passenger( startFloor, destinationFloor );
	}

	private Passenger() {
		uniqueIndex = staticIndex++;
	}

	private Passenger( int startFloor, int destinationFloor ) {
		this();
		this.startFloor = startFloor;
		this.destinationFloor = destinationFloor;
	}

	public void lift() {
		log.info( "{} 승객이 탑승했다 ", uniqueIndex );
	}

	public void drop() {
		log.info( "{} 승객이 하차했다 ", uniqueIndex );
	}

	public boolean isDestination( int currentFloor ) {
		return this.destinationFloor == currentFloor;
	}
}
