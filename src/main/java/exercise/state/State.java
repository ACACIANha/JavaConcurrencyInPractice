package exercise.state;

public abstract class State< T > {

	protected final StateMachine< T > machine;

	protected State( StateMachine< T > machine ) {
		this.machine = machine;
	}

	public abstract void enter();

	public abstract void execute();

	public abstract void exit();

}
