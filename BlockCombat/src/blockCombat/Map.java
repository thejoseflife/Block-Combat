package blockCombat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

	private int map;
	private Game game;
	public HealthBox healthBox;
	private int timer = 4000;
	private int spawnTimer = 4000;
	public List<Platform> objects = new ArrayList<Platform>();
	
	public Map(int map, Game game) {
		this.map = map;
		this.game = game;
		initializeMaps();
	}
	
	public void tick() {
		spawnTimer--;
		if(healthBox == null && spawnTimer <= 0) {
			Random r = new Random();
			healthBox = new HealthBox(r.nextInt(Game.WIDTH - 200) + 100, r.nextInt(Game.HEIGHT - 300) + 200, this, game);
			timer = 4000;
			spawnTimer = 4000;
		}
		for(Platform p: objects) {
			if(healthBox != null && healthBox.healthBox != null && p.platform != null && healthBox.healthBox.intersects(p.platform)) {
				healthBox = null;
				
			}
		}
		if(healthBox != null) {
			if(timer > 0) timer--;
			if(timer <= 0) {
				healthBox.y += 0.06;
			}
		}
	}
	
	public void initializeMaps() {
		objects.add(new Platform(0, Game.HEIGHT - 120, Game.WIDTH, 120, Color.WHITE));
		if(map == 1) {
			objects.add(new Platform(Game.WIDTH / 2 - 40, Game.HEIGHT - 200, 80, 40, Color.WHITE));
		}
		if(map == 2) {
			objects.add(new Platform(Game.WIDTH / 2 - 110, Game.HEIGHT - 200, 80, 40, Color.GREEN));
			objects.add(new Platform(Game.WIDTH / 2 - 40, Game.HEIGHT - 281, 80, 40, new Color(120, 0, 120)));
			objects.add(new Platform(Game.WIDTH / 2 + 30, Game.HEIGHT - 200, 80, 40, Color.WHITE));
		}
		healthBox = new HealthBox(Game.WIDTH / 2 - 17, 230, this, game);
	}
	
	public void renderMap(Graphics2D g) {
		for(Platform p: objects) p.render(g);
		if(healthBox != null) healthBox.render(g);
		
		if(game.player != null && game.enemy != null) {
			g.setFont(new Font("arial", Font.BOLD, 40));
			if(game.player.hp == 0 && game.enemy.hp > 0) {
				g.setColor(game.enemy.color);
				g.drawString("Player 2 took the dub!", Game.WIDTH / 2 - 210, 200);
			}
			if(game.enemy.hp == 0 && game.player.hp > 0) {
				g.setColor(game.enemy.color);
				g.drawString("Player 1 took the dub!", Game.WIDTH / 2 - 210, 200);
			}
		}
		
	}
	
}
