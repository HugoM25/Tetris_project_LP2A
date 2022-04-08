package com.tetris_project.git;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {

	public static void main(String[] args) {
	
		JFrame frame = new JFrame("Tetris Game");
	    final Drawing canvas = new Drawing();
	    canvas.setSize(700, 700);
	    frame.add(canvas);
	    frame.pack();
	    frame.setVisible(true);
	   
	    final Graphics g = canvas.getGraphics();
		final GameSystem myGame = new GameSystem(); 
		myGame.Update();
		canvas.setGridDisplay(myGame.gridToDisplay());
		
	    Timer timer = new Timer();
	    timer.schedule( new TimerTask() {
	        public void run() {
	        	canvas.repaint();
	        	canvas.setGridDisplay(myGame.gridToDisplay());
	        	System.out.println("update");
	        	myGame.Update();
	        }
	     }, 0, 1*1000);
	}
}
