package com.tetris_project.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import tetrominoes.Tetromino;

public class DispTetroPanel extends JPanel {
	

	private Tetromino tetromino;
	private int DIM_MAX; 
	private int size; 
	//Array containing colors used to draw the tetrominos
	private static final Color[] COLOR_PALETTE = { Color.WHITE, Color.decode("#1ccad8"), Color.decode("#d6e681"), Color.decode("#f1a208"), 
			  Color.decode("#4b7f52") , Color.decode("#db5461"), Color.decode("#9381ff"), 
			  Color.decode("#246eb9")
			}; 
	
	private int marginX; 
	private int marginY;
	public DispTetroPanel() {
		this.DIM_MAX = 4;
	}
	public void findMargins() {
		this.marginX = (int) ((this.getSize().width) - this.size*DIM_MAX)/2;
		this.marginY = (int) ((this.getSize().height) - this.size*DIM_MAX)/2;
	}
	
	public Tetromino getTetromino() {
		return tetromino;
	}


	public void setTetromino(Tetromino tetromino) {
		this.tetromino = tetromino;
	}
	public void findGoodSize() {
		this.size =(int) this.getSize().height/DIM_MAX;
	}


	public void paint(Graphics g) {
		
	
	    Graphics2D graphic2d = (Graphics2D) g; 
	    //Create black background
	    graphic2d.setColor(Color.decode("#0B132B"));
	    graphic2d.fillRect(0, 0, this.getSize().width, this.getSize().height);
	    
	    //Display tetro if any
	    if (this.tetromino != null) {
	    	findGoodSize();
	    	findMargins();
	    	
	    	 
	    	for(int i = 0; i < DIM_MAX; i++) {
	    		for (int j = 0; j < DIM_MAX; j++) {
	    			if ( i < this.tetromino.arrayPiece[0].length && j < this.tetromino.arrayPiece[0][0].length) {
	    				if (this.tetromino.arrayPiece[0][i][j] >= 1 && this.tetromino.arrayPiece[0][i][j] <= 7) {
		        			graphic2d.setColor(COLOR_PALETTE[this.tetromino.arrayPiece[0][i][j]]);
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
