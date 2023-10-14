package com.wizard.funkin;

import com.wizard.backend.Game;
import com.wizard.backend.Conductor;
import com.wizard.backend.rendering.Sprite;

public class Note extends Sprite {
    public double time;

    public Strum strum;
    public String[] types = {"purple", "blue", "green", "red"};

    public Note(Strum strum, double time) {
        super(10, 10, "src/main/java/assets/images/note_assets.png");
        System.out.println("new note being made rn");

        this.strum = strum;
        this.time = time;

        setScale(0.7, 0.7);
        addAnim("note", types[strum.id] + " instance 1", 24, false);
        playAnim("note");
        System.out.println("erm note MADE?");
        
        Game.window.add(this);
        this.draw(getGraphics());
    }

    public void update(double elapsed) {
        super.update(elapsed);
        this.x = strum.x;
        this.y = strum.y - (Conductor.songPosition - this.time) / 5;
        
        System.out.println("note pos: " + this.x + ", " + this.y);
    }
}