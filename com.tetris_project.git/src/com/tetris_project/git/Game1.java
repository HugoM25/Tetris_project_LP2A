package com.tetris_project.git;

import javax.swing.SwingUtilities;

public class Game1 {

	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Interface().displayGUI();
			}
		});
	}
}