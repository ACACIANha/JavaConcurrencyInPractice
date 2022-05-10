package concurrency.exercise.ch2.exercise3;

public class Person {

	private static int serialNum;

	private final int num;

	private final int maxPoint;
	private int currentPoint;

	private Person( int num, int maxPoint ) {
		this.num = num;
		this.maxPoint = maxPoint;
		this.currentPoint = maxPoint;
	}

	public static Person newInstance( int maxPoint ) {
		return new Person( serialNum++, maxPoint );
	}

	public void decreasePoint( int point ) {
		if ( point < 0 ) {
			throw new ArithmeticException();
		}
		currentPoint -= point;
	}

	public boolean isDead() {
		return currentPoint < 0;
	}

	@Override
	public String toString() {
		return String.format( "%2s,[%s/%s]", num, currentPoint, maxPoint );
	}
}
