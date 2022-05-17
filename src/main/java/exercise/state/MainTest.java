package exercise.state;

import exercise.state.player.Player;

public class MainTest {

	public static void main( String[] args ) {

		Player player = new Player();

		while ( true ) {
			player.update();
		}

	}
}
