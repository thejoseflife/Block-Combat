package blockCombat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import blockCombat.Game.STATE;

public class Selection implements MouseListener {

	private Game game;
	private Player player;
	private Enemy enemy;
	public ColorBox[] boxes1 = new ColorBox[8], boxes2 = new ColorBox[8];
	private boolean readyToPlay = false;
	public RoundRectangle2D start = new RoundRectangle2D.Double(Game.WIDTH / 2 - 250, Game.HEIGHT / 2, 500, 100, 70, 70);
	
	public Selection(Game game, Player player, Enemy enemy) {
		this.game = game;
		this.player = player;
		this.enemy = enemy;
		initializeBoxes();
		game.addMouseListener(this);
	}
	
	public void render(Graphics2D g) {
		for(ColorBox b: boxes1) {
			b.render(g);
			
		}
		for(ColorBox b: boxes1) if(b.selected) {
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(4));
			g.draw(new Rectangle(b.x - 2, b.y - 2, 54, 54));
		}
		
		for(ColorBox b: boxes2) {
			b.render(g);
			
		}
		for(ColorBox b: boxes2) if(b.selected) {
			g.setColor(Color.WHITE);
			g.setStroke(new BasicStroke(4));
			g.draw(new Rectangle(b.x - 2, b.y - 2, 54, 54));
		}
	
		if(readyToPlay) {
			g.setColor(Color.WHITE);
			g.fill(start);
			g.setColor(Color.BLUE);
			g.setFont(new Font("arial", Font.BOLD, 60));
			g.drawString("START", Game.WIDTH / 2 - 95, Game.HEIGHT / 2 + 70);
		}
	}
	
	public void initializeBoxes() {
		for(int i = 0; i < boxes1.length; i++) boxes1[i] = new ColorBox(75 + (i * 50), 200, game, this, player);
		boxes1[0].setColor(Color.RED);
		boxes1[1].setColor(Color.ORANGE);
		boxes1[2].setColor(Color.YELLOW);
		boxes1[3].setColor(Color.GREEN);
		boxes1[4].setColor(Color.BLUE);
		boxes1[5].setColor(new Color(120, 0, 120));
		boxes1[6].setColor(Color.PINK);
		boxes1[7].setColor(Color.WHITE);
		
		for(int i = 0; i < boxes2.length; i++) boxes2[i] = new ColorBox((Game.WIDTH - i * 50) - 125, 200, game, this, enemy);
		boxes2[0].setColor(Color.RED);
		boxes2[1].setColor(Color.ORANGE);
		boxes2[2].setColor(Color.YELLOW);
		boxes2[3].setColor(Color.GREEN);
		boxes2[4].setColor(Color.BLUE);
		boxes2[5].setColor(new Color(120, 0, 120));
		boxes2[6].setColor(Color.PINK);
		boxes2[7].setColor(Color.WHITE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(game.state == STATE.SELECTION) {
			if(!readyToPlay) {
				int count = 0;
				for(ColorBox b: boxes1) if(b.selected) count++;
				for(ColorBox b: boxes2) if(b.selected) count++;
				if(count == 2) readyToPlay = true;
			} else
				if(start.contains(e.getPoint())) game.state = STATE.GAME;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
