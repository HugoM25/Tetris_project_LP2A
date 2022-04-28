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
		int tmpTime = Math.round(this.time/1000); 
		
		//time is now in seconds therefore :
		int hours = tmpTime/3600;
		int min = tmpTime / 60 - hours*3600;
		int seconds = tmpTime - hours*3600 - min*60;
		//makes it prettier
		String hoursS = "" + hours;
		String minS = "" + min ;
		String secondsS = "" + seconds; 
		if (hours < 10) {
			hoursS = "0" + hoursS; 
		}
		if (min < 10) {
			minS = "0" + minS;
		}
		if (seconds < 10) {
			secondsS = "0" + secondsS;
		}
	
		return hoursS + ":" + minS + ":" + secondsS;
		
	}
}