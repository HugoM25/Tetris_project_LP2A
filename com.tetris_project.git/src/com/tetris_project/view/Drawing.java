package com.tetris_project.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class Drawing extends JPanel {
	
	public int[][] gridDisplay;
	public static final Color[] COLOR_PALETTE = { Color.WHITE, Color.decode("#1ccad8"), Color.decode("#d6e681"), Color.decode("#f1a208"), 
												  Color.decode("#4b7f52") , Color.decode("#db5461"), Color.decode("#9381ff"), 
												  Color.decode("#246eb9")
	}; 
	private int DIMX;
	private int DIMY;

	
	private int size; 
	private int marginX;
	private int marginY;
	
	//Calculate ideal size base on the height of the panel 
	public void findGoodSize() {
		this.size =(int) this.getSize().height/20;
	}
	
	public void findMargins() {
		this.marginX = (int) ((this.getSize().width) - this.size*DIMX)/2;
		this.marginY = (int) ((this.getSize().height) - this.size*DIMY)/2;
	}
	
	public Drawing() {
		this.DIMX = 10;
		this.DIMY = 20;
		this.size = 10;
		this.marginX = 0;
		this.marginY = 0;
	}
	
	public int[][] getGridDisplay() {
		return gridDisplay;
	}


	public void setGridDisplay(int[][] gridDisplay) {
		this.gridDisplay = gridDisplay;
	}


	public void paint(Graphics g) {
		findGoodSize();
		findMargins();
	    Graphics2D graphic2d = (Graphics2D) g;
	    int[][] gridDisplay = this.gridDisplay; 
	    //Create black background
	    graphic2d.setColor(Color.decode("#0B132B"));
	    graphic2d.fillRect(0, 0, this.getSize().width, this.getSize().height);
	    
	    //Display grid if any
	    if (gridDisplay != null) {	    	
		    for (int i = 0; i < gridDisplay.length; i++) {
	        	for (int j = 0; j < gridDisplay[i].length; j++) {
	        		if (gridDisplay[i][j] >= 1 && gridDisplay[i][j] <= 7) {
	        			
	        			graphic2d.setColor(COLOR_PALETTE[gridDisplay[i][j]]);
        				graphic2d.fillRect(i*size + this.marginX, j*size + this.marginY, size, size);
        				
        				graphic2d.setColor(Color.BLACK);
                		graphic2d.drawRect(i*size + this.marginX, j*size + this.marginY, size, size);
	        		}
	        		else {
	        			graphic2d.setColor(Color.BLACK);
        				graphic2d.fillRect(i*size + this.marginX, j*size + this.marginY, size, size);
        				
        				graphic2d.setColor(Color.WHITE);
                		graphic2d.drawRect(i*size + this.marginX, j*size + this.marginY, size, size);
	        		}
	        		
	        	}
	        }
	    }
	    
	}
}