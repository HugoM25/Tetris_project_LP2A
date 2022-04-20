package tetrominoes;
import com.tetris_project.git.Vector2D;

public class Tetromino {
	
	public Vector2D position;
	public int orientation;
	public int[][][] arrayPiece; 
	public int colorIndex;  
	
	public Tetromino(Vector2D position, int orientation, int[][][] arrayPiece) {
		this.position = position;
		this.orientation = orientation;
		this.arrayPiece = arrayPiece;
		this.colorIndex =1; 
	}
	public Tetromino() {
		this.position = new Vector2D(); 
		this.orientation = 0; 
		this.arrayPiece = new int[5][5][5]; 
		this.colorIndex =1; 
	}
	public Tetromino(Tetromino tetromino) {
		this.position = new Vector2D(tetromino.position); 
		this.orientation = tetromino.orientation; 
		this.arrayPiece = tetromino.arrayPiece; 
		this.colorIndex = tetromino.colorIndex;
	}

	public void Rotate(int direction) {
		//Rotate (-1 clockwise / 1 counter-clockwise) 
		int rotaTest = orientation + direction; 
		//Handle out of bounds rotations
		if ( rotaTest < 0) {
			rotaTest = 3;
		}
		else if (rotaTest  > 3) {
			rotaTest = 0; 
		}
		//Rotate 
		this.orientation = rotaTest; 
	}	
	public void Move(Vector2D direction) {
		position.add(direction);
	}
}
