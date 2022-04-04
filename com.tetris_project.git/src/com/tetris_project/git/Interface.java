package com.tetris_project.git;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends Game1{
	
	private JFrame frame, frules;
    private JLabel label, label1, label2, label3;
    private JPanel panel, panel1, panel2, panel3;
    private JButton playB, rulesB, returnB;
    private ButtonGroup difficulty;

	public void displayGUI() {
    	
		// Define frame
    	frame = new JFrame("TETRIS GAME");    	
    	frules = new JFrame("RULES");
    	setF(frame);
    	frame.setSize(350, 350);
    	frame.setLayout(new GridLayout(5, 1));
    	setF(frules);
    	frules.setSize(500, 500);

        frame.setVisible(true);
        
        // Define panels
        panel = new JPanel();
    	panel1 = new JPanel();
    	panel2 = new JPanel();
    	panel3 = new JPanel();
    	
        // Define labels
    	label = new JLabel("TETRIS GAME", JLabel.CENTER);
    	label1 = new JLabel("Choose a difficulty :", JLabel.CENTER);
    	label2 = new JLabel("RULES", JLabel.CENTER);
    	label3 = new JLabel("<html>Tetris is primarily made up of a field of play in which pieces of various geometric shapes, known as “tetrominoes (made of four connected squares)”, descend from the top of the field. During this descent, you can move and rotate the pieces until they reach the bottom of the field or land on\r\n"
    			+ "a piece that was placed before it. You can neither slow down nor stop the falling pieces, but\r\n"
    			+ "they can be accelerated. The goal of the game is to create as many horizontal lines of blocks as possible\r\n"
    			+ "using the pieces. When a line is finished, it vanishes, and the blocks above it fall one rank. Completing\r\n"
    			+ "lines grants points and accumulating a certain number of points moves you up a level, which\r\n"
    			+ "increases the number of points granted per completed line. If you cannot make the blocks\r\n"
    			+ "disappear quickly enough, the field will start to fill, and when the pieces reach the top of the field and\r\n"
    			+ "prevent the arrival of additional pieces, the game ends. At the end of each game, you receives a\r\n"
    			+ "score based on the number of lines that have been completed.</html>");
    	
    	// Define buttons
    	playB = new JButton(" Play ");
        rulesB = new JButton("Rules");
        returnB = new JButton("Return");
        
        // Define radio buttons
        difficulty = new ButtonGroup();     
        JRadioButton radio1 = new JRadioButton("1", true);
        JRadioButton radio2 = new JRadioButton("2", false);
        JRadioButton radio3 = new JRadioButton("3", false);
        JRadioButton radio4 = new JRadioButton("4", false);
        JRadioButton radio5 = new JRadioButton("5", false);

        // Add labels, panels and buttons to the frame/panels
        frame.add(label);
        frame.add(panel);
        frame.add(panel1);
        frame.add(label1);
        frame.add(panel2);
 
        panel.add(playB);        
        panel1.add(rulesB);

        // Set the color and the font
        //frame.getContentPane().setBackground(Color.decode("#77E4B4"));
        
        label.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setFont(new Font("Serif", Font.BOLD, 20)); 
        label2.setFont(new Font("Serif", Font.BOLD, 40)); 
        
        setP(panel);
        setP(panel1);
        setP(panel2);
        setP(panel3);
        
        setB(playB);
        setB(rulesB);
        setB(returnB);
        
        setRB(radio1);
        setRB(radio2);
        setRB(radio3);
        setRB(radio4);
        setRB(radio5);
   
        rulesB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {

    			frules.setLayout(new GridLayout(3, 1));
    		    frules.setVisible(true);
    		    frame.setVisible(false);
    		    
    		    frules.add(label2);
    		    frules.add(label3);
    		    frules.add(panel3);
    		    panel3.add(returnB);
    		    
    		}
    	});
        
        returnB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {

    		    frules.setVisible(false);
    		    frame.setVisible(true);  
    		}
    	});
    	
    }
	
	public void setF(JFrame f) {
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLocationRelativeTo(null);
	    f.getContentPane().setBackground(Color.decode("#77E4B4"));
	}
	
	public void setP(JPanel p) {
		p.setBackground(Color.decode("#77E4B4"));
	}
	
	public void setB(JButton b) {
		b.setFont(new Font("Serif", Font.BOLD, 20));
	}
	
	public void setRB(JRadioButton rb) {
		difficulty.add(rb);
		panel2.add(rb);
<<<<<<< Upstream, based on branch 'master' of https://github.com/HugoM25/Tetris_project_LP2A.git
		rb.setBackground(Color.ORANGE);
		
=======
		rb.setBackground(Color.decode("#77E4B4"));
>>>>>>> b7c6e38 color change
		rb.setFont(new Font("Serif", Font.BOLD, 20));
	}
}