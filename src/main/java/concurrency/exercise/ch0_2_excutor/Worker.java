package concurrency.exercise.ch0_2_excutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Worker implements Runnable {

	private int id;
	private Job job;

	@Override
	public void run() {

		int sum = this.job.getFirst() + this.job.getSecond();

		System.out.println( this.toString() + " sum : " + sum + " Thread : " + Thread.currentThread().getName() );
	}
}
