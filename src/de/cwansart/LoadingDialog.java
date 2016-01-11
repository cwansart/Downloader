package de.cwansart;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import org.apache.commons.io.FileUtils;

public class LoadingDialog extends JDialog {	
	private static final long serialVersionUID = 1138442902958974678L;

	public LoadingDialog() {		
		ImageIcon loadingImage = new ImageIcon("images/loading40.gif");
		JLabel loadingLabel = new JLabel(loadingImage);
		this.add(loadingLabel);
		
//		this.setModal(true);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void startDownload(URL source, File destination) {
		try {
			FileUtils.copyURLToFile(source, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
