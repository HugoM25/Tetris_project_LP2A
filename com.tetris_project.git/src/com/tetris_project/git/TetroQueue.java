package com.tetris_project.git;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tetrominoes.*;

public class TetroQueue {
	
	private Tetromino bag;
	private List<Tetromino> queue; 
	
	public TetroQueue() {
		this.queue = new ArrayList<Tetromino>(); 
		this.fillQueue();
	}
	public Tetromino getTetro() {
		//Get first tetro in queue
		Tetromino tmpTetro = queue.get(0); 
		//Remove it from the queue
	    queue.remove(0);
	    //If size of queue is too small fill it
		if (this.queue.size() < 5) {
			this.fillQueue();
		}
		//Return the tetro
		return tmpTetro; 
	}
	public void fillQueue() {
		//Create a bag with every tetromino piece
		Tetromino[] bag = {new IStyle(), new JStyle(), new LStyle(), new OStyle(), new SStyle(), new TStyle(), new ZStyle()};
		
		//Shuffles it randomly
		Random rand = new Random();
		//Choose random number of perma iteration
		int bagLength = bag.length; 
		int randNumPerma = rand.nextInt(bagLength);
		
		for (int i = 0; i < randNumPerma ; i++) {
			//Choose two randoms elements in the list
			int randIndex1 = rand.nextInt(bagLength);
			int randIndex2 = rand.nextInt(bagLength);
			//Swap them
			Tetromino tmp = bag[randIndex1];
			bag[randIndex1] = bag[randIndex2]; 
			bag[randIndex2] = tmp; 
		}
		
		//Add the tetrominoes to the queue
		for (int j = 0; j < bagLength; j++) {
			this.queue.add(bag[j]);
		}
	
	}

}
