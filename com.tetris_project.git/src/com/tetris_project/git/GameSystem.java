package com.tetris_project.git;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetrominoes.*;


public class GameSystem {
	
	//Data 
	private int score; 
	//Used for the game
	private Grid grid; 
	private Tetromino tetromino;
	private TetroQueue tetroQueue; 
	public GameState state; 
	
	
	public Tetromino getTetromino() {
		return tetromino;
	}
	public void setTetromino(Tetromino tetromino) {
		this.tetromino = tetromino;
	}
	public GameSystem(int x, int y, Tetromino tetromino) {
		this.grid = new Grid(x,y); 
		this.tetromino = tetromino; 
		state = GameState.NOT_STARTED; 
	}
	public GameSystem() {
		this.grid = new Grid(); 
		state = GameState.NOT_STARTED;
		this.tetroQueue = new TetroQueue(); 
	}

	public Grid getGrid() {
		return grid;
	}
	public void setGrid(Grid grid) {
		this.grid = grid;
	}
	public void moveTetroLeft() {
		if (this.tetromino != null && grid.isPiecePosOk(tetromino, new Vector2D(-1,0))) {
			this.tetromino.position.add(new Vector2D(-1,0));
		}
	}
	public void moveTetroRight() {
		if (this.tetromino != null && grid.isPiecePosOk(tetromino, new Vector2D(1,0))) {
			this.tetromino.position.add(new Vector2D(1,0));
		}
	}
	
	public void rotateTetroLeft() {
		if (this.tetromino != null) {
			Tetromino tetrominoTest = new Tetromino(this.tetromino); 
			tetrominoTest.Rotate(-1);
			if (grid.isPiecePosOk(tetrominoTest, new Vector2D(0,0))) {
				this.tetromino.Rotate(-1); 
			}
		}
	}
	public int[][] showGrid(){
		return this.grid.gridToDisplay(this.tetromino);
		
	}
	public void hardDrop() {
		while (this.tetromino != null) {
			this.Update();
		}
	}
	public void Start() {
		state = GameState.PLAY;
		this.tetromino = this.tetroQueue.getTetro(); 
	}
	public void Update() {
		
		//Handle piece movement
		if (this.tetromino != null) {		
			
			//Check if the piece can go down 
			if (grid.isPiecePosOk(this.tetromino, new Vector2D(0,1))) {
				//Apply the change to the tetromino
			    this.tetromino.position.add(new Vector2D(0,1));;
			}
			else {
				//Anchors it
				grid.attachTetrominoToGrid(tetromino);
				this.tetromino = null; 
				//Check for lines to remove 
				List<Integer> indexes = grid.checkForLineToRemove();
				for (int i = 0; i < indexes.size() ; i++) {
					grid.removeLineFromGrid(indexes.get(i));
				}
				//Update score
				addScoreLines(indexes);
				
			}
		}
		else {
			//Spawn a new tetromino
			this.tetromino = this.tetroQueue.getTetro(); 
			//Just for easier debug for the moment spawn t-style piece
		    //this.tetromino = new TStyle(); 
			this.tetromino.position = new Vector2D(2,-1); 
		}
	}

	public void addScoreLines(List<Integer> indexesToBeRemoved) {
		
		int nblines = indexesToBeRemoved.size();
		
		switch (nblines) {
			case 4:
				this.score += 800;
				break;
			default:
				this.score += nblines*100;
		}
		
	}
	public Tetromino[] getNextTetrominoes() {
		return this.tetroQueue.seeNNextTetromino(3);
	}
}
