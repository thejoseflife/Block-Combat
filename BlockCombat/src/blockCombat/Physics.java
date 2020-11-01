package blockCombat;

import java.awt.Rectangle;

import blockCombat.Game.STATE;

public class Physics {

	private Player player;
	private Enemy enemy;
	private Game game;
	
	public static final float SPEED = 2.0f;
	
	
	public Physics(Player player, Enemy enemy, Game game) {
		this.player = player;
		this.enemy = enemy;
		this.game = game;
	}
	
	public void tick() {
		if(game.state == STATE.GAME) {
			playerCollision();
			playerPhysics(player);
			playerPhysics(enemy);
		}
	}
	
	public void playerPhysics(Entity entity) {
		if(entity.jumping) entity.velY += Game.GRAVITY;
		
		if(entity.x <= 0) {
			entity.velX = 0;
			entity.x = 0;
			entity.knockback = false;
		}
		if(entity.x >= Game.WIDTH - 56) {
			entity.velX = 0;
			entity.x = Game.WIDTH - 56;
			entity.knockback = false;
		}
		
		if(entity.knockback) {
			if(entity.velX > 0) {
				entity.velX -= Game.GRAVITY;
				if(entity.velX <= 0) {
					entity.knockback = false;
					entity.velX = 0;
				}
			} else if(entity.velX < 0) {
				entity.velX += Game.GRAVITY;
				if(entity.velX >= 0) {
					entity.knockback = false;
					entity.velX = 0;
				}
			}
		}
		
		platformCollision(entity);
	}
	
	
	public void platformCollision(Entity entity) {
		for(Platform p: game.map.objects) {
			if(p.platform != null) {
				if(entity.y <= p.platform.y && new Rectangle((int)entity.x, (int)entity.y - 2 + entity.addY + entity.height, 50, 2).intersects(p.platform)) {
					entity.jumping = false;
					entity.velY = 0;
					entity.y = p.platform.y - 80;
					entity.standingOn = p;
				} else if(p == entity.standingOn && entity.standingOn.platform != null && !new Rectangle((int)entity.x + 1, (int)entity.y + 81, 48, 1).intersects(entity.standingOn.platform)) {
						entity.standingOn = null;
						entity.jumping = true;
				}
				
				if(new Rectangle((int)entity.x, (int)entity.y + entity.addY, 52, entity.height).intersects(p.platform) && entity.x + 49 < p.platform.x) {
					entity.velX = 0;
					entity.x = p.platform.x - 51;
					entity.knockback = false;
				}
				if(new Rectangle((int)entity.x, (int)entity.y + entity.addY, 52, entity.height).intersects(p.platform) && entity.x + 1 > p.platform.x + p.platform.width) {
					entity.velX = 0;
					entity.x = p.platform.x + p.platform.width + 1;
					entity.knockback = false;
				}
				if(new Rectangle((int)entity.x, (int)entity.y, 50, 40).intersects(p.platform) && entity.crouching) {
					entity.canStand = false;
					entity.underneath = p;
				} else if(entity.underneath == p && entity.underneath.platform != null && entity.crouching && !entity.canStand) {
					entity.canStand = true;
					entity.underneath = null;
				}
				if(entity.jumping && new Rectangle((int)entity.x, (int)entity.y, 50, 2).intersects(p.platform) && entity.velY < 0) {
					entity.velY = -entity.velY;
				}
				
			}
		}
	}
	
	public void playerCollision() {
		if(player.bounds != null && enemy.bounds != null) {
			if(player.bounds.intersects(enemy.bounds) && player.x < enemy.x) {
				player.knockback(0);
				enemy.knockback(1);
			} else if(player.bounds.intersects(enemy.bounds) && player.x > enemy.x) {
				player.knockback(1);
				enemy.knockback(0);
			}
		}
	}
	
}
