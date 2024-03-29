package concurrency.exercise.ch5.ev.exercise2;

import lombok.Getter;

@Getter
public enum Direction {

	UP( 1 ),
	NONE( 0 ),
	DOWN( -1 );

	private int moveDir;

	Direction( int moveDir ) {
		this.moveDir = moveDir;
	}


	@Override
	public String toString() {
		return String.format( "%-4s", name() );
	}
}
