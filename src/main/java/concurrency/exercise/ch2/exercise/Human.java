package concurrency.exercise.ch2.exercise;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Human {

	private static int humanCount;

	private int num;

	private int currentHp;
	private int maxHp;

	protected Human() {
		this.num = humanCount++;
		this.maxHp = ( int ) ( Math.random() * 10 );
		this.currentHp = this.maxHp;
	}

	public void process() {
		--currentHp;
		System.out.println( "human process : " + this );
	}

}
