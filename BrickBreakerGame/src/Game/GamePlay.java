package Game;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;
import javax.swing.JPanel;
public class GamePlay extends JPanel implements KeyListener, ActionListener
{
	private boolean play = false;
	private int score = 0;
	private int totalbricks = 21;
	private Timer timer;
	private int delay = 0;
	private int playerX = 310;
	private int BallposX = 120;
	private int BallposY = 350;
	private int BallXdir = -1;
	private int BallYdir = -2;
	private Bricks map;
	
	public GamePlay()
	{
		map = new Bricks(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g)
	{
		// background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//draw bricks
		map.draw((Graphics2D)g);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("" +score, 592, 30);
		
		//the paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(BallposX,BallposY,20,20);
		
		if(totalbricks<=0)
		{
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Hooray Victory", 260, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
			
		}
		if(BallposY > 570)
		{
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Better Luck Next Time", 190, 300);
			
			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press Enter to Restart", 230, 350);
		}
		g.dispose();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		if(play)
		{
			if(new Rectangle(BallposX,BallposY, 20,20).intersects(new Rectangle(playerX,550,100,8))) 
			{
				BallYdir = -BallYdir;
			}
			A:for(int i = 0; i<map.map.length;i++)
			{
				for(int j = 0; j<map.map[0].length; j++ ) 
				{
					if(map.map[i][j] >0 )
					{
						int brickX = j*map.brickwidth + 80;
						int brickY = i*map.brickheight +50;
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;
						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);
						Rectangle ballrect = new Rectangle(BallposX,BallposY,20,20);
						Rectangle brickrect = rect;
						
						if(ballrect.intersects(brickrect)) {
							map.setBrickValue(0, i,j);
							totalbricks--;
							score += 5;
							if(BallposX + 19<=brickrect.x || BallposX+ 1>=brickrect.x + brickrect.width) 
							{
								BallXdir= -BallXdir;
							}
							else 
							{
								BallYdir = -BallYdir;
							}
							break A;
							
						}
					}
				}
			}
			
			BallposX +=BallXdir;
			BallposY +=BallYdir;
			if(BallposX<0)
			{
				BallXdir = -BallXdir;
			}
			if(BallposY<0)
			{
				BallYdir = -BallYdir;
			}
			if(BallposX>670)
			{
				BallXdir = -BallXdir;
			}
		}
		repaint();
		
	}
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) 
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		{
			if(playerX>=600)
			{
				playerX = 600;
			}
			else 
			{
				moveRight();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) 
		{
			if(playerX<10)
			{
				playerX = 10;
			}
			else 
			{
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) 
		{
			if(!play)
			{
				play = true;
				BallposX = 120;
				BallposY = 350;
				BallXdir = -1;
				BallYdir = -2;
				playerX = 310;
				score = 0;
				totalbricks = 21;
				map = new Bricks(3,7);
				repaint();
			}
		}
		
		}
	public void moveRight() 
	{
		play = true;
		playerX+=20;
	}
	public void moveLeft() 
	{
		play = true;
		playerX-=20;	
	}
}
	
