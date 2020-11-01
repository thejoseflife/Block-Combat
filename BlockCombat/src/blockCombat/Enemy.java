package blockCombat;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Enemy extends Entity implements KeyListener {
	
	public Enemy(Game game) {
		x = Game.WIDTH - 80;
		direction = 1;
		this.game = game;
		game.addKeyListener(this);
	}
	
	public void tick() {
		x += velX;
		y += velY;
		
		if(canStand && crouching && !keyDown[2]) {
			if(Math.abs(velX) == 0.1) velX *= 5;
			height = 80;
			addY = 0;
			crouching = false;
		}
		
		if(bullet != null) bullet.tick();
		
	}
	
	public void render(Graphics2D g) {
		g.setColor(color);
		bounds = new Rectangle((int)x, (int)y + addY, 50, height);
		g.fill(bounds);
		
		if(bullet != null) bullet.render(g);
		
		g.setColor(color);
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString("Player 2", (int)x - 2, (int)y - 60);
		
		renderHealthBar(g);
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(!knockback) {
			if(key == KeyEvent.VK_RIGHT) {
				if(!crouching) velX = Physics.SPEED;
				else velX = Physics.SPEED * 0.2;
				keyDown[0] = true;
				direction = 1;
			}
			if(key == KeyEvent.VK_LEFT) {
				if(!crouching) velX = -Physics.SPEED;
				else velX = -Physics.SPEED * 0.2;
				keyDown[1] = true;
				direction = 0;
			}
			if(key == KeyEvent.VK_UP && !jumping && canStand) {
				velY = -2;
				jumping = true;
			}
			if(key == KeyEvent.VK_SHIFT && bullet == null) {
				bullet = new Bullet(this, game.player, velX, velY, game);
			}
			if(key == KeyEvent.VK_DOWN && !crouching) {
				if(velX != 0) {
					if(direction == 1) velX = Physics.SPEED * 0.2;
					else velX = -Physics.SPEED * 0.2;
				}
				height = 40;
				addY = 40;
				crouching = true;
				keyDown[2] = true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(!knockback) {
			if(key == KeyEvent.VK_RIGHT) keyDown[0] = false;
			if(key == KeyEvent.VK_LEFT) keyDown[1] = false;
			if(!keyDown[0] && !keyDown[1]) {
				velX = 0;
			}
			if(key == KeyEvent.VK_DOWN && crouching) {
				keyDown[2] = false;
				if(canStand) {
					if(Math.abs(velX) == 0.1) velX *= 5;
					height = 80;
					addY = 0;
					crouching = false;
				}
			}
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
