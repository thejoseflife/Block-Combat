package blockCombat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import blockCombat.Game.STATE;

public class ColorBox implements MouseListener {

	private Color color;
	private Rectangle box;
	private Selection selection;
	private Entity entity;
	public boolean selected = false;
	public int x, y;
	private Game game;
	
	public ColorBox(int x, int y, Game game, Selection selection, Entity entity) {
		box = new Rectangle(x, y, 50, 50);
		this.selection = selection;
		this.x = x;
		this.y = y;
		this.entity = entity;
		game.addMouseListener(this);
		this.game = game;
	}
	
	public void setColor(Color color) { this.color = color; }
	
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fill(box);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(box.contains(e.getPoint()) && game.state == STATE.SELECTION) {
			selected = true;
			
			if(contains(selection.boxes1, this)) for(ColorBox b: selection.boxes1) {
				b.selected = false;
				if(b == this) {
					b.selected = true;
					entity.setColor(color);
				}
			}
			if(contains(selection.boxes2, this)) for(ColorBox b: selection.boxes2) {
				b.selected = false;
				if(b == this) {
					b.selected = true;
					entity.setColor(color);
				}
			}
		}
		
	}
	
	public boolean contains(ColorBox[] arr, ColorBox cb) {
		for(ColorBox b: arr) {
			if(b == cb) return true;
		}
		return false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
