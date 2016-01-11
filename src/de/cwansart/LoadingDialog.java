package de.cwansart;

import java.awt.AWTError;
import java.awt.Dialog;
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
		ImageIcon loadingImage = new ImageIcon(Main.class.getResource("/loading40.gif"));
		JLabel loadingLabel = new JLabel(loadingImage);
		this.add(loadingLabel);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void startDownload(URL source, File destination) {
		try {
			this.setVisible(true);
			this.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
			System.out.println("möp");
			FileUtils.copyURLToFile(source, destination);
			System.out.println("blub");
			this.dispose();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
