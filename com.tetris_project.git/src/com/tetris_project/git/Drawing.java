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

public class Drawing extends Canvas {
	
	public int[][] gridDisplay;
	public static final Color[] COLOR_PALETTE = { Color.WHITE, Color.GREEN, Color.BLUE, Color.RED, Color.PINK , Color.MAGENTA, Color.YELLOW, Color.CYAN}; 
	public Drawing() {

	}
	public int[][] getGridDisplay() {
		return gridDisplay;
	}


	public void setGridDisplay(int[][] gridDisplay) {
		this.gridDisplay = gridDisplay;
	}


	public void paint(Graphics g) {

	    Graphics2D graphic2d = (Graphics2D) g;
	    int[][] gridDisplay = this.gridDisplay; 

	    if (gridDisplay != null) {
	    
	    	int size = 30;
		    for (int i = 0; i < gridDisplay.length; i++) {
	        	for (int j = 0; j < gridDisplay[i].length; j++) {
	        		if (gridDisplay[i][j] >= 1 && gridDisplay[i][j] <= 7) {
	        			graphic2d.setColor(COLOR_PALETTE[gridDisplay[i][j]]);
        				graphic2d.fillRect(i*size, j*size, size, size);
	        		}
	        		else {
	        			graphic2d.setColor(Color.GRAY);
        				graphic2d.fillRect(i*size, j*size, size, size);
        				
        				graphic2d.setColor(Color.BLACK);
                		graphic2d.drawRect(i*size, j*size, size, size);
	        		}
	        		
	        	}
	        }
	    }
	    
	}


}
