package com.tetris_project.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class HighScoreSys {
	private int HighScoreVal; 
	private PrintWriter out; 
	private String fileScoreName = "Res/saveScore.txt";
	
	public HighScoreSys() {
		try {
			this.readHighScore();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeHighScore() {
		try {
			out = new PrintWriter(fileScoreName);
			out.print(this.HighScoreVal);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void readHighScore() throws IOException {
        File file = new File(fileScoreName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            this.HighScoreVal = Integer.parseInt(st); 
        }
        br.close();
	}
	
	public void CheckHighScore(int newScore) {
		if (this.HighScoreVal <= newScore) {
			this.HighScoreVal = newScore;
			this.writeHighScore();
		}
	}
	
	public int getHighScore() {
		return this.HighScoreVal;
	}
}