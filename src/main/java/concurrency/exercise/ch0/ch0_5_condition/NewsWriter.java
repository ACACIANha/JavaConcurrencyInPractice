package concurrency.exercise.ch0.ch0_5_condition;

public class NewsWriter extends Thread {
	private NewsPaper paper;

	public NewsWriter( NewsPaper paper ) {
		this.paper = paper;
	}

	@Override
	public void run() {
		paper.setNews( "열기가 뜨겁습니다." );
	}
}
