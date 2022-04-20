package com.tetris_project.git;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Game implements KeyListener {
	
	private static Interface in; 
	private static GameSystem sysGame;
	private static int FPS = 60; 
	
	public Game() {
		in = new Interface(); 
		sysGame = new GameSystem();
		in.canvas.addKeyListener(this);

	}

	public static void main(String[] args) {
		
		Game myGame = new Game(); 
		
		sysGame.Update();
	    
	    Timer timer = new Timer();
	    
	    timer.schedule( new TimerTask() {
	    	int counter =0; 
	    	int counterMax = 60; 
	    	
	        public void run() {
	        	
	        	in.RefreshPlayFrame(sysGame.showGrid());
	        	
	        	//Update game automatically every 60 frames --> 1 second 
	        	if (counter > counterMax ) {
	        		counter = 0;
	        		sysGame.Update();
	        	}
	        	else {
	        		counter++; 
	        	}
	        }
	     }, 0, (long) (1000/FPS));
	    
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		    sysGame.moveTetroRight();
	
		}
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		    sysGame.moveTetroLeft();
		   
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			sysGame.Update();
			
		}
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			sysGame.rotateTetroLeft();
		}

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
