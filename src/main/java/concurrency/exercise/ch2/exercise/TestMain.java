package concurrency.exercise.ch2.exercise;

import java.util.concurrent.ExecutionException;

public class TestMain {

	public static void main( String[] args ) throws ExecutionException, InterruptedException {

//		House house = new House( 4 );
//		house.taskInitial();
//		house.process();
//		house.process();
//		house.process();

		House.newInstance( 2 ).start();
	}
}
