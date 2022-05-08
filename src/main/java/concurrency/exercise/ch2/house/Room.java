package concurrency.exercise.ch2.house;

import java.util.concurrent.Callable;

//@ToString
public class Room implements Callable< String > {

	private static final int decreasePoint = 1;

	private final int roomNum;
	private Person workPerson = null;

	private Room( int roomNum ) {
		this.roomNum = roomNum;
	}

	public static Room newInstance( int roomNum ) {
		return new Room( roomNum );
	}

	public void setPerson( Person person ) {
		if ( null == person ) {
			return;
		}
		this.workPerson = person;
	}

	@Override
	public String call() throws Exception {

		if ( workPerson != null ) {

			if ( workPerson.decreasePoint( decreasePoint ) ) {
				clearWorkPerson(); // 큐 공유받아서 바로 세팅해줘도 무방
			}
		}

		return this.toString();
	}

	@Override
	public String toString() {
		return String.format( "{room:%2s,person:%10s} ",
				roomNum, workPerson != null ? workPerson.toString() : "EMPTY" );
	}

	boolean isEmpty() {
		return workPerson == null;
	}

	void clearWorkPerson() {

		workPerson = null;
	}
}
