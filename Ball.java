package Pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x,y;
	public int width,height;
	public double dx,dy;
	public double speed =1.7;
	
	public Ball(double x, double y) {
		super();
		this.x = x;
		this.y = y;
		this.width=4;
		this.height=4;
		int angle = new Random().nextInt(120-45)+46;
		this.dx = Math.cos(Math.toRadians(angle));
		this.dy = Math.sin(Math.toRadians(angle));
	}
	public void tick() {
		x += dx*speed;
		y += dy*speed;
		
		if(x+(dx*speed)+width >= Game.WIDTH) {
			 dx *= -1;
		 }else if (x+(dx*speed) < 0) {
			 dx*=-1;  
		 }
		if(y >= Game.HEIGHT) {
			//enemy Score 1
			new Game();
		 }else if (y< 0) {  
			//player score
			 new Game();
		 }
		//Collision Rectangles;
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)),width,height);
		Rectangle boundPlayer = new Rectangle(Game.player.x,Game.player.y,Game.player.width,Game.player.height);
		Rectangle boundEnemy = new Rectangle((int)Game.enemy.x,(int)Game.enemy.y,Game.enemy.width,Game.enemy.height);
	
		if(bounds.intersects(boundPlayer)) {
			int angle = new Random().nextInt(120-45)+46;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy>0) {
				dy*=-1;
			}
		}else if(bounds.intersects(boundEnemy)){
			int angle = new Random().nextInt(120-45)+46;
			this.dx = Math.cos(Math.toRadians(angle));
			this.dy = Math.sin(Math.toRadians(angle));
			if(dy<0) {
				dy*=-1;
			}
		}
		
		
		
	
	
	}  
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect((int)x,(int)y,width,height);
	}

}
