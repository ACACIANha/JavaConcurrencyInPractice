package concurrency.exercise.ch5.ev.exercise1;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class Passenger {

	static int staticIndex = 0;

	int uniqueIndex;
	String name;
	int currentFloor;
	int destinationFloor;

	private Passenger() {
		uniqueIndex = staticIndex++;
	}

	public Passenger( int currentFloor, int destinationFloor ) {
		this();
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
	}

	public void lift() {
		log.info( "{} 승객이 탑승했다 ", uniqueIndex );
	}
	public void drop() {
		log.info( "{} 승객이 하차했다 ", uniqueIndex );
	}
}
