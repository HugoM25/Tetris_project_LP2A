package com.tetris_project.git;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface {
	
	public JFrame frame, frules, playFrame;
    private JLabel label, label1, label2, label3;
    private JPanel panel, panel1, panel2, panel3;
    private JButton playB, rulesB, returnB;
    private ButtonGroup difficulty;
    
    public Drawing canvas; 
    
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
    	
    	//The main panel (used for layout)
    	JPanel main = new JPanel(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints(); 
    	
    	//The layout panels 
    	JPanel header = new JPanel(); 
    	JPanel footer = new JPanel(); 
    	JPanel leftPart = new JPanel(); 
    	JPanel rightPart = new JPanel(); 
    	JPanel centerPart = new JPanel();
    	
    	//The header
    	c.fill = GridBagConstraints.BOTH;
        c.gridwidth = 3; 
        c.weighty = 0.05;
        c.gridy = 0;
         
        header = new JPanel(new GridBagLayout());
        header.setBackground(Color.BLACK);
        
        JLabel title = new JLabel("NOT TETRIS GAME"); 
        title.setForeground(Color.WHITE);
       
        header.add(title);
        
        main.add(header,c);
         
        //The infos on the left
        c.gridwidth = 1;
        c.weightx = 0.35; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 0; 
        leftPart = new JPanel();
        leftPart.setBackground(Color.BLACK);
        main.add(leftPart, c);
              
        //The game grid part
        c.gridwidth = 1;
        c.weightx = 0.30; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 1; 
        
        centerPart = new JPanel(new BorderLayout());
        centerPart.setBackground(Color.BLACK);
       
        //The canvas used to display the playGrid
        canvas = new Drawing();
        
        canvas.setGridDisplay(null);
      	canvas.requestFocus();
      	canvas.setFocusable(true);
        centerPart.add(canvas, BorderLayout.CENTER);
        
        main.add(centerPart, c);
        
        //The infos on the right
        c.gridwidth = 1;
        c.weightx = 0.35; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 2; 
        rightPart = new JPanel();
        rightPart.setBackground(Color.BLACK);
        main.add(rightPart, c);
        
        
        //The footer 
        footer = new JPanel(new GridBagLayout());
        footer.setBackground(Color.BLACK);
       
        c.gridwidth = 3; 
        c.weighty = 0.05;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 2;

        main.add(footer,c);
		//Finally add the main layout to the frame
		playFrame.add(main);
		//And configure the frame
		playFrame.pack();
        playFrame.validate();
        playFrame.setSize(1066, 600);
        playFrame.setLocationRelativeTo(null);
        playFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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