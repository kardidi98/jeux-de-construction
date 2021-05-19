package com.lego;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Model extends JPanel implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame frame;

	private Consignes consignes;
	private ImageIcon icon;

	private JButton model1;
	private JButton model2;

	private Object choosenModel;

	public Model(JFrame frame) throws IOException {

		this.frame = frame;
		this.frame.setLayout(new GridLayout(1, 2));
		this.frame.setTitle("Choisissez un modèle");

		// Fisrt model
		this.icon = new ImageIcon("src/images/task1.png");
		this.model1 = new JButton(icon);
		this.model1.addActionListener(this);
		this.model1.setSize(10, 250);
		// Second model
		this.icon = new ImageIcon("src/images/task2.png");
		this.model2 = new JButton(icon);
		this.model2.addActionListener(this);
		this.model1.setSize(10, 250);

		this.frame.add(model1);
		this.frame.add(model2);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationByPlatform(true);
		this.frame.setResizable(false);
		this.frame.setSize(600, 500);
		this.frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.choosenModel = e.getSource();
		if (this.choosenModel == this.model1) {
			try {
				this.frame.setVisible(false);
				this.consignes = new Consignes(new JFrame(), 4);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}
		if (this.choosenModel == this.model2) {
			try {
				this.frame.setVisible(false);
				this.consignes = new Consignes(new JFrame(), 5);

			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
