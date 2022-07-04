package concurrency.exercise.ch5.ev.exercise1;

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
}
