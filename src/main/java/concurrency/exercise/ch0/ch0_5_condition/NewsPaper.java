package concurrency.exercise.ch0.ch0_5_condition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NewsPaper {
	private String news;
	private boolean isNews;

	private final ReentrantLock key = new ReentrantLock();
	private final Condition reader = key.newCondition();

	public void setNews( String news ) {
		this.news = news;
		isNews = true;
		key.lock();

		try {
			reader.signalAll();
		} finally {
			key.unlock();
		}

	}

	public String getNews() {
		key.lock();
		try {
			if ( !isNews ) {
				reader.await();
			}
		} catch ( InterruptedException e ) {
			e.printStackTrace();
		} finally {
			key.unlock();
		}
		return news;
	}
}
