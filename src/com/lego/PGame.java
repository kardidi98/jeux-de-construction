package com.lego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

//Status of bricks => 0: free, 1: using, 2: used

public class PGame extends JPanel implements KeyListener, ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JFrame frame = new JFrame();
	GameOver isGameOver = new GameOver();
	Winner isWinner = new Winner();

	private boolean play = false;
	private int totalBricks;
	private Timer timer;
	private int delay = 10;
	private List<Integer> randomXPos = new ArrayList<>();
	private List<List<Integer>> bricks = new ArrayList<>();
	private int indexPlayBrick = -1;
	private int pbrickX;
	private int pbrickY;
	private int pbrickW;
	private int pbrickH;
	private boolean choose_lego = false;

	public PGame(int totalBricks) throws InterruptedException, IOException {

		addKeyListener(this);
		setFocusable(false);
		setFocusTraversalKeysEnabled(false);
		this.timer = new Timer(delay, this);
		this.timer.start();
		this.totalBricks = totalBricks;
		initialDisposition();
	}

	private void initialDisposition() throws InterruptedException {

		for (int i = 0; i < totalBricks; i++) {
			List<Integer> brick = new ArrayList<>();
			// brick representation is like: [status, x, y, width, height]
			brick.add(0);
			brick.add(randomX() * 50);
			brick.add(randomY() * 75);
			brick.add(50);
			brick.add(75);
			this.bricks.add(brick);
		}
		this.pbrickX = this.bricks.get(0).get(1);
		this.pbrickY = this.bricks.get(0).get(2);
		this.pbrickW = this.bricks.get(0).get(3);
		this.pbrickY = this.bricks.get(0).get(4);
	}

	private int randomX() {
		int randomPositionX;
		do {
			randomPositionX = 0 + (int) (Math.random() * ((9 - 0) + 1));
		} while (this.randomXPos.contains(randomPositionX));

		this.randomXPos.add(randomPositionX);
		return randomPositionX;
	}

	private int randomY() {
		return 0 + (int) (Math.random() * ((4 - 0) + 1));
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		Font font = new Font("Serif", Font.CENTER_BASELINE, 12);
		g2.setFont(font);

		// background
		g.setColor(Color.white);
		g.fillRect(1, 1, 692, 592);

		for (List<Integer> brick : this.bricks) {
			g.setColor(getColor(brick.get(0)));
			g.fillRect(brick.get(1), brick.get(2), brick.get(3), brick.get(4));
			int x = (int) brick.get(3) / 2 + brick.get(1);
			int y = (int) brick.get(4) / 2 + brick.get(2) + 5;

			g2.setColor(Color.black);
			g2.drawString("" + (this.bricks.indexOf(brick) + 1), x, y);

		}
		// Vertical Grids
		for (int i = 1; i <= 10; i++) {
			g.setColor(Color.gray);
			g.fillRect(i * 50, 0, 1, 400);
		}
		// Horizontal Grids
		for (int i = 1; i <= 5; i++) {
			g.setColor(Color.gray);
			g.fillRect(0, i * 75, 500, 1);
		}
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (choose_lego) {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				if (pbrickX >= 450) {
					pbrickX = 450;
				} else {
					moveRightOrLeft(50);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				if (pbrickX <= 0) {
					pbrickX = 0;
				} else {
					moveRightOrLeft(-50);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {

				if (pbrickY <= 0) {
					pbrickY = 0;
				} else {
					moveTopOrDown(-75);
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				if (pbrickY >= 300) {
					pbrickY = 300;
				} else {
					moveTopOrDown(75);
				}
			}
		} else
			JOptionPane.showMessageDialog(frame, "Cliquez à chaque fois sur le milieu d'une brique pour avancer.");

	}

	public void moveRightOrLeft(int step) {

		boolean existVoisinX = false;
		for (List<Integer> brick : this.bricks) {
			if (pbrickX + step == brick.get(1) && pbrickY == brick.get(2)) {
				existVoisinX = true;
				break;
			}
		}
		if (!existVoisinX) {
			this.play = true;
			pbrickX += step;
			bricks.get(this.indexPlayBrick).set(1, pbrickX);
		}
		if (this.isWinner.checkWinner(this.bricks, this.totalBricks)) {
			JOptionPane.showMessageDialog(frame, "Tu as réussi", "Félicitation", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} else if (this.isGameOver.checkGameOver(this.bricks)) {
			JOptionPane.showMessageDialog(frame, "GameOver", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

	public void moveTopOrDown(int step) {

		boolean existVoisinY = false;
		for (List<Integer> brick : this.bricks) {
			if (pbrickY + step == brick.get(2) && pbrickX == brick.get(1)) {
				existVoisinY = true;
				break;
			}
		}
		if (!existVoisinY) {
			this.play = true;
			pbrickY += step;
			bricks.get(this.indexPlayBrick).set(2, pbrickY);
		}
		if (this.isWinner.checkWinner(this.bricks, this.totalBricks)) {
			JOptionPane.showMessageDialog(frame, "Tu as réussi", "Félicitation", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		} else if (this.isGameOver.checkGameOver(this.bricks)) {
			JOptionPane.showMessageDialog(frame, "GameOver", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		this.choose_lego = true;
		if (this.indexPlayBrick == -1) {
			changeFisrtOrOldBrick(e.getX(), e.getY(), true);
		} else {
			changeFisrtOrOldBrick(e.getX(), e.getY(), false);
		}
		if (this.isWinner.checkWinner(this.bricks, this.totalBricks)) {
			JOptionPane.showMessageDialog(frame, "Tu as réussi", "Félicitation", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

		else if (this.isGameOver.checkGameOver(this.bricks)) {
			JOptionPane.showMessageDialog(frame, "GameOver", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}

	}

	public void changeFisrtOrOldBrick(int x, int y, boolean isFirst) {
		for (List<Integer> brick : this.bricks) {
			if ((x >= brick.get(1) && x <= brick.get(1) + brick.get(3))
					&& (y >= brick.get(2) && y <= brick.get(2) + brick.get(4))) {
				this.pbrickX = brick.get(1);
				this.pbrickY = brick.get(2);
				this.pbrickW = brick.get(3);
				this.pbrickH = brick.get(4);
				int color_id = nextColor(brick);
				brick.set(0, color_id);
				if (!isFirst) {
					List<Integer> oldBrick = this.bricks.get(this.indexPlayBrick);
					oldBrick.set(0, nextColor(oldBrick));
				}

				this.indexPlayBrick = bricks.indexOf(brick);
				break;
			}
		}
	}

	public int nextColor(List<Integer> brick) {
		if (brick.get(0) == 0)
			return 1;
		else if (brick.get(0) == 1)
			return 2;
		else if (brick.get(0) == 2)
			return 2;
		else
			return 0;
	}

	public Color getColor(int status) {
		if (status == 0) {
			return Color.green;
		} else if (status == 1) {
			return Color.yellow;
		}
		return Color.red;
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
