package com.tetris_project.git;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class Drawing extends Canvas{
	
	public static Game myGame; 
	
	public void paint(Graphics g) {
		
		myGame = new Game(); 
		
		for (int j = 0; j < 18; j++) {
			myGame.Update();
		}
	    
	    
	    Graphics2D graphic2d = (Graphics2D) g;
	    int[][] gridDisplay = myGame.gridToDisplay(); 
	    int size = 30; 
	    for (int i = 0; i < gridDisplay.length; i++) {
        	for (int j = 0; j < gridDisplay[i].length; j++) {
        	
        		switch(gridDisplay[i][j]) {
        			case 0:
        				graphic2d.setColor(Color.GRAY);
        				graphic2d.fillRect(i*size, j*size, size, size);
        				
        				graphic2d.setColor(Color.BLACK);
                		graphic2d.drawRect(i*size, j*size, size, size);
        				break; 
        			case 1:
        				graphic2d.setColor(Color.ORANGE);
        				graphic2d.fillRect(i*size, j*size, size, size);
        				break; 
        			default :
        				break; 
        		}
        		

        	}
        }
	}
}
