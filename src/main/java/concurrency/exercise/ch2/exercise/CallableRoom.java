package concurrency.exercise.ch2.exercise;

import lombok.Setter;
import lombok.ToString;

import java.util.concurrent.Callable;

@ToString
public class CallableRoom implements Callable< Integer > {

	private final int number;

	@Setter
	private Human human;

	private CallableRoom( int number ) {
		this.number = number;
	}

	public static CallableRoom newInstance( int number ) {

		CallableRoom room = new CallableRoom( number );
		room.setHuman( new Human() );
		return room;
	}

	@Override
	public Integer call() {

		System.out.println( "call Method Room : " + number );

		if ( null != human ) {
			human.process();
		}
		return human.getCurrentHp();
//		return null;
//		return number;
	}

}
