package com.tetris_project.git;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetrominoes.*;


public class GameSystem {
	
	//Data 
	
	//Used for the game
	private Grid grid; 
	private Tetromino tetromino; 
	
	
	public Tetromino getTetromino() {
		return tetromino;
	}
	public void setTetromino(Tetromino tetromino) {
		this.tetromino = tetromino;
	}
	public GameSystem(int x, int y, Tetromino tetromino) {
		this.grid = new Grid(x,y); 
		this.tetromino = tetromino; 
	}
	public GameSystem() {
		this.grid = new Grid(); 
		this.tetromino = this.generateNextTetromino(); 
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
	public void Start() {
		
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
				
				//Debug
				grid.displayGridValue();
			}
		}
		else {
			//Spawn a new tetromino
			this.tetromino = generateNextTetromino(); 
			//Just for easier debug for the moment spawn t-style piece
		    //this.tetromino = new TStyle(); 
			this.tetromino.position = new Vector2D(2,-1); 
		}
	}

	public int addScoreLines(List<Integer> indexesToBeRemoved) {
		
		int score = 0;
		int nblines = indexesToBeRemoved.size();
		
		switch (nblines) {
			case 4:
				score += 800;
				break;
			default:
				score += nblines*100;
		}
		
		return score;
	}
	
	public Tetromino generateNextTetromino() {
		Random rand = new Random();
		int rand_perc = rand.nextInt(100);
		if (rand_perc < 10) {
			return new OStyle();
		}
		else if (rand_perc < 20) {
			return new JStyle(); 
		}
		else if (rand_perc < 30) {
			return new IStyle(); 
		}
		else if (rand_perc < 40) {
			return new LStyle();
		}
		else if (rand_perc < 50) {
			//Celui là est en double
			return new OStyle(); 
		}
		else if (rand_perc < 60) {
			return new SStyle();
		}
		else if (rand_perc < 70) {
			return new TStyle(); 
		}
		else {
			return new ZStyle(); 
		}
		
	}

}
