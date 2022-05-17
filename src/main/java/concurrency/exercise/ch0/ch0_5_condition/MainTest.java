package concurrency.exercise.ch0.ch0_5_condition;

public class MainTest {

	public static void main( String[] args ) {

		NewsPaper newsPaper = new NewsPaper();
		NewsReader newsReader1 = new NewsReader( newsPaper );
		NewsReader newsReader2 = new NewsReader( newsPaper );
		NewsWriter newsWriter = new NewsWriter( newsPaper );


		try {
			newsReader1.start();
			newsReader2.start();

			Thread.sleep( 10000 );
			newsWriter.start();

			newsReader1.join();
			newsReader2.join();
			newsWriter.join();

		} catch ( InterruptedException e ) {
			e.printStackTrace();
		}


	}
}
