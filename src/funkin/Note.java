package src.funkin;

import src.backend.rendering.Sprite;

public class Note extends Sprite {
    public double time;

    public Strum strum;
    public String[] types = {"purple", "blue", "green", "red"};

    public Note(double x, double y, Strum strum, double time) {
        super(x, y, "images/note_assets.png");

        this.strum = strum;
        this.time = time;

        setScale(0.7, 0.7);
        addAnim("note", types[strum.id] + " instance 1", 24, false);

        playAnim("note");
    }

    public void update() {
        System.out.println("mahysdn");

        this.x = this.strum.x;
        this.y = this.strum.y - (0 - this.time);
    }
}