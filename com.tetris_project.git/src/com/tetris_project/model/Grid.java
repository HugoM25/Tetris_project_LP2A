package com.tetris_project.model;

import java.util.ArrayList;
import java.util.List;

import tetrominoes.Tetromino;

public class Grid {
	private int height;
	private int width;
	private int[][] tab;
	
	public Grid(int height, int width) {
		this.height = height; 
		this.width = width; 
		this.tab = new int[height][width]; 
	}
	public Grid() {
		this.height = 20; 
		this.width = 10; 
		this.tab = new int [10][20];
	}
	
	//Function only used to debug the program 
	public void displayGridValue() {
		for (int i = 0; i < height; i++) {
			String str = ""; 
			for (int j = 0; j < width; j++) {
				str += this.tab[j][i]; 
			}
			System.out.println(str); 
		}
	}
	
	public boolean isPiecePosOk(Tetromino tetromino, Vector2D deplacement) {
		
		//For every square of the piece
		for (int i = 0; i < tetromino.arrayPiece[tetromino.orientation].length; i++) {
			for (int j = 0; j <  tetromino.arrayPiece[tetromino.orientation][i].length; j++) {
				//Check if non-empty square are inside and are free to place
				if (tetromino.arrayPiece[tetromino.orientation][i][j] != 0) {
					//If squares are outside the grid array then the piece can't be placed
					if (tetromino.position.getX()+deplacement.getX()+i < 0 || tetromino.position.getX()+deplacement.getX()+i >= this.width) {
						return false;
					}
					else if (tetromino.position.getY()+deplacement.getY()+j < 0 || tetromino.position.getY()+deplacement.getY()+j >= this.height) {
						return false; 
					}
					else if (this.tab[tetromino.position.getX()+i+deplacement.getX()][tetromino.position.getY()+j+deplacement.getY()] != 0 ) {
						return false; 
					}
				}
			}
		}
		return true; 
	}
	
	public void attachTetrominoToGrid(Tetromino tetromino) {
		
		for (int j = 0; j < tetromino.arrayPiece[tetromino.orientation].length; j++) {
			
			for (int k = 0; k < tetromino.arrayPiece[tetromino.orientation][j].length; k++) {
				
				if (tetromino.arrayPiece[tetromino.orientation][j][k] != 0 ) {
					//Attach the block if it is in the grid
					if (j+tetromino.position.getX()>= 0 && k+tetromino.position.getY() >=0 && this.tab[j+tetromino.position.getX()][k+tetromino.position.getY()] == 0) {
						
						this.tab[j+tetromino.position.getX()][k+tetromino.position.getY()] = tetromino.arrayPiece[tetromino.orientation][j][k]; 
					}
				}
			}
		}
	}
	//This function is used to give the view part a grid to render (where the actual tetromino is in place)
	public int[][] gridToDisplay(Tetromino tetromino){
		//Copy grid to a new grid only for display
		int[][] gridDisplay = new int[this.tab.length][this.tab[0].length];
		for (int i = 0; i < gridDisplay.length; i++) {
			System.arraycopy(this.tab[i], 0, gridDisplay[i], 0, this.tab[i].length);
		}
		//Place the manipulated tetromino in the grid
		if (tetromino != null ) {
			for (int j = 0; j < tetromino.arrayPiece[tetromino.orientation].length; j++) {
				for (int k = 0; k < tetromino.arrayPiece[tetromino.orientation][j].length; k++) {
					//If it is a used piece square place it
					if (tetromino.arrayPiece[tetromino.orientation][j][k] != 0) {
						if (k+tetromino.position.getY()>=0 && k+tetromino.position.getY()<gridDisplay[j].length) {
							gridDisplay[j+tetromino.position.getX()][k+tetromino.position.getY()] = tetromino.colorIndex; 
						}
						
					}
				}
			}
		}
		return gridDisplay; 
	}
	public void removeLineFromGrid(int lineIndex) {
		
		//Remove lines and make the other lines fall on it
		for (int j = lineIndex; j > 0; j--) {
			for(int i = 0; i < this.width; i++) {
				this.tab[i][j] = this.tab[i][j-1];
			}
		}
	}
	public List<Integer> checkForLineToRemove() {
		int i = 0;
		List<Integer> indexesToBeRemoved = new ArrayList<Integer>();  
		//Check for every line if it has to be removed
		for(int j = 0; j < this.height; j++) {
			i = 0; 
			while (i < this.width && this.tab[i][j] != 0) {
				i++;
			}
			//If every square was a block square then add the line to the array
			if (i >= this.width) {
				indexesToBeRemoved.add(j); 
			}
		}
		
		return indexesToBeRemoved ;
	}
	//Set every square to empty value (0)
	public void resetGrid() {
		for(int i = 0; i < this.width ; i++) {
			for(int j = 0; j < this.height; j++) {
				this.tab[i][j] = 0; 
			}
		}
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	} 
	
}
