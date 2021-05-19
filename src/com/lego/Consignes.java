package com.lego;

import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Consignes extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int res;
	JFrame obj = new JFrame();
	PGame pgame;

	public Consignes(JFrame frame, int totalBricks) throws IOException {

		res = JOptionPane.showConfirmDialog(frame,
				"* Vous disposez de 5 pi�ces.\n" + "* Cliquer au milieux de chaque pi�ce pour avancer.\n"
						+ "* Vous devez reconstruire cette forme en utilisant les pi�ces"
						+ "\n et en respectant l'enchainement num�rique.\n"
						+ "* L'emplacement de la forme n'est pas important.\n"
						+ "* Les positions initiales des pi�ces sont al�atoires.\n"
						+ "* Le jeu se t�rmine s'il n'a plus de pi�ces libres (Vertes).\n",
				"Consignes", JOptionPane.OK_CANCEL_OPTION);
		if (res == JOptionPane.OK_OPTION) {
			try {
				pgame = new PGame(totalBricks);
				obj.setBounds(0, 0, 500, 400);
				obj.setTitle("Jeu de construction: LEGO");
				obj.setResizable(false);
				obj.setVisible(true);
				obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				obj.add(pgame);
				obj.addKeyListener(pgame);
				obj.addMouseListener(pgame);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

}
