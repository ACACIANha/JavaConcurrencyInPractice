package exercise.state.player;

import exercise.state.State;
import exercise.state.StateMachine;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class IdleState extends State< Player > {
	public IdleState( StateMachine< Player > machine ) {
		super( machine );
	}

	@Override
	public void enter() {
		log.info( "idle enter" );
	}

	@SneakyThrows
	@Override
	public void execute() {

		log.info( "idle execute" );
		TimeUnit.SECONDS.sleep( 2 );
		machine.changeState( "jump" );
	}

	@Override
	public void exit() {
		log.info( "idle exit" );
	}
}
