package src.backend;

    // WRITTEN BY Ne_Eo, HE IS TOO SMART I CANNOT CLAIM ANY OF THIS ;-;
	// this code sucks, i have no clue how to do key inputs in java
	// please fix this pleaseeeeeee

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

public class Keys {
	private static final int PRESSED = 1;
	private static final int RELEASED = 0;

	public static volatile int dPressed = RELEASED;
	public static volatile boolean _dPressed = false;
	public static volatile int fPressed = RELEASED;
	public static volatile boolean _fPressed = false;
	public static volatile int jPressed = RELEASED;
	public static volatile boolean _jPressed = false;
	public static volatile int kPressed = RELEASED;
	public static volatile boolean _kPressed = false;
	public static boolean pressed_D() { synchronized (Keys.class) { return dPressed >= PRESSED; } }
	public static boolean pressed_F() { synchronized (Keys.class) { return fPressed >= PRESSED; } }
	public static boolean pressed_J() { synchronized (Keys.class) { return jPressed >= PRESSED; } }
	public static boolean pressed_K() { synchronized (Keys.class) { return kPressed >= PRESSED; } }
	public static boolean justPressed_D() { synchronized (Keys.class) { return dPressed == PRESSED || dPressed == PRESSED + 1; } }
	public static boolean justPressed_F() { synchronized (Keys.class) { return fPressed == PRESSED || fPressed == PRESSED + 1; } }
	public static boolean justPressed_J() { synchronized (Keys.class) { return jPressed == PRESSED || jPressed == PRESSED + 1; } }
	public static boolean justPressed_K() { synchronized (Keys.class) { return kPressed == PRESSED || kPressed == PRESSED + 1; } }

	public static void update() {
		synchronized (Keys.class) {
			if (_dPressed) dPressed++;
			if (_fPressed) fPressed++;
			if (_jPressed) jPressed++;
			if (_kPressed) kPressed++;
		}
	}

	private static void doAction(KeyEvent event, int value) {
		switch(event.getKeyCode()) {
			case KeyEvent.VK_D:
				if((value == PRESSED && !_dPressed) || value != PRESSED)
					dPressed = value;
				break;
			case KeyEvent.VK_F:
				if((value == PRESSED && !_fPressed) || value != PRESSED)
					fPressed = value;
				break;
			case KeyEvent.VK_J:
				if((value == PRESSED && !_jPressed) || value != PRESSED)
					jPressed = value;
				break;
			case KeyEvent.VK_K:
				if((value == PRESSED && !_kPressed) || value != PRESSED)
					kPressed = value;
				break;
		}

		_dPressed = dPressed != RELEASED;
		_fPressed = fPressed != RELEASED;
		_jPressed = jPressed != RELEASED;
		_kPressed = kPressed != RELEASED;
	}

	public static void keyDown(KeyEvent e) {
		//System.out.println("Key Down " + e.getKeyCode());
		doAction(e, PRESSED);
	}
	public static void keyReleased(KeyEvent e) {
		//System.out.println("Key Up " + e.getKeyCode());
		doAction(e, RELEASED);
	}

	public static void init() {
		KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

			@Override
			public boolean dispatchKeyEvent(KeyEvent ke) {
				synchronized (Keys.class) {
					switch (ke.getID()) {
						case KeyEvent.KEY_PRESSED:
							keyDown(ke);
							break;

						case KeyEvent.KEY_RELEASED:
							keyReleased(ke);
							break;
					}
					return false;
				}
			}
		});
	}
}