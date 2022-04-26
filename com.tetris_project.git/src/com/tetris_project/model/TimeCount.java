package com.tetris_project.model;

public class TimeCount {
	private int time;

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	} 
	
	public TimeCount(){
		this.time = 0; 
	}
	public void resetTimer() {
		this.time = 0; 
	}
	public void updateTimer(int deltaTime) {
		this.time += deltaTime;
	}
	public String toString() {
		int tmptime = this.time/1000; 
		//time is now in seconds therefore :
		int hours = tmptime % 3600;
		int min = tmptime % 60 - hours*3600;
		int seconds = tmptime - hours*3600 - min*60;
		
		return hours + ":" + min + ":" + seconds;
		
	}
}
