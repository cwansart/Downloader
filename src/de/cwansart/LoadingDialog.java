package de.cwansart;

import java.awt.Dialog;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class LoadingDialog extends JDialog {
	private static final long serialVersionUID = 1138442902958974678L;
	private Thread downloadThread;

	public LoadingDialog(MainWindow parent) {
		super(parent);
		URL loadingUrl = Main.class.getResource("/loading40.gif");
		ImageIcon loadingImage = new ImageIcon(loadingUrl);
		System.out.println(loadingUrl.toString());
		JLabel loadingLabel = new JLabel(loadingImage);
		this.add(loadingLabel);

		// this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		final LoadingDialog THIS = this;
		this.addWindowListener(new WindowListener() {
			@SuppressWarnings("deprecation")
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to stop the download process?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					if (THIS.downloadThread != null) {
						THIS.downloadThread.stop();
					}
					THIS.getParent().setVisible(true);
					THIS.dispose();
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowOpened(WindowEvent e) {
			}
		});

		this.pack();
		this.setLocationRelativeTo(null);
	}

	public void startDownload(URL source, File destination) {
		// I need those to pass them to the Runnable
		final URL SOURCE = source;
		final File DEST = destination;
		final LoadingDialog THIS = this;

//		this.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
		this.getParent().setVisible(false);
		this.setVisible(true);

		downloadThread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.err.println("Thread started");
				try {
					FileUtils.copyURLToFile(SOURCE, DEST);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					e.printStackTrace();
				} finally {
					THIS.getParent().setVisible(true);
					THIS.dispose();
				}
			}
		});

		downloadThread.start();
	}
}
