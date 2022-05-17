package exercise.state.player;

import exercise.state.StateMachine;


public class PlayerStateMachine extends StateMachine< Player > {

	public PlayerStateMachine( Player player ) {
		super( player );

		currentState = new IdleState( this );
		states.put( "idle", currentState );
		states.put( "jump", new JumpState( this ) );
		states.put( "roll", new RollState( this ) );
		states.put( "run", new RunState( this ) );
	}

}
