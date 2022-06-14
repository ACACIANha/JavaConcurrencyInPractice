package netty.recorder;

import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

@Slf4j
public class RecorderClientForm {

	private JFrame jFrame;
	private JPanel panel;
	private JTextField textField;
	private JButton sendButton;

	RecorderClientForm() {
		jFrame = new JFrame();
		jFrame.setLocation( 500, 400 );
		jFrame.setContentPane( panel );
		jFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		jFrame.pack();
		jFrame.setVisible( true );

	}

	public void setButtonAddListener( ActionListener actionListener ) {
		sendButton.addActionListener( actionListener );
	}

	public String getText() {
		return textField.getText();
	}

	public void exit() {
		jFrame.dispatchEvent( new WindowEvent( jFrame, WindowEvent.WINDOW_CLOSING ) );
	}

}
