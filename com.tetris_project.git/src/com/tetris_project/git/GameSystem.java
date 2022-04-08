package com.tetris_project.git;


import java.util.ArrayList;
import java.util.List;

import tetrominoes.IStyle;
import tetrominoes.JStyle;
import tetrominoes.SStyle;
import tetrominoes.TStyle;
import tetrominoes.Tetromino;

public class GameSystem {
	
	//Data 
	
	//Used for the game
	private int[][] grid; 
	private Tetromino tetromino; 
	
	
	public Tetromino getTetromino() {
		return tetromino;
	}
	public void setTetromino(Tetromino tetromino) {
		this.tetromino = tetromino;
	}
	
	
	public GameSystem(int[][] grid, Tetromino tetromino) {
		this.grid = grid;
		this.tetromino = tetromino; 
	}
	public GameSystem(int x, int y, Tetromino tetromino) {
		this.grid = new int[x][y]; 
		this.tetromino = tetromino; 
	}
	public GameSystem() {
		this.grid = new int[10][20];
		this.tetromino = new TStyle(); 
	}

	public int[][] getGrid() {
		return grid;
	}

	public void setGrid(int[][] grid) {
		this.grid = grid;
	}
	public void spawnNewTetromino() {
		
	}
	public boolean isPiecePosOk(Tetromino tetromino, Vector2D deplacement) {
		
		//For every square of the piece
		for (int i = 0; i < tetromino.arrayPiece[tetromino.orientation].length; i++) {
			for (int j = 0; j <  tetromino.arrayPiece[tetromino.orientation][i].length; j++) {
				//Check if non-empty square are inside and are free to place
				if (tetromino.arrayPiece[tetromino.orientation][i][j] != 0) {
					
					if (tetromino.position.getX()+deplacement.getX()+i < 0 || tetromino.position.getX()+deplacement.getX()+i >= grid.length) {
						return false;
					}
					else if (tetromino.position.getY()+deplacement.getY()+j < 0 || tetromino.position.getY()+deplacement.getY()+j >= grid[0].length) {
						return false; 
					}
					else if (this.grid[tetromino.position.getX()+i+deplacement.getX()][tetromino.position.getY()+j+deplacement.getY()] != 0 ) {
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
						this.grid[j+tetromino.position.getX()][k+tetromino.position.getY()] = tetromino.arrayPiece[tetromino.orientation][j][k]; 
					}
				}
			}

		}
		
	}
	
	public int[][] gridToDisplay(){
		//Copy grid to a new grid only for display
		int[][] gridDisplay = new int[this.grid.length][this.grid[0].length];
		for (int i = 0; i < gridDisplay.length; i++) {
			System.arraycopy(this.grid[i], 0, gridDisplay[i], 0, this.grid[i].length);
		}
		//Place the manipulated tetromino in the grid
		if (this.tetromino != null ) {
			
			for (int j = 0; j < this.tetromino.arrayPiece[this.tetromino.orientation].length; j++) {
				for (int k = 0; k < this.tetromino.arrayPiece[this.tetromino.orientation][j].length; k++) {
					//If it is a used piece square 
					if (this.tetromino.arrayPiece[this.tetromino.orientation][j][k] != 0) {
						if (k+this.tetromino.position.getY()>=0 && k+this.tetromino.position.getY()<gridDisplay.length) {
							gridDisplay[j+this.tetromino.position.getX()][k+this.tetromino.position.getY()] = 1; 
						}
						
					}
				}
			}
		}
		return gridDisplay; 
	}
	public List<Integer> checkForLineToRemove() {
		int j = 0;
		List<Integer> indexesToBeRemoved = new ArrayList<Integer>();  
		//Check for every line if it has to be removed
		for(int i = 0; i < this.grid.length; i++) {
			while (j < this.grid[i].length && j != 0) {
				j++;
			}
			if (j >= this.grid[i].length) {
				indexesToBeRemoved.add(i); 
			}
		}
		return indexesToBeRemoved ;
	}
	public void removeLineFromGrid(int lineIndex) {
		//Remove line blocks
		for( int k = 0; k < this.grid[lineIndex].length; k++ ) {
			this.grid[lineIndex][k] = 0; 
		}
		//Make the other lines fall on this one
		
		//Copy grid
		int[][] gridTmp = new int[this.grid.length][this.grid[0].length];
		for (int i = 0; i < gridTmp.length; i++) {
			System.arraycopy(this.grid[i], 0, gridTmp[i], 0, this.grid[i].length);
		}
		//Modify grid
		for (int i = 0; i < lineIndex; i++) {
			for (int j = 0; j < this.grid[lineIndex].length; j++) {
				
			}
		}
		
		//Update grid
		this.grid = gridTmp; 
		
	}
	
	public void displayGridValue() {
		for (int i = 0; i < this.grid .length; i++) {
			String str = ""; 
			for (int j = 0; j < this.grid[i].length; j++) {
				str = str + grid[i][j];
			}
			System.out.println(str);
		}
	}
	
	public void Update() {
		
		//Handle piece movement
		if (this.tetromino != null) {		
			
			//Check if the piece can go down 
			if (this.isPiecePosOk(this.tetromino, new Vector2D(0,1))) {
				//Apply the change to the tetromino
			    this.tetromino.position.setY(tetromino.position.getY()+1);
			}
			else {
				//Anchors it
				this.attachTetrominoToGrid(tetromino);
				this.tetromino = null; 
			}
		}
		else {
			//Spawn a new tetromino
			this.tetromino = new JStyle(); 
			this.tetromino.Rotate(-1);
			this.tetromino.position.setX(2);
			this.tetromino.position.setY(5);
		}
	}

	
	
}
