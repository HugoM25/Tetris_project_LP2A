package com.tetris_project.git;

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
		if (this.isPiecePosOk(tetromino, new Vector2D(0,0))) {
			for (int j = 0; j < tetromino.arrayPiece[tetromino.orientation].length; j++) {
				for (int k = 0; k < tetromino.arrayPiece[tetromino.orientation][j].length; k++) {
					if (tetromino.arrayPiece[tetromino.orientation][j][k] != 0) {
						this.tab[j+tetromino.position.getX()][k+tetromino.position.getY()] = tetromino.arrayPiece[tetromino.orientation][j][k]; 
					}
				}
			}
		}
	}
	
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
					//If it is a used piece square 
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
		//Remove line blocks
		for( int k = 0; k < this.tab[lineIndex].length; k++ ) {
			this.tab[lineIndex][k] = 0; 
		}
		//Make the other lines fall on this one
		
		//Copy grid
		int[][] gridTmp = new int[this.tab.length][this.tab[0].length];
		for (int i = 0; i < gridTmp.length; i++) {
			System.arraycopy(this.tab[i], 0, gridTmp[i], 0, this.tab[i].length);
		}
		//Modify grid
		for (int i = 0; i < lineIndex; i++) {
			for (int j = 0; j < this.tab[lineIndex].length; j++) {
				
			}
		}
		
		//Update grid
		this.tab = gridTmp;
	}
	public List<Integer> checkForLineToRemove() {
		int j = 0;
		List<Integer> indexesToBeRemoved = new ArrayList<Integer>();  
		//Check for every line if it has to be removed

		//Need to fix the way we know the order of the grid
		for(int i = 0; i < this.tab[0].length; i++) {
			 
			while (j < this.tab.length && this.tab[j][i] != 0) {
				j++;
			}
			
			if (j >= this.tab.length) {
				indexesToBeRemoved.add(i); 
			}
		}
		
		//Debug
		System.out.println(indexesToBeRemoved); 
		
		return indexesToBeRemoved ;
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
