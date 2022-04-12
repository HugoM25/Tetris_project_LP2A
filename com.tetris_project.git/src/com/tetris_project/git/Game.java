package com.tetris_project.git;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game {

	public static void main(String[] args) {
		
		final GameSystem myGame = new GameSystem(); 
		final Interface in = new Interface(); 
		
		myGame.Update();
		
	    Timer timer = new Timer();
	    timer.schedule( new TimerTask() {
	        public void run() {
	        	
	        	in.RefreshPlayFrame(myGame.gridToDisplay());
	        	System.out.println("updated");
	        	myGame.Update();
	        }
	     }, 0, (long) (0.5f*1000));
	    
	}
}
