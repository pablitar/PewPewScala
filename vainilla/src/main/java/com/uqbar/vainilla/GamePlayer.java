package com.uqbar.vainilla;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import com.uqbar.vainilla.events.EventAdapter;

public class GamePlayer extends Canvas implements Runnable {

	private static final int BACKBUFFER_COUNT = 2;

	private Game game;

	private volatile Thread playerThread;

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	public GamePlayer(Game game) {
		this.setGame(game);
		this.setEventListener(new EventAdapter(game));
		this.setPreferredSize(game.getDisplaySize());
		this.setMinimumSize(game.getDisplaySize());
		this.setMaximumSize(game.getDisplaySize());
		this.setIgnoreRepaint(true);
		this.setFocusTraversalKeysEnabled(false);
		this.setFocusable(true);
		this.hideMouse();
	}

	// ****************************************************************
	// ** QUERIES
	// ****************************************************************

	protected boolean isPaused() {
		return this.getPlayerThread() != Thread.currentThread();
	}

	// ****************************************************************
	// ** OPERATIONS
	// ****************************************************************

	@Override
	public void run() {
		this.createBufferStrategy(BACKBUFFER_COUNT);
		this.setPlayerThread(Thread.currentThread());

		while(!this.isPaused()) {
			this.takeStep();
		}
	}

	public void resume() {
		this.setPlayerThread(new Thread(this, this.getGame().getTitle()));

		this.getPlayerThread().start();
	}

	public void pause() {
		this.setPlayerThread(null);

		this.getGame().pause();
	}

	protected void takeStep() {
		Graphics2D graphics = (Graphics2D) this.getBufferStrategy().getDrawGraphics();
		graphics.clearRect(0, 0, this.getWidth(), this.getHeight());

		this.getGame().takeStep(graphics);

		this.sleep();

		graphics.dispose();
		this.getBufferStrategy().show();
	}

	public void setEventListener(EventAdapter eventListener) {
		this.addMouseListener(eventListener);
		this.addMouseMotionListener(eventListener);
		this.addKeyListener(eventListener);
	}

	protected void sleep() {
		try {
			Thread.sleep(0, 1);
		}
		catch(InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	protected void hideMouse() {
		Image image = this.createImage(new MemoryImageSource(16, 16, new int[16 * 16], 0, 16));
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(image, new Point(0, 0), ""));
	}

	// ****************************************************************
	// ** ACCESSORS
	// ****************************************************************

	protected Game getGame() {
		return this.game;
	}

	protected void setGame(Game game) {
		this.game = game;
	}

	protected Thread getPlayerThread() {
		return this.playerThread;
	}

	protected void setPlayerThread(Thread playerThread) {
		this.playerThread = playerThread;
	}
}