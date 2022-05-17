package exercise.state.player;

import exercise.state.State;
import exercise.state.StateMachine;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RunState extends State< Player > {
	public RunState( StateMachine< Player > machine ) {
		super( machine );
	}

	@Override
	public void enter() {
		log.info( "run enter" );
	}

	@SneakyThrows
	@Override
	public void execute() {

		log.info( "run execute" );
		TimeUnit.SECONDS.sleep( 2 );
		machine.changeState( "idle" );
	}

	@Override
	public void exit() {
		log.info( "run exit" );
	}
}
