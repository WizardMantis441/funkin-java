package com.wizard;
import javax.swing.JFrame;
import com.wizard.backend.*;
import com.wizard.funkin.*;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

public class Main {
	public static void main(String[] args) {

		GameCanvas canvas = new GameCanvas(1280, 720);
		Game.window = new JFrame("Friday Night Funkin' - Java Edition");
		Game.window.add(canvas);
		Game.window.pack();
		Game.window.setLayout(null);
		Game.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Game.window.setResizable(false);
		Game.window.setSize(1280, 720);
		Game.window.setVisible(true);

		Game.window.addKeyListener(canvas);
		Game.window.setFocusable(true);
		Game.window.setFocusTraversalKeysEnabled(false);

		Game.window.addWindowFocusListener(new WindowFocusListener() {
			@Override
			public void windowLostFocus(WindowEvent e) {
				canvas.paused = true;
			}

			@Override
			public void windowGainedFocus(WindowEvent e) {
				canvas.paused = false;
			}
		});

		Keys.init();

		Game.state = new PlayState();
		Game.state.create();
		
		// UPDATE FUNCTION

		double lastTime = System.nanoTime();
		while (true) {
			if (canvas.paused) continue;

			long now = System.nanoTime();
			double elapsed = (now - lastTime) / 1000000.0;
			lastTime = now;

			elapsed = elapsed / 1000.0;
			Conductor.update(elapsed);
			canvas.update(elapsed);
			Keys.update();
			canvas.draw();

			try { // bc i don't wanna overrun the loop lmao
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class GameCanvas extends Canvas implements Runnable, KeyListener {
	public boolean paused = false;

	public GameCanvas(int width, int height) {
		setPreferredSize(new Dimension(width, height));
		setMaximumSize(new Dimension(width, height));
		setMinimumSize(new Dimension(width, height));
	}

	@Override
	public void run() {}

	public void keyPressed(KeyEvent e) {} // Keys.keyDown(e);
	public void keyReleased(KeyEvent e) {} // Keys.keyReleased(e);
	public void keyTyped(KeyEvent e) {} // System.out.println("keyTyped");

	public void update(double elapsed) {
		if (Game.state != null)
			Game.state.update(elapsed);
	}

	public void draw() {
		BufferStrategy bufferstrategy = getBufferStrategy();
		if (bufferstrategy == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bufferstrategy.getDrawGraphics();
		Game.g = g;

		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());

		if (Game.state != null)
			Game.state.draw(g);

		g.dispose();
		bufferstrategy.show();
	}
}