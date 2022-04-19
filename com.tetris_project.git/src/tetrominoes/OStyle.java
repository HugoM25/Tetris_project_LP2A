package tetrominoes;

public class OStyle extends Tetromino {
	public OStyle() {
		super();
		this.orientation = 0; 
		this.colorIndex = 2; 
		InitializeGrid(); 
	}
	public void InitializeGrid() {
		//Fill the array with zeros
		this.arrayPiece = new int[4][2][2]; 
		//Place the blocks
		
		//Rotation 0 :
		this.arrayPiece[0][0][0] = this.colorIndex; 
		this.arrayPiece[0][0][1] = this.colorIndex; 
		this.arrayPiece[0][1][0] = this.colorIndex; 
		this.arrayPiece[0][1][1] = this.colorIndex; 
		
		//Rotation 1 :
		this.arrayPiece[1][0][0] = this.colorIndex; 
		this.arrayPiece[1][0][1] = this.colorIndex; 
		this.arrayPiece[1][1][0] = this.colorIndex; 
		this.arrayPiece[1][1][1] = this.colorIndex; 
		
		//Rotation 2 :
		this.arrayPiece[2][0][0] = this.colorIndex; 
		this.arrayPiece[2][0][1] = this.colorIndex; 
		this.arrayPiece[2][1][0] = this.colorIndex; 
		this.arrayPiece[2][1][1] = this.colorIndex; 
		
		//Rotation 3 :
		this.arrayPiece[3][0][0] = this.colorIndex; 
		this.arrayPiece[3][0][1] = this.colorIndex; 
		this.arrayPiece[3][1][0] = this.colorIndex; 
		this.arrayPiece[3][1][1] = this.colorIndex; 
	}
}
