package blockCombat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class HealthBox {
	
	public Rectangle healthBox;
	private Game game;
	private Map map;
	public double x, y;
	
	public HealthBox(double x, double y, Map map, Game game) {
		this.x = x;
		this.y = y;
		this.map = map;
		this.game = game;
	}
	
	public void render(Graphics2D g) {
		healthBox = new Rectangle((int)x, (int)y, 35, 35);
		g.setColor(Color.RED);
		g.fill(healthBox);
		g.setColor(Color.YELLOW);
		g.setStroke(new BasicStroke(2));
		g.draw(new Rectangle(healthBox.x, healthBox.y, 34, 34));
		
		if(game.player.bounds != null && game.player.bounds.intersects(healthBox)) {
			if(game.player.hp < 100) game.player.hp += 10;
			map.healthBox = null;
		}
		if(game.enemy.bounds != null && game.enemy.bounds.intersects(healthBox)) {
			if(game.enemy.hp < 100) game.enemy.hp += 10;
			map.healthBox = null;
		}
	}
	
}
