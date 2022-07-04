package concurrency.exercise.ch5.ev.exercise1;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@ToString
public class Elevator implements Runnable {

	Direction currentDirection = Direction.NONE;
	Direction destinationDirection = Direction.NONE;
	int currentFloor = 1;
	int destinationFloor = 1;
	int currentPassenger = 0;
	int maxPassenger = 10;
	Passengers passengers = new Passengers();

	public void changeDestinationDirection( Direction direction ) {

		this.destinationDirection = direction;
	}

	public void changeCurrentDirection( Direction direction ) {

		this.currentDirection = direction;
	}

	private void dropAllPassenger() {

		changeCurrentDirection( Direction.NONE );
		changeDestinationDirection( Direction.NONE );
		log.info( "모든 승객 하차" );
	}

	public void liftPassenger( Passenger passenger ) {
		if ( passengers.count() < maxPassenger ) {
			passenger.lift();
			passengers.add( passenger );
		} else {
			log.info( "full condition " );
		}
	}

	// 버튼을 누른 방향도 중요할듯
	public void buttonEvent( int floor ) {

		Direction direction = currentFloor < floor ? Direction.UP : Direction.DOWN;

		changeDestinationDirection( direction );
	}

	public void eventButton( int floor ) {

		if ( currentFloor < floor ) {
			changeDestinationDirection( Direction.UP );
		} else {
			changeDestinationDirection( Direction.DOWN );
		}
	}

	@Override
	public void run() {

		if ( passengers.ableDropPassenger( currentFloor ) ) {
			changeCurrentDirection( Direction.NONE );
			passengers.pop( currentFloor ).drop();
			return;
		}
		if ( passengers.isEmpty() ) {
			dropAllPassenger();
			return;
		}
		move();
	}

	private void move() {
		changeCurrentDirection( destinationDirection );
		currentFloor += currentDirection.getMoveDir();
	}

	public String customString() {

		return String.format( " 현재 층수 : %d | 방향 : %s | 탑승 인원 : %d | ", currentFloor, currentDirection, passengers.count() );
	}
}
