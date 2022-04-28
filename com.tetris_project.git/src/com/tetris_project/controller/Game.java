package com.tetris_project.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import com.tetris_project.model.GameState;
import com.tetris_project.model.GameSystem;
import com.tetris_project.view.GameGUI;


public class Game implements KeyListener {
	
	private static GameGUI guiGame; 
	private static GameSystem sysGame;
	
	private static int FPS = 60;  
	
	public Game() {
		guiGame = new GameGUI(); 
		sysGame = new GameSystem();
		guiGame.canvas.addKeyListener(this);
	}

	public static void main(String[] args) {
		
		Game myGame = new Game(); 
	    
		//Use timer to create the main looping thread
	    Timer timer = new Timer();
	    timer.schedule( new TimerTask() {
	    	int counter =0;    	
	        public void run() {
	        	
	        	//Refresh UI
	        	guiGame.RefreshPlayFrame(sysGame.showGrid());
	        	guiGame.DisplayNextTetrominoes(sysGame.getNextTetrominoes());
	        	guiGame.setScoreText("Score : " + sysGame.getScore());
	        	guiGame.setTimerText("TIME : " + sysGame.getTimer());
	        	guiGame.setStateText("Difficulty : " + guiGame.getDiff());
	        	guiGame.setHighscoreText("Highscore : "+sysGame.getHighscore());
	        	guiGame.setLinesClearedText("LINES CLEARED : " + sysGame.getLinesClearedCount());
	        	
	        	//Update game automatically every N frames
	        	if (sysGame.state == GameState.PLAY) {
	        		if (counter > sysGame.getNbFramesBetweenUpdates() ) {
	        			counter = 0;
	        			sysGame.Update();
	        		}
	        		else {
	        			counter++; 
	        		}
	        		//add the elapsed time to the timer
	        		sysGame.getTimer().updateTimer(1000/FPS);
	        	}
	        }
	     }, 0, (long) (1000/FPS));
	    //Make the game waits 1000/FPS milliseconds between every loop
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
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					sysGame.rotateTetroLeft();
				}
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					sysGame.hardDrop();
				}
				else if (e.getKeyCode() == KeyEvent.VK_P) {
					sysGame.state = GameState.PAUSE;
				}
				break;
			case NOT_STARTED :
				if (e.getKeyCode() == KeyEvent.VK_S) {
					sysGame.setDifficulty(guiGame.getDiff());
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
		if (e.getKeyCode() == KeyEvent.VK_R) {
			sysGame.Restart();
		}
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}


}
