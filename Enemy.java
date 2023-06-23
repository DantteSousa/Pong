package Pong;

import java.awt.Color;
import java.awt.Graphics;


public class Enemy {
	public double x,y;
	public int width,height;
	
	public Enemy(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.width=30;
		this.height=5;
	}
	public void tick() {
		x+=(Game.ball.x-x-6)*0.07;
		 
	
		
		
	}
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)x,(int)y,width,height);
	}


}
