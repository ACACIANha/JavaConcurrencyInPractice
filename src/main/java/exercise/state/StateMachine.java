package exercise.state;

import exercise.state.player.Player;

import java.util.HashMap;
import java.util.Map;

public class StateMachine< T > {

	protected final T owner;
	protected State< T > currentState;
	protected Map< String, State< T > > states;


	public StateMachine( T owner ) {
		this.owner = owner;
		states = new HashMap<>();
	}

	public void update() {

		currentState.execute();
	}

	public void changeState( State< T > state ) {

		currentState.exit();
		currentState = state;
		currentState.enter();
	}

	public void changeState( String state ) {
		State< T > tState = states.get( state );
		changeState( tState );
	}
}
