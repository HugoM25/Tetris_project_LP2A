package com.tetris_project.git;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame{
	
	public JFrame frame, frules, playFrame;
    private JLabel label, label1, label2, label3;
    private JPanel panel, panel1, panel2, panel3;
    private JButton playB, rulesB, returnB;
    private ButtonGroup difficulty;
    
    public final Drawing canvas = new Drawing(); 
    
    public Interface() {
    	InitializeFrames();
    	frame.setVisible(true);
    	
    }
    public void InitializeFrames() {
    	InitializePlayFrame();
    	InitializeMenuFrame();
    	InitializeRulesFrame(); 
    }
    
    public void InitializePlayFrame() {
    	playFrame = new JFrame("Tetris Game");
    	setF(playFrame);
    	playFrame.setSize(700,634);
    	canvas.setSize(700, 634);
		canvas.setGridDisplay(null);
		playFrame.add(canvas);
		canvas.requestFocus();
		canvas.setFocusable(true);
	}
    
    public void RefreshPlayFrame(int[][] gridDisplay) {    	
		canvas.setGridDisplay(gridDisplay);
    	canvas.repaint();
    }
    
    public void InitializeMenuFrame() {
    	
    	frame = new JFrame("TETRIS MENU");    
    	setF(frame);
    	frame.setSize(350, 350);
    	frame.setLayout(new GridLayout(5, 1));
        
        // Define panels
        panel = new JPanel();
    	panel1 = new JPanel();
    	panel2 = new JPanel();

    	// Define labels
    	label = new JLabel("TETRIS GAME", JLabel.CENTER);
    	label1 = new JLabel("Choose a difficulty :", JLabel.CENTER);
    	
    	
    	label2 = new JLabel("RULES", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        label1.setFont(new Font("Serif", Font.BOLD, 20)); 
        label2.setFont(new Font("Serif", Font.BOLD, 40)); 
         
             
    	// Define buttons
    	playB = new JButton(" Play ");
        rulesB = new JButton("Rules");
        returnB = new JButton("Return");
        
        panel.add(playB);        
        panel1.add(rulesB);
        
        // Define radio buttons
        difficulty = new ButtonGroup();     
        JRadioButton radio1 = new JRadioButton("1", true);
        JRadioButton radio2 = new JRadioButton("2", false);
        JRadioButton radio3 = new JRadioButton("3", false);
        JRadioButton radio4 = new JRadioButton("4", false);
        JRadioButton radio5 = new JRadioButton("5", false); 
        setP(panel);
        setP(panel1);
        setP(panel2);
        setB(playB);
        setB(rulesB);
        setRB(radio1);
        setRB(radio2);
        setRB(radio3);
        setRB(radio4);
        setRB(radio5);
        
        // Add labels, panels and buttons to the frame/panels
        frame.add(label);
        frame.add(panel);
        frame.add(panel1);
        frame.add(label1);
        frame.add(panel2);
        
        playB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {
    		    frame.setVisible(false);
    		    playFrame.setVisible(true);
    		    canvas.requestFocus();
    		}
    	});
   
        rulesB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {
    		    frules.setVisible(true);
    		    frame.setVisible(false);
    		}
    	});
    }
    
    
    public void InitializeRulesFrame() {
    	frules = new JFrame("RULES");
    	setF(frules);
    	frules.setSize(700, 500);
    	
    	panel3 = new JPanel();
    	

    	label3 = new JLabel("<html>Tetris is primarily made up of a field of play in which pieces of various"
    			+ " geometric shapes, known as “tetrominoes (made of four connected squares)”, descend from the"
    			+ " top of the field. During this descent, you can move and rotate the pieces until they reach"
    			+ " the bottom of the field or land on a piece that was placed before it. You can neither slow"
    			+ " down nor stop the falling pieces, but they can be accelerated. The goal of the game is to"
    			+ " create as many horizontal lines of blocks as possible using the pieces. When a line is finished,"
    			+ " it vanishes, and the blocks above it fall one rank. Completing lines grants points and accumulating"
    			+ " a certain number of points moves you up a level, which increases the number of points granted per"
    			+ " completed line. If you cannot make the blocks disappear quickly enough, the field will start to fill,"
    			+ " and when the pieces reach the top of the field and prevent the arrival of additional pieces, the game"
    			+ " ends. At the end of each game, you receives a score based on the number of lines that have been completed.</html>");
    	
		frules.setLayout(new GridLayout(3, 1));	    
		returnB = new JButton("Return");
	    frules.add(label2);
	    frules.add(label3);
	    frules.add(panel3);
        setB(returnB);
	    panel3.add(returnB);
	    setP(panel3);
	    
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
		rb.setBackground(Color.ORANGE);
		rb.setBackground(Color.decode("#77E4B4"));
		rb.setFont(new Font("Serif", Font.BOLD, 20));
	}
}