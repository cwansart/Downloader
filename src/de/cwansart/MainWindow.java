package de.cwansart;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = -3880026026104218593L;
	private JTextField urlTextField;
	private JTextField saveTextField;

	public MainWindow(String title) {
		super(title);

		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		// this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
		this.setLayout(layout);

		/* URL INPUT */
		String clipboard = null;
		try {
			clipboard = (String) Toolkit.getDefaultToolkit().getSystemClipboard()
					.getData(DataFlavor.stringFlavor);
		} catch (HeadlessException | UnsupportedFlavorException | IOException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			e.printStackTrace();
		}

		JPanel urlPanel = new JPanel();
		urlPanel.setLayout(new BoxLayout(urlPanel, BoxLayout.LINE_AXIS));
		JLabel urlLabel = new JLabel("URL");
		this.urlTextField = new JTextField(20);
		
		if(clipboard != null) {
			this.urlTextField.setText(clipboard);
		}
		
		urlPanel.add(urlLabel);
		urlPanel.add(Box.createHorizontalStrut(27));
		urlPanel.add(urlTextField);
		this.add(urlPanel);
		this.add(Box.createVerticalStrut(5));

		/* DOWNLOAD FOLDER */
		JPanel savePanel = new JPanel();
		savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.LINE_AXIS));
		JLabel saveLabel = new JLabel("Save as");
		this.saveTextField = new JTextField(20);
		JButton selectLocationButton = new JButton("Select location");
		savePanel.add(saveLabel);
		savePanel.add(Box.createHorizontalStrut(5));
		savePanel.add(saveTextField);
		savePanel.add(Box.createHorizontalStrut(5));
		savePanel.add(selectLocationButton);
		this.add(savePanel);
		this.add(Box.createVerticalStrut(5));

		selectLocationButton.addMouseListener(new LocationButtonListener(this.urlTextField, this.saveTextField));

		/* DOWNLOAD BUTTON */
		JPanel downloadPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton downloadButton = new JButton("Start download");
		downloadPanel.add(downloadButton);
		this.add(downloadPanel);

		downloadButton.addMouseListener(new DownloadButtonListener(this.urlTextField, this.saveTextField));

		this.pack();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// this.setSize(430, 500);

	}

}
