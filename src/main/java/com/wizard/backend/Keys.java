package com.wizard.backend;

// hey so i'm swordcube hihihiiihii 👋
// i rewrote this shit because it lookedl ike hell

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Keys {
	public static Map<String, Boolean> justPressedKeys = new HashMap<>();
	public static Map<String, Boolean> justReleasedKeys = new HashMap<>();
	public static Map<String, Boolean> pressedKeys = new HashMap<>();

	public static Boolean justPressed(String key) {
		if (justPressedKeys.get(key) == null)
			return false;
		return justPressedKeys.get(key) == true;
	}

	public static Boolean pressed(String key) {
		if (pressedKeys.get(key) == null)
			return false;
		return pressedKeys.get(key) == true;
	}

	public static Boolean justReleased(String key) {
		if (justReleasedKeys.get(key) == null)
			return false;
		return justReleasedKeys.get(key) == true;
	}

	public static Boolean released(String key) {
		return pressed(key) == false; // lol
	}

	// DON'T WORRY ABOUT THESE USE THE FUNCTIONS ABOVE!!!!!
	public static void update() {
		// will do something LATER!!!
		List<String> set = new ArrayList<String>(justPressedKeys.keySet());
		for(int i = 0; i < set.size(); i++) {
			justPressedKeys.put(set.get(i), false);
			justReleasedKeys.put(set.get(i), false);
		}
	}

	private static void keyDown(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyText = KeyEvent.getKeyText(keyCode);
		if(pressedKeys.get(keyText) != null && pressedKeys.get(keyText) == true)
			return;

		justPressedKeys.put(keyText, true);
		pressedKeys.put(keyText, true);
	}
	
	private static void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		String keyText = KeyEvent.getKeyText(keyCode);
		if(pressedKeys.get(keyText) != null && pressedKeys.get(keyText) == false)
			return;
		
		justReleasedKeys.put(keyText, true);
		pressedKeys.put(keyText, false);
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