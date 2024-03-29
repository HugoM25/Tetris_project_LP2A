package com.tetris_project.view;

import javax.swing.*;
import tetrominoes.Tetromino;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameGUI {
	
	public JFrame frame, frules, playFrame;
    private JLabel label, label1, label2, label3;
    private JPanel panel, panel1, panel2, panel3;
    private JButton playB, rulesB, returnB;
    private ButtonGroup difficulty;
    
    public String[] COLOR_PALETTE = {"#0B132B", "#f7f0f5" , "#1ccad8" , "#db5461" , "#d6e681" , "#4b7f52" }; 
    public Drawing canvas; 
    public JLabel scoreText, timer, state, pause, restart, highscore, linesCleared; 
    public DispTetroPanel nextT1, nextT2, nextT3;
    
    private String diff;
    
    public GameGUI() {
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
    	
    	//Created new constraints for the 4 part of the game Interface
    	GridBagConstraints c = new GridBagConstraints(); 
    	GridBagConstraints c2 = new GridBagConstraints();
    	GridBagConstraints c3 = new GridBagConstraints();
    	GridBagConstraints c4 = new GridBagConstraints();
    	
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
        header.setBackground(Color.decode("#0B132B"));
        
        JLabel title = new JLabel("NOT TETRIS GAME"); 
        title.setFont(new Font("Verdana", Font.PLAIN, 30));
        title.setForeground(Color.WHITE);
       
        header.add(title);
        main.add(header,c);
         

        c.gridwidth = 1;
        c.weightx = 0.35; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 0; 
        leftPart = new JPanel(new GridBagLayout());
        leftPart.setBackground(Color.decode("#0B132B"));
        main.add(leftPart, c);
        
        c3.gridwidth = 1;
        c3.weightx = 1; 
        c3.weighty = 0.2;
        c3.gridy = 0;
        c3.gridx = 0;
        scoreText = new JLabel("Score : 0");
        scoreText.setMaximumSize( new Dimension(  10, 10));
        scoreText.setForeground(Color.WHITE);
        scoreText.setFont(new Font("Verdana", Font.PLAIN, 20));
        leftPart.add(scoreText,c3);
        
        c3.gridwidth = 1;
        c3.weightx = 1; 
        c3.weighty = 0.2;
        c3.gridy = 1;
        c3.gridx = 0; 
        highscore = new JLabel("HighScore : 0");
        highscore.setForeground(Color.WHITE);
        highscore.setFont(new Font("Verdana", Font.PLAIN, 20));
        leftPart.add(highscore,c3);
        
        c3.gridwidth = 1;
        c3.weightx = 1; 
        c3.weighty = 0.2;
        c3.gridy = 2;
        c3.gridx = 0; 
        timer = new JLabel("TIMER : 0");
        timer.setForeground(Color.WHITE);
        timer.setFont(new Font("Verdana", Font.PLAIN, 20));
        leftPart.add(timer,c3);
        
        c3.gridwidth = 1;
        c3.weightx = 1; 
        c3.weighty = 0.2;
        c3.gridy = 3;
        c3.gridx = 0; 
        linesCleared = new JLabel("LINES CLEARED : ");
        linesCleared.setForeground(Color.WHITE);
        linesCleared.setFont(new Font("Verdana", Font.PLAIN, 15));
        leftPart.add(linesCleared,c3);
        
        c3.gridwidth = 1;
        c3.weightx = 1; 
        c3.weighty = 0.2;
        c3.gridy = 4;
        c3.gridx = 0; 
        state = new JLabel("Difficulty : NULL");
        state.setForeground(Color.WHITE);
        state.setFont(new Font("Verdana", Font.PLAIN, 15));
        leftPart.add(state,c3);
           
        //The game grid part
        c.gridwidth = 1;
        c.weightx = 0.30; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 1; 
        centerPart = new JPanel(new BorderLayout());
        centerPart.setBackground(Color.decode("#0B132B"));
       
        //The canvas used to display the playGrid
        canvas = new Drawing();
        
        canvas.setGridDisplay(null);
      	canvas.requestFocus();
      	canvas.setFocusable(true);
      	
        centerPart.add(canvas, BorderLayout.CENTER);
        
        main.add(centerPart, c);
        
        //The info on the right + constraints
        c.gridwidth = 1;
        c.weightx = 0.25; 
        c.weighty = 0.90;
        c.gridy = 1;
        c.gridx = 2; 
        rightPart = new JPanel(new GridBagLayout());
        rightPart.setBackground(Color.decode("#0B132B"));
        
        JPanel upRight = new JPanel(new GridBagLayout());
        upRight.setBackground(Color.decode("#0B132B"));
        JPanel downRight = new JPanel(new GridBagLayout());
        downRight.setBackground(Color.decode("#0B132B"));
        JPanel middleRight = new JPanel(new GridBagLayout());
        middleRight.setBackground(Color.decode("#0B132B"));
        JLabel titleNext = new JLabel("NEXT TETROMINOES"); 
        titleNext.setFont(new Font("Verdana", Font.PLAIN, 15));
        
        c2.fill = GridBagConstraints.BOTH;
    	c2.weightx = 1;
    	c2.weighty = 0.1;
        c2.gridx = 0; 
        c2.gridy = 0;
        rightPart.add(upRight,c2); 
        c2.gridx = 0; 
        c2.gridy = 1;
        c2.weighty = 0.4;
        rightPart.add(middleRight, c2);
        c2.gridx = 0; 
        c2.gridy = 2;
        c2.weighty = 0.5;
        rightPart.add(downRight ,c2);
        
        c2.insets = new Insets(10,10,10,10);
        c2.weightx = 1;
        c2.weighty = 0.1;
        c2.gridx =0;
        c2.gridy =0;
        
        title.setForeground(Color.WHITE);
        header.add(title);
        
        //The info on the middle Right + constraints
        nextT1 = new DispTetroPanel();
        c2.weightx = 1;
        c2.weighty = 0.2;
        c2.gridx =0;
        c2.gridy =1;
        middleRight.add(nextT1, c2);
        
        c2.gridy =2;
        nextT2 = new DispTetroPanel();
        middleRight.add(nextT2, c2);
        c2.gridy =3;
        nextT3 = new DispTetroPanel();
        middleRight.add(nextT3, c2);
        
        //The info on the up Right + constraints
        upRight.add(titleNext); 
        titleNext.setForeground(Color.WHITE);
        
        //The info on the down Right + constraints
        c4.fill = GridBagConstraints.CENTER;
        c4.weightx = 1; 
        c4.weighty = 0.6;
        c4.gridy = 0;
        c4.gridx = 0; 
        JLabel startLabel = new JLabel("START (press: s)");
        startLabel.setForeground(Color.WHITE);
        startLabel.setFont(new Font("Verdana", Font.PLAIN, 15));
        downRight.add(startLabel,c4);   
        
        c4.weightx = 1; 
        c4.weighty = 0.2;
        c4.gridy = 1;
        c4.gridx = 0; 
        pause = new JLabel("PAUSE (press: p)");
        pause.setForeground(Color.WHITE);
        pause.setFont(new Font("Verdana", Font.PLAIN, 15));
        downRight.add(pause,c4);
        
        c4.weighty = 0.2;
        c4.gridy = 	2;
        restart = new JLabel("RESTART (press: r)");
        restart.setForeground(Color.WHITE);
        restart.setFont(new Font("Verdana", Font.PLAIN, 15));
        downRight.add(restart,c4);    
        
        JPanel empty = new JPanel();
        empty.setBackground(Color.decode("#0B132B"));
        rightPart.add(empty,c2);
        
        main.add(rightPart, c);
        c2.weighty = 0.3;
        c2.gridy =4;
        
        //The footer 
        footer = new JPanel(new GridBagLayout());
        footer.setBackground(Color.decode("#0B132B"));
       
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
    
    public void DisplayNextTetrominoes(Tetromino[] tetrominoes) {
    	nextT1.setTetromino(tetrominoes[0]);
    	nextT2.setTetromino(tetrominoes[1]);
    	nextT3.setTetromino(tetrominoes[2]);
    	nextT1.repaint();
    	nextT2.repaint();
    	nextT3.repaint();
    }
    
    public void setScoreText(String text) {
    	if (scoreText != null) {
    		scoreText.setText(text);
    	}
    	 
    }
    
    public void setHighscoreText(String text) {
    	if (highscore != null) {
    		highscore.setText(text);
    	}
    }
    
    public void setTimerText(String text) {
    	if (timer != null) {
    		timer.setText(text);
    	}
    	 
    }
    
    public void setStateText(String text) {
    	if (state != null) {
    		state.setText(text);
    	}
    	 
    }
    
    public void setLinesClearedText(String text) {
    	if ( linesCleared != null ) {
    		linesCleared.setText(text);
    	}
    }
    
    public void InitializeMenuFrame() {
    	
    	// Set the principal frame
    	frame = new JFrame("TETRIS MENU");    
    	setF(frame);
    	frame.setSize(500, 350);
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
        label.setForeground(Color.WHITE);
        
        label1.setFont(new Font("Serif", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        
        label2.setFont(new Font("Serif", Font.BOLD, 40));
        label2.setForeground(Color.WHITE); 
             
    	// Define buttons
    	playB = new JButton(" Play ");
        rulesB = new JButton("Rules");
        returnB = new JButton("Return");
        
        panel.add(playB);        
        panel1.add(rulesB);
        
        // Define radio buttons
        difficulty = new ButtonGroup();     
        JRadioButton radio1 = new JRadioButton("Easy", true);
        JRadioButton radio2 = new JRadioButton("Medium", false);
        JRadioButton radio3 = new JRadioButton("Hard", false);
        JRadioButton radio4 = new JRadioButton("Pro", false);
        JRadioButton radio5 = new JRadioButton("Legend", false); 

        // Add an action when the button is selected
        radio1.setActionCommand("Easy");
        radio2.setActionCommand("Medium");
        radio3.setActionCommand("Hard");
        radio4.setActionCommand("Pro");
        radio5.setActionCommand("Legend");
  
        //set the different objects
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
        
        // ActionListener is for knowing what he have to do when we push the button
        playB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {
    		    frame.setVisible(false);
    		    playFrame.setVisible(true);
    		    canvas.requestFocus();
    		    diff = difficulty.getSelection().getActionCommand();
    		}
    	});
   
        rulesB.addActionListener( new ActionListener()	{
    		public void actionPerformed(ActionEvent ae) {
    		    frules.setVisible(true);
    		    frame.setVisible(false);
    		}
    	});
    }
    
    public String getDiff() {
    	return this.diff;
    }
    
    public void setTimeText(String timeText) {
    	this.timer.setText(timeText);
    }
    
    public void InitializeRulesFrame() {
    	frules = new JFrame("RULES");
    	setF(frules);
    	frules.setSize(700, 500);
    	
    	panel3 = new JPanel();

    	label3 = new JLabel("<html> &emsp;&emsp;Tetris is primarily made up of a field of play in which pieces of various"
    			+ " geometric shapes, known as “tetrominoes (made of four connected squares)”, descend from the"
    			+ " top of the field. During this descent, you can move and rotate the pieces until they reach"
    			+ " the bottom of the field or land on a piece that was placed before it. You can neither slow"
    			+ " down nor stop the falling pieces, but they can be accelerated. The goal of the game is to"
    			+ " create as many horizontal lines of blocks as possible using the pieces. When a line is finished,"
    			+ " it vanishes, and the blocks above it fall one rank. Completing lines grants points and accumulating"
    			+ " a certain number of points moves you up a level, which increases the number of points granted per"
    			+ " completed line. If you cannot make the blocks disappear quickly enough, the field will start to fill,"
    			+ " and when the pieces reach the top of the field and prevent the arrival of additional pieces, the game"
    			+ " ends. At the end of each game, you receives a score based on the number of lines that have been completed.<html>");
    	
    	label3.setForeground(Color.WHITE);
    	label3.setHorizontalTextPosition(SwingConstants.RIGHT);
    	
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
	    f.getContentPane().setBackground(Color.decode("#0B132B"));
	}
	
	public void setP(JPanel p) {
		p.setBackground(Color.decode("#0B132B"));
	}
	
	public void setB(JButton b) {
		b.setFont(new Font("Serif", Font.BOLD, 20));
	}
	
	public void setRB(JRadioButton rb) {
		difficulty.add(rb);
		panel2.add(rb);
		rb.setBackground(Color.decode("#0B132B"));
		rb.setForeground(Color.WHITE);
		rb.setFont(new Font("Serif", Font.BOLD, 20));
	}
}