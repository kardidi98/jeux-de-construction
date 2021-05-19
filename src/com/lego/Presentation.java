package com.lego;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class Presentation {

	public static void main(String[] args) throws InterruptedException, IOException {
		JFrame obj = new JFrame();
		PGame pgame = new PGame();
		
		obj.setBounds(0, 0, 500, 400);
		obj.setTitle("Jeu de construction: LEGO");
		obj.setResizable(false);
		obj.setVisible(true);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.add(pgame);
		obj.addKeyListener(pgame);
		obj.addMouseListener(pgame);
		
	}

}
