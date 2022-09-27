package concurrency.exercise.ch5.ev.exercise2;

import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;

@Slf4j
@Getter
@ToString
public class Elevator implements Runnable {

	Direction currentDirection = Direction.NONE;
	int currentFloor = 1;
	Passenger passenger = null;

	State state = State.EMPTY;

	private final BlockingQueue< Passenger > passengers;


	public static Elevator instance( int startFloor, BlockingQueue< Passenger > passengers ) {
		return new Elevator( startFloor, passengers );
	}

	private Elevator( int startFloor, BlockingQueue< Passenger > passengers ) {

		this.currentFloor = startFloor;
		this.passengers = passengers;
	}

	public void changeCurrentState( State state ) {

		this.state = state;
	}

	public void changeCurrentDirection( Direction direction ) {

		this.currentDirection = direction;
	}

	public void changeToFloor( int floor ) {
		if ( floor - currentFloor == 0 ) {
			return;
		}
		if ( floor - currentFloor > 0 ) {
			changeCurrentDirection( Direction.UP );
		} else {
			changeCurrentDirection( Direction.DOWN );
		}
	}

	@Override
	public void run() {

		if ( state == State.EMPTY ) {
			searchPassenger();
			move();
			liftCheck();
		} else if ( state == State.FULL ) {
			move();
			passengerCheck();
		}
	}

	private void liftCheck() {
		if ( passenger == null ) {
			return;
		}
		if ( passenger.startFloor - currentFloor == 0 ) {
			passenger.lift();
			changeCurrentState( State.FULL );
			changeToFloor( passenger.destinationFloor );
		}
	}

	private void searchPassenger() {
		if ( passenger == null ) {
			settingPassengerOn( passengers.poll() );
		}
	}

	private void settingPassengerOn( Passenger passenger ) {

		if ( passenger == null ) {
//			log.info( " 남은 승객 없음" );
			changeCurrentDirection( Direction.NONE );
			return;
		}

		this.passenger = passenger;
		changeToFloor( passenger.startFloor );
	}

	private void move() {

		currentFloor += currentDirection.getMoveDir();
	}

	private void passengerCheck() {
		if ( passenger.isDestination( currentFloor ) ) {
			drop();
		}
	}


	private void drop() {

		passenger.drop();
		passenger = null;

		changeCurrentState( State.EMPTY );
		changeCurrentDirection( Direction.NONE );
	}

	public String customString() {

		return String.format( " 현재 층수 : %d, 방향 : %s, 상태 : %s ", currentFloor, currentDirection, state );
	}
}
