package exercise.state.player;

import exercise.state.State;
import exercise.state.StateMachine;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class JumpState extends State< Player > {
	public JumpState( StateMachine< Player > machine ) {
		super( machine );
	}

	@Override
	public void enter() {
		log.info( "jump enter" );
	}

	@SneakyThrows
	@Override
	public void execute() {

		log.info( "jump execute" );
		TimeUnit.SECONDS.sleep( 2 );
		machine.changeState( "roll" );
	}

	@Override
	public void exit() {
		log.info( "jump exit" );
	}
}
