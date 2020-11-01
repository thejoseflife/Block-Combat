package blockCombat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {
	
	protected double x, y = Game.HEIGHT - 210;
	protected double velX = 0, velY = 0;
	protected int direction; // 0 is left, 1 is right
	protected int hp = 100;
	protected int height = 80;
	protected int addY = 0;
	protected Rectangle bounds;
	protected Bullet bullet = null;
	protected Color color = Color.WHITE;
	protected Game game;
	protected Platform standingOn;
	protected Platform underneath;
	protected boolean knockback = false;
	protected boolean crouching = false;
	protected boolean jumping = true;
	protected boolean canStand = true;
	protected boolean[] keyDown = {false, false, false};
	
	public void knockback(int direction) {//0 is left, 1 is right
		if(direction == 0) velX = -1.5;
		else velX = 1.5;
		knockback = true;
	}
	
	public double getX() {
		return x;
	}
	
	public void renderHealthBar(Graphics2D g) {
		Color color = Color.GREEN;
		if(hp > 80 && hp <= 90) color = new Color(255 * 1/9, 255 * 8/9, 0);
		if(hp > 70 && hp <= 80) color = new Color(255 * 2/9, 255 * 7/9, 0);
		if(hp > 60 && hp <= 70) color = new Color(255 * 3/9, 255 * 6/9, 0);
		if(hp > 50 && hp <= 60) color = new Color(255 * 4/9, 255 * 5/9, 0);
		if(hp > 40 && hp <= 50) color = new Color(255 * 5/9, 255 * 4/9, 0);
		if(hp > 30 && hp <= 40) color = new Color(255 * 6/9, 255 * 3/9, 0);
		if(hp > 20 && hp <= 30) color = new Color(255 * 7/9, 255 * 2/9, 0);
		if(hp > 10 && hp <= 20) color = new Color(255 * 8/9, 255 * 1/9, 0);
		if(hp <= 10) color = Color.RED;
		
		if(hp < 0) hp = 0;
		
		g.setStroke(new BasicStroke(1));
		g.setColor(color);
		g.fillRect((int)x - 25, (int)y - 30, hp, 10);
		g.setColor(Color.WHITE);
		g.drawRect((int)x - 25, (int)y - 30, 100, 10);
		g.setFont(new Font("arial", Font.BOLD, 15));
		int add = 16;
		if(hp == 100) add = 11;
		if(hp == 0) add = 21;
		g.drawString("" + hp, (int)x + add, (int)y - 37);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
}
