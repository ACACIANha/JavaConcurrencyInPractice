package exercise.state.player;

import exercise.state.State;
import exercise.state.StateMachine;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RollState extends State< Player > {
	public RollState( StateMachine< Player > machine ) {
		super( machine );
	}

	@Override
	public void enter() {
		log.info( "roll enter" );

	}

	@SneakyThrows
	@Override
	public void execute() {

		log.info( "roll execute" );
		TimeUnit.SECONDS.sleep( 2 );
		machine.changeState( "run" );
	}

	@Override
	public void exit() {
		log.info( "roll exit" );

	}
}
