package concurrency.exercise.ch2.exercise3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Room implements Runnable {

	private final int decreasePoint = 1;

	private final int num;
	private AtomicReference< Person > aWorkPerson;

	private Lock lock;
	private Condition lockCondition;

	private AtomicBoolean aWaiting = new AtomicBoolean( true );

	private Room( int num ) {
		this.num = num;
		lock = new ReentrantLock();
		lockCondition = lock.newCondition();

		aWorkPerson = new AtomicReference<>( null );
	}

	public static Room newInstance( int roomNum ) {
		return new Room( roomNum );
	}

	public void enterPerson( Person person ) {
		if ( null == person ) {
			return;
		}
		this.aWorkPerson.set( person );
		this.resume();
	}

	@SneakyThrows
	@Override
	public void run() {

		while ( !Thread.currentThread().isInterrupted() ) {

			if ( null != aWorkPerson.get() ) { //Objects.nonNull( workPerson )
				aWorkPerson.get().decreasePoint( decreasePoint );
				deadCheck();
			}
			TimeUnit.SECONDS.sleep( 1 );
		}
	}

	private synchronized void deadCheck() {
		if ( aWorkPerson.get().isDead() ) {
			aWorkPerson.set( null );
			this.waitForSignal();
		}
	}

	public boolean isEmpty() {
		return aWorkPerson.get() == null;
	}

	private void waitForSignal() {

		lock.lock();
		try {
//			Collection< Thread > waitingThreads = ( ( AbstractQueuedSynchronizer ) lockCondition ).getWaitingThreads( ( AbstractQueuedSynchronizer.ConditionObject ) lockCondition );
			aWaiting.set( true );
			this.lockCondition.await();
//			Thread.currentThread().wait();
		} catch ( InterruptedException e ) {
//			e.printStackTrace();
			Thread.currentThread().interrupt();
//			log.error( "interrupted : {} ", e.getMessage() );
		} finally {
			lock.unlock();
		}
	}

	public void resume() {
		lock.lock();
		this.lockCondition.signalAll();
		aWaiting.set( false );
//		Thread.currentThread().notify();
		lock.unlock();
	}

	public boolean isWaiting() {
		//TODO: 이거대신 현재 쓰레드
//		return Thread.currentThread().getState() == Thread.State.WAITING;
		return aWaiting.get();
	}

	public boolean isWorking() {
		return !this.isWaiting();
	}

	@Override
	public String toString() {
		return String.format( "[room-%2s person-%8s]", num, aWorkPerson.get() != null ? aWorkPerson.get() : "EMPTY" );
	}
}
