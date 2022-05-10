package concurrency.exercise.ch2.exercise2;

import lombok.Getter;

@Getter
//@ToString
public class Person {

	private final int num;

	private int currentPoint;
	private final int maxPoint;

	private Person( int num, int maxPoint ) {

		this.num = num;
		this.maxPoint = maxPoint;
		this.currentPoint = maxPoint;
	}

	public static Person newInstance( int num, int maxPoint ) {
		return new Person( num, maxPoint );
	}

	public boolean decreasePoint( int point ) {

		currentPoint -= point;
		if ( currentPoint < 0 ) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.format( "{%s,[%s/%s]}", num, currentPoint, maxPoint );
	}
}
