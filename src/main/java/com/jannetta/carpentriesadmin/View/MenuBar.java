package com.jannetta.carpentriesadmin.View;

import com.google.gson.Gson;
import com.jannetta.carpentriesadmin.controller.Globals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MenuBar extends JMenuBar implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger logger = LoggerFactory.getLogger(getClass());
	Globals globals = Globals.getInstance();
	MainFrame mf;

	public MenuBar(MainFrame mf) {
		this.mf = mf;
		JMenu file;
		JMenu help;
		JMenuItem newfile;
		JMenuItem openFile;
		JMenuItem saveFile;
		JMenuItem saveAsFile;
		JMenuItem exit;
		JMenuItem about;

		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		
		// File menu items
		newfile = new JMenuItem("New", KeyEvent.VK_N);
		newfile.addActionListener(this);
		openFile = new JMenuItem("Open", KeyEvent.VK_O);
		openFile.addActionListener(this);
		saveFile = new JMenuItem("Save", KeyEvent.VK_S);
		saveFile.addActionListener(this);
		saveAsFile = new JMenuItem("Save As", KeyEvent.VK_A);
		saveAsFile.addActionListener(this);
		exit = new JMenuItem("Exit", KeyEvent.VK_X);
		exit.addActionListener(this);
		help = new JMenu("Help");
		help.setMnemonic('H');

		// Help menu items
		about = new JMenuItem("About", 'A');
		about.addActionListener(this);

		// Add menu items to file
		file.add(newfile);
		file.add(openFile);
		file.add(saveFile);
		file.add(saveAsFile);
		file.add(exit);

		// Add menu items to help
		help.add(about);
		
		this.add(file);
		this.add(help);
	}

	private ImageIcon createImageIcon(String path, String description) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try {
			Image icon = toolkit.getImage(ClassLoader.getSystemResource("CarpentriesAdminLogo.png"));
			return new ImageIcon(icon);
		} catch (NullPointerException e) {
			logger.error("Logo.png not found.");
			return null;
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("About")) {
			ImageIcon icon = Globals.createImageIcon("CarpentriesAdminLogo.png", "Carpentries Admin Logo");
			JOptionPane.showMessageDialog(this,
					"Manage your Carpentries Workshops\n"
							+ "Copyright: Jannetta S Steyn, 2020",
					"About Certify", JOptionPane.PLAIN_MESSAGE, icon);
		}
		if (e.getActionCommand().equals("Exit")) {
			mf.closer();
			System.exit(0);
		}

	}

}
