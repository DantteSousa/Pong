package Pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable,KeyListener{
	public static int WIDTH = 160;
	public static int HEIGHT = 120;
	public static int SCALE = 3; 
	public BufferedImage  layer = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	
	
	public Game() {
	
		this.setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		this.addKeyListener(this);
		player =new Player(WIDTH/2-15, HEIGHT-5);//-10,which 10 is the size of the player
		enemy = new Enemy(WIDTH/2-15,0);
		ball  = new Ball(WIDTH/2,(HEIGHT/2)-1);
	
	}
	
	public static void main(String[] args) {
		Game game = new Game(); 
		JFrame frame = new  JFrame("Pong"); 
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack(); 
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		new Thread(game).start();
	}
	
	public void tick() {
		
		player.tick();
		enemy.tick();
		ball.tick();
	}
	public void render() 
	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g = bs.getDrawGraphics(); 
		g.drawImage(layer, 0, 0, WIDTH*SCALE,HEIGHT*SCALE,null);
	
		bs.show();
	}  

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			requestFocus();
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	
	
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			player.right = true;
		}
		else if(e.getKeyCode()  == KeyEvent.VK_LEFT) {
			player.left = true;
		}
	}
	

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			player.right = false;
		}
		else if(e.getKeyCode()  == KeyEvent.VK_LEFT) {
			player.left = false;
		}
	}
}
