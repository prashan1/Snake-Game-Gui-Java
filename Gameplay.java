import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.Timer;
import javax.swing.*;
public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private ImageIcon titleImage ;
	private ImageIcon snakeimage;
	
	private int[] snakexLength = new int[900];
	private int[] snakeyLength = new int[750];
	
private int[] enemyxPosition ={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825};
	private int[] enemyyPosition = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500};
		
		Random rand = new Random();
	 int xpos = rand.nextInt(34);
	 int ypos = rand.nextInt(23);
	
	
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	private int lengthOfSnake = 3;
	private int moves = 0 ;
	private int scores = 0 ;
	
	private ImageIcon rightmouth ;
	private ImageIcon leftmouth ;
	private ImageIcon downmouth ;
	private ImageIcon upmouth ;
	private ImageIcon enemyimage;
	
	private Timer timer;
	private int delay = 90;
	
	private int xIndex = 0 ;
	private int yIndex = 0 ;
	 
	public Gameplay(){
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer( delay , this );
		timer.start();
	}
	public void paint(Graphics g){
		
		if( moves == 0 ){
			
			snakexLength[2] = 50;
			snakexLength[1] = 75;
			snakexLength[0] = 100;
			
			snakeyLength[2] = 100;
			snakeyLength[1] = 100;
			snakeyLength[0] = 100;
		}
		g.setColor(Color.white);
		g.drawRect(24,10,851,55);
		
		titleImage = new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this,g,25,11);
		
		g.setColor(Color.white);
		g.drawRect(24,74,851,577);
		
		g.setColor(Color.black);
		g.fillRect(25,75,850,575);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial" , Font.PLAIN , 13 ));
		g.drawString("Scores: " + scores , 760 , 30 );
		
		g.setColor(Color.white);
		g.setFont(new Font("arial" , Font.PLAIN , 13 ));
		g.drawString("Length: " + lengthOfSnake , 760 , 50 );
		
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this,g,snakexLength[0],snakeyLength[0]);
		for(int a = 0 ; a < lengthOfSnake ; a++ ){
		
			
			if(a != 0 ){
			
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if( a == 0 && right){
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if( a == 0 && left){
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if( a == 0 && up){
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
			if( a == 0 && down){
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this,g,snakexLength[a],snakeyLength[a]);
			}
		}	
		enemyimage = new ImageIcon("enemy.png");
		enemyimage.paintIcon(this,g,enemyxPosition[xpos],enemyyPosition[ypos]);
		
	if( snakexLength[0] == enemyxPosition[ xpos ] && snakeyLength[ 0] == enemyyPosition[ypos] ){
		lengthOfSnake++;
		scores++;
	xpos = rand.nextInt(34);
	ypos = rand.nextInt(23);
	
	}
	for(int i = 1 ; i < lengthOfSnake ; i++ )
		if(snakexLength[0] == snakexLength[i] && snakeyLength[0] == snakeyLength[i]){
			right = false;
			left = false;
			up = false;
			down = false;
			g.setColor(Color.white);
			g.setFont(new Font("arial" , Font.PLAIN , 30 ));
			g.drawString("GameOver! press Space to restart ",300,350 );
			
		}
		
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e){
		timer.start();
		if( right ){
			
			for(int i = lengthOfSnake - 1; i >= 0 ; i--)
				snakeyLength[ i + 1] = snakeyLength[ i ];
			
			for( int r = lengthOfSnake ; r >= 0 ; r--){
			
			 if( r == 0 )
				snakexLength[r] = snakexLength[r] + 25;
			 else
				 snakexLength[r ] = snakexLength[ r - 1 ];
			
			if( snakexLength[r] > 850 )
				snakexLength[r] = 25;
			}
			
			 repaint();	
		}
		if( left ){
			
			for(int i = lengthOfSnake - 1; i >= 0 ; i--)
				snakeyLength[ i + 1] = snakeyLength[ i ];
			
			for(int r = lengthOfSnake ; r >= 0 ; r-- ){
				if( r == 0 )
					snakexLength[r] = snakexLength[r] - 25;
				else
					snakexLength[ r ] = snakexLength[ r - 1 ];
			
			
			    if( snakexLength[r] < 25 )
					snakexLength[r] = 850;
			}
			repaint();	
		}
		if( down ){
			
			for(int i = lengthOfSnake - 1; i >= 0 ; i--)
				snakexLength[ i + 1] = snakexLength[ i ];
			
			for(int r = lengthOfSnake ; r >= 0 ; r-- ){
				if( r == 0 )
				snakeyLength[r] = snakeyLength[r] + 25;
			    else
				   snakeyLength[ r ] = snakeyLength[ r - 1 ];
			   
				if( snakeyLength[r] > 625 )
					snakeyLength[r] = 75;
			 }
			repaint();	
		}
		if( up ){
			
			for(int i = lengthOfSnake - 1; i >= 0 ; i--)
				snakexLength[ i + 1] = snakexLength[ i ];
			
			for(int r = lengthOfSnake ; r >= 0 ; r--  ){
				if( r == 0 )
					snakeyLength[r] = snakeyLength[r] - 25;
			    else
				   snakeyLength[ r ] = snakeyLength[ r - 1 ];
			
				if( snakeyLength[r] < 75 )
					snakeyLength[r] = 625;
			}repaint();		
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e){}
	@Override
	public void keyPressed(KeyEvent e){
		
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
				scores = 0 ;
				lengthOfSnake = 4 ;
				
				repaint();
		}
		
		
		if( e.getKeyCode() == KeyEvent.VK_RIGHT ){
			moves++;
			
			if( !left )
				right = true;
			else{
				right = false;
				left = true ;
			}
				up = false;
				down = false;
				
			}
		
		if( e.getKeyCode() == KeyEvent.VK_LEFT ){
			moves++;
			if( !right )
				left = true;
			else{
				right = true;
				left = false;
			}
			
				up = false;
				down = false;
			}
		
		if( e.getKeyCode() == KeyEvent.VK_UP ){
			moves++;
		   
			if( !down )
				up = true;
			else{
				up = false;
				down = true;
			}
				right = false;
				left = false;
			}
		
		if( e.getKeyCode() == KeyEvent.VK_DOWN ){
			moves++;
		
			if( !up )
				down = true;
			else{
				down = false;
				up = true;
			}
				left = false;
				right = false;
			}
		
		
	}
		@Override
	public void keyReleased(KeyEvent e){}
}
