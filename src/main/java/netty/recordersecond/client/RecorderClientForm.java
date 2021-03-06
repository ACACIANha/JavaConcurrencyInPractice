package netty.recordersecond.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class RecorderClientForm {

	private JFrame frame;
	private JPanel panel;
	private JTextField statusField;
	private JTextField inputField;
	private JButton connectButton;
	private JButton disconnectButton;
	private JButton sendButton;

	public RecorderClientForm() {
		frame = new JFrame();
		frame.setLocation( 500, 400 );
		frame.setContentPane( panel );
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		frame.pack();
		frame.setVisible( true );
		this.setStatus( false );
	}

	public void setStatus( boolean status ) {
		String s = status ? "Connected" : "Disconnected";
		statusField.setText( s );
	}

	public String getInputText() {
		return inputField.getText();
	}

	public void exit() {
		frame.dispatchEvent( new WindowEvent( frame, WindowEvent.WINDOW_CLOSING ) );
	}

	public void setConnectButtonListener( ActionListener actionListener ) {
		connectButton.addActionListener( actionListener );
	}

	public void setDisconnectButtonListener( ActionListener actionListener ) {
		disconnectButton.addActionListener( actionListener );
	}

	{
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
		$$$setupUI$$$();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		panel = new JPanel();
		panel.setLayout( new BorderLayout( 0, 0 ) );
		final JPanel panel1 = new JPanel();
		panel1.setLayout( new com.intellij.uiDesigner.core.GridLayoutManager( 1, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
		panel.add( panel1, BorderLayout.WEST );
		final JPanel panel2 = new JPanel();
		panel2.setLayout( new com.intellij.uiDesigner.core.GridLayoutManager( 1, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
		panel.add( panel2, BorderLayout.EAST );
		final JPanel panel3 = new JPanel();
		panel3.setLayout( new com.intellij.uiDesigner.core.GridLayoutManager( 1, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
		panel.add( panel3, BorderLayout.NORTH );
		final JPanel panel4 = new JPanel();
		panel4.setLayout( new com.intellij.uiDesigner.core.GridLayoutManager( 1, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
		panel.add( panel4, BorderLayout.SOUTH );
		final JPanel panel5 = new JPanel();
		panel5.setLayout( new com.intellij.uiDesigner.core.GridLayoutManager( 5, 1, new Insets( 0, 0, 0, 0 ), -1, -1 ) );
		panel.add( panel5, BorderLayout.CENTER );
		statusField = new JTextField();
		statusField.setEditable( false );
		panel5.add( statusField, new com.intellij.uiDesigner.core.GridConstraints( 0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension( 150, -1 ), null, 0, false ) );
		connectButton = new JButton();
		connectButton.setText( "connect" );
		panel5.add( connectButton, new com.intellij.uiDesigner.core.GridConstraints( 2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false ) );
		disconnectButton = new JButton();
		disconnectButton.setText( "disconnect" );
		panel5.add( disconnectButton, new com.intellij.uiDesigner.core.GridConstraints( 3, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false ) );
		inputField = new JTextField();
		panel5.add( inputField, new com.intellij.uiDesigner.core.GridConstraints( 1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension( 150, -1 ), null, 0, false ) );
		sendButton = new JButton();
		sendButton.setText( "send" );
		panel5.add( sendButton, new com.intellij.uiDesigner.core.GridConstraints( 4, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false ) );
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return panel;
	}
}
