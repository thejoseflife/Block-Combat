package blockCombat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Platform {
	
	private double x, y;
	private int width, height;
	private Color color;
	public Rectangle platform;
	
	public Platform(double x, double y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void render(Graphics2D g) {
		platform = new Rectangle((int)x, (int)y, width, height);
		g.setColor(color);
		g.fill(platform);
	}
	
}
