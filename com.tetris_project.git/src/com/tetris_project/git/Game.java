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
	        public void run() {
	        	
	        	in.RefreshPlayFrame(sysGame.gridToDisplay());
	        	
	        	//System.out.println("updated");
	        	sysGame.Update();
	        }
	     }, 0, (long) (0.5f*1000));
	    
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
			sysGame.getTetromino().Rotate(1);
		}
		in.RefreshPlayFrame(sysGame.gridToDisplay());
	}

	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
