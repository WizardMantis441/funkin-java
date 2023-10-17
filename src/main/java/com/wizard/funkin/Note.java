package com.wizard.funkin;

import com.wizard.backend.Paths;
import com.wizard.backend.Game;
import com.wizard.backend.Conductor;
import com.wizard.backend.rendering.Sprite;

public class Note extends Sprite {
    public double time;

    public Strum strum;
    public String[] types = {"purple", "blue", "green", "red"};

    public Note(Strum strum, double time) {
        super(0, 0, Paths.image("note_assets"));

        this.strum = strum;
        this.time = time;

        setScale(0.7, 0.7);
        addAnim("note", types[strum.id] + " instance 1", 24, false);
        playAnim("note");
        
        Game.window.add(this);
    }

    public void update(double elapsed) {
        super.update(elapsed);
        setPosition(strum.x, strum.y + (time - Conductor.songPosition));
    }
}