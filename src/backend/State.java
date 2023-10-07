package src.backend;

import java.util.ArrayList;
import java.util.List;

import src.backend.rendering.Sprite;

import java.awt.Graphics;

public class State {
    public List<Sprite> sprites = new ArrayList<Sprite>();

    public void add(Sprite spr) {
        if (sprites.contains(spr))
            return;

        sprites.add(spr);
        Game.window.add(spr);
    }

    public void remove(Sprite spr) {
        if (sprites.contains(spr))
            return;

        sprites.remove(spr);
        Game.window.remove(spr);
    }

    public void create() {
        // setup function
    }

    public void update(double elapsed) {

        // sprite rendering + update function
        for (int i = 0; i < sprites.size(); i++) {
            Sprite spr = sprites.get(i);
            spr.update(elapsed);
        }
    }

    public void draw(Graphics g) {
        for (int i = 0; i < sprites.size(); i++) {
            Sprite spr = sprites.get(i);
            spr.draw(g);
        }
    }
}