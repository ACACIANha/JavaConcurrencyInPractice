package concurrency.exercise.ch0.ch0_5_condition;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NewsReader extends Thread {

	private NewsPaper paper;

	public NewsReader( NewsPaper paper ) {
		this.paper = paper;
	}

	@Override
	public void run() {
		log.info( "오늘의 뉴스: {}", paper.getNews() );
	}
}
