package com.tetris_project.git;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;

public class Drawing extends Canvas{
	public static Game myGame; 
	
	public static void main(String[] args) {
		
		myGame = new Game(); 
		
		for (int j = 0; j < 18; j++) {
			myGame.Update();
		}
		// TODO Auto-generated method stub
		 
		JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(400, 400);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
	}
	
	public void paint(Graphics g) {
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
