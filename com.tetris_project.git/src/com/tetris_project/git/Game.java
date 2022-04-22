package com.tetris_project.git;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;


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
	    
	    Timer timer = new Timer();
	    
	    timer.schedule( new TimerTask() {
	    	int counter =0; 
	    	int counterMax = 60; 
	    	
	        public void run() {
	        	
	        	in.RefreshPlayFrame(sysGame.showGrid());
	        	
	        	//Update game automatically every 60 frames --> 1 second 
	        	if (sysGame.state == GameState.PLAY) {
	        		if (counter > counterMax ) {
	        			counter = 0;
	        			sysGame.Update();
	        		}
	        		else {
	        			counter++; 
	        		}
	        	}
	        }
	     }, 0, (long) (1000/FPS));
	    
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent e) {
		//Handle inputs (it varies with the game's state) 
		switch (sysGame.state) {
			case PLAY :
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
				else if (e.getKeyCode() == KeyEvent.VK_P) {
					sysGame.state = GameState.PAUSE;
				}
			case NOT_STARTED :
				if (e.getKeyCode() == KeyEvent.VK_S) {
					sysGame.Start();
				}
				break; 
			case PAUSE :
				if (e.getKeyCode() == KeyEvent.VK_P) {
					sysGame.state = GameState.PLAY;
				}
				break;
			case END :
				break;
			default :
				break;
		}
		// TODO Auto-generated method stub
		

	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
