package netty.recorder;

import javax.swing.*;

public class RecorderServerForm {
	private JFrame jFrame;
	private JPanel panel;
	private JTextArea textBox;

	RecorderServerForm() {
		jFrame = new JFrame();
		jFrame.setLocation( 500, 400 );
		jFrame.setContentPane( panel );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.pack();
		jFrame.setVisible( true );
	}

	public void addText( String chat ) {
		String text = textBox.getText();
		text = text + chat + "\n";
		textBox.setText( text );
	}
}
