package de.cwansart;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DownloadButtonListener implements MouseListener {

	private JTextField urlTextField;
	private JTextField saveTextField;

	public DownloadButtonListener(JTextField urlTextField, JTextField saveTextField) {
		this.urlTextField = urlTextField;
		this.saveTextField = saveTextField;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(urlTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to enter the url first!", "An error occured", JOptionPane.OK_OPTION);
			urlTextField.requestFocus();
			return;
		}
		
		if(saveTextField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to enter a save location first!", "An error occured", JOptionPane.OK_OPTION);
			saveTextField.requestFocus();
			return;
		}
		
		try {
			URL source = new URL(urlTextField.getText());
			File destination = new File(saveTextField.getText());
			
			LoadingDialog loadingDialog = new LoadingDialog();
			loadingDialog.startDownload(source, destination);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
