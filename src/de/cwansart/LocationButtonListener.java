package de.cwansart;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class LocationButtonListener implements MouseListener {

	private JTextField textField;
	private JTextField urlTextField;

	public LocationButtonListener(JTextField urlTextField, JTextField saveTextField) {
		this.urlTextField = urlTextField;
		this.textField = saveTextField;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(urlTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to enter the url first!", "An error occured", JOptionPane.OK_OPTION);
			urlTextField.requestFocus();
			return;
		}
		
		String filename = new File(urlTextField.getText()).getName();
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogTitle("Save as");
		chooser.setSelectedFile(new File(filename));
		
		if(chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			textField.setText(chooser.getSelectedFile().toString());
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
