package blockCombat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import blockCombat.Game.STATE;

public class Menu implements MouseListener {

	public RoundRectangle2D start = new RoundRectangle2D.Double(Game.WIDTH / 2 - 250, Game.HEIGHT / 2, 500, 100, 70, 70);
	public RoundRectangle2D quit = new RoundRectangle2D.Double(Game.WIDTH / 2 - 250, Game.HEIGHT / 2 + 150, 500, 100, 70, 70);
	
	private Game game;
	
	public Menu(Game game) {
		game.addMouseListener(this);
		this.game = game;
	}
	
	public void render(Graphics2D g) {
		g.setColor(Color.WHITE);
		g.fill(start);
		g.fill(quit);
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("arial", Font.BOLD, 60));
		g.drawString("START", Game.WIDTH / 2 - 95, Game.HEIGHT / 2 + 70);
		g.drawString("QUIT", Game.WIDTH / 2 - 71, Game.HEIGHT / 2 + 220);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(game.state == STATE.MENU) {
			if(start.contains(e.getPoint())) game.state = STATE.SELECTION;
			if(quit.contains(e.getPoint())) System.exit(0);
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
