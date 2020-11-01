package blockCombat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Bullet {

	private Entity attacker;
	private Entity defender;
	private Rectangle bullet;
	private Game game;
	private double x, y;
	private double velX;
	
	public Bullet(Entity attacker, Entity defender, double velX, double velY, Game game) {
		this.attacker = attacker;
		this.defender = defender;
		this.game = game;
		x = attacker.x;
		
		if(attacker.crouching) y = attacker.y + 50;
		else y = attacker.y + 10;
		this.velX = velX * 1.5;
		if((velX == 0 || attacker.crouching) && Math.abs(velX) < 1) {
			if(attacker.direction == 0) this.velX = -1;
			if(attacker.direction == 1) this.velX = 1;
		}
	}
	
	public void tick() {
		x += velX;
		
		if(x > Game.WIDTH || x < -10 || y > Game.HEIGHT || y < -10) attacker.bullet = null;
		for(Platform p: game.map.objects) if(p.platform != null && bullet != null && bullet.intersects(p.platform)) attacker.bullet = null;
		
		collision();
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.ORANGE);
		bullet = new Rectangle((int)x + 20, (int)y, 10, 10);
		g.fill(bullet);
		
	}
	
	public void collision() {
		if(defender != null && bullet != null && defender.bounds.intersects(bullet)) {
			defender.hp -= 10;
			attacker.bullet = null;
		}
	}
	
}
