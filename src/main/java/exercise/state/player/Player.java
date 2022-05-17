package exercise.state.player;

public class Player {

	private final PlayerStateMachine stateMachine;

	public Player() {
		this.stateMachine = new PlayerStateMachine( this );
	}

	public void update() {
		stateMachine.update();
	}
}
