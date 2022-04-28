package com.tetris_project.model;

import java.util.List;

import tetrominoes.*;

public class GameSystem {
	
	//Data 
	private int score; 
	private int linesClearedCount; 
	//Used for the game
	private Grid grid; 
	private Tetromino tetromino;
	private TetroQueue tetroQueue; 
	public GameState state; 
	private boolean isInCombo; 
	
	private Difficulty difficulty; 
	private int nbFramesBetweenUpdates;
	
	private TimeCount timer; 
	private HighScoreSys bestScore; 
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public Tetromino getTetromino() {
		return tetromino;
	}
	
	public void setTetromino(Tetromino tetromino) {
		this.tetromino = tetromino;
	}
	
	public int getNbFramesBetweenUpdates() {
		return this.nbFramesBetweenUpdates; 
	}
	
	public TimeCount getTimer() {
		return this.timer;
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
		this.isInCombo = false;
		this.nbFramesBetweenUpdates = 60; 
		this.difficulty = Difficulty.LEGEND;
		this.timer = new TimeCount(); 
		this.bestScore = new HighScoreSys();
		this.linesClearedCount = 0; 
	}
	
	public void setDifficulty(String diff) {
		//Set the difficulty based on the active radioButton string
		if (diff.equals("Easy")) {
			this.difficulty = Difficulty.EASY;
		}else if (diff.equals("Medium")) {
			this.difficulty = Difficulty.MEDIUM;
		}else if (diff.equals("Hard")) {
			this.difficulty = Difficulty.HARD;
		}else if (diff.equals("Pro")) {
			this.difficulty = Difficulty.PRO;
		}else if (diff.equals("Legend")) {
			this.difficulty = Difficulty.LEGEND;
		}else {
			this.difficulty = Difficulty.EASY;
		}
	}
	
	public int getLinesClearedCount() {
		return this.linesClearedCount; 
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
		//Check if we have any tetromino to rotate
		if (this.tetromino != null) {
			
			//Create a clone of the tetromino and try to rotate it
			Tetromino tetrominoTest = new Tetromino(this.tetromino); 
			tetrominoTest.Rotate(-1);
			//If the rotation is ok then rotate the original tetromino
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
	
	public void setFramesWithDifficulty() {
		switch(this.difficulty) {
			case EASY :
				this.nbFramesBetweenUpdates = 60;
				break;
			case MEDIUM :
				this.nbFramesBetweenUpdates = 40;
				break; 
			case PRO :
				this.nbFramesBetweenUpdates = 30;
				break;
			case LEGEND :
				this.nbFramesBetweenUpdates = 10;
				break;
			default :
				this.nbFramesBetweenUpdates = 60;
				break;
		}
	}
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}
	
	public void GameOver() {
		state = GameState.END;
		this.bestScore.CheckHighScore(score);
	}
	
	public void Restart() {
		this.grid.resetGrid();
		this.score = 0;
		this.Start();
	}
	
	public void Start() {
		setFramesWithDifficulty();
		state = GameState.PLAY;
		this.tetromino = this.tetroQueue.getTetro();
		this.tetromino.position = new Vector2D(grid.getWidth()/2,-1); 
		timer.resetTimer(); 
		this.isInCombo = false;
		this.linesClearedCount = 0; 
	}

	public void Update() {
		//Handle piece movement
		if (this.tetromino != null) {		
			
			//Check if the piece can go down 
			if (grid.isPiecePosOk(this.tetromino, new Vector2D(0,1))) {
				//Apply the change to the tetromino
			    this.tetromino.position.add(new Vector2D(0,1));;
			}else {
				//Check if game is lost
				if (this.tetromino.position.getY() < 0) {
					GameOver();
				}
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
		}else {
			//Spawn a new tetromino
			this.tetromino = this.tetroQueue.getTetro(); 
			this.tetromino.position = new Vector2D(grid.getWidth()/2,-1); 
		}
	}

	public void addScoreLines(List<Integer> indexesToBeRemoved) {
		
		int nblines = indexesToBeRemoved.size();
		switch (nblines) {
			case 4:
				if (isInCombo == true) {
					this.score += 1200;
				}
				else {
					this.score += 800;
					isInCombo = true;
				}
				this.linesClearedCount += 4; 
				break;
			case 0 :
				break;
			default:
				this.score += nblines*100;
				this.linesClearedCount += nblines;
				isInCombo = false;
				break;
		}
		
	}
	
	public int getHighscore() {
		return this.bestScore.getHighScore();
	}
	
	public Tetromino[] getNextTetrominoes() {
		return this.tetroQueue.seeNNextTetromino(3);
	}
}
