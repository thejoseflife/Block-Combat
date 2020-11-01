package blockCombat;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;
	
	private Thread thread;
	private boolean running = false;
	
	public static final String TITLE = "TestGame";
	public static final int WIDTH = 1000, HEIGHT = 700;
	public static final double GRAVITY = 0.009;
	
	public Player player;
	public Enemy enemy;
	public Physics physics;
	public Menu menu;
	public Selection selection;
	public Map map;
	
	public enum STATE {
		MENU,
		SELECTION,
		GAME
	}
	public STATE state = STATE.MENU;
	
	public void init() {
		addKeyListener(this);
		requestFocus();
		
		map = new Map(1, this);
		player = new Player(this);
		enemy = new Enemy(this);
		physics = new Physics(player, enemy, this);
		menu = new Menu(this);
		selection = new Selection(this, player, enemy);
	}
	
	public void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init();
		while(running) {
			tick();
			render();
		}
		stop();
	}
	
	public void tick() {
		if(state == STATE.GAME) {
			player.tick();
			map.tick();
			enemy.tick();
			physics.tick();
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics2D g = (Graphics2D)bs.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(state == STATE.MENU) {
			menu.render(g);
		} else if(state == STATE.SELECTION) {
			selection.render(g);
		} else if(state == STATE.GAME) {
			map.renderMap(g);
			player.render(g);
			enemy.render(g);
			g.setColor(Color.WHITE);
		}
		
		g.dispose();
		bs.show();
		
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("BlockCombat");
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		frame.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		Game game = new Game();
		frame.add(game);
		frame.setVisible(true);
		game.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}

}
