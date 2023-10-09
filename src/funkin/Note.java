package src.funkin;

import src.backend.Conductor;
import src.backend.rendering.Sprite;

public class Note extends Sprite {
    public double time;

    public Strum strum;
    public String[] types = {"purple", "blue", "green", "red"};

    public Note(Strum strum, double time) {
        super(9999, 9999, "assets/images/note_assets.png");

        this.strum = strum;
        this.time = time;

        setScale(0.7, 0.7);
        addAnim("note", types[strum.id] + " instance 1", 24, false);

        playAnim("note");
    }

    public void update(double elapsed) {
        super.update(elapsed);
        x = strum.x;
        y = strum.y - (0.45 * (Conductor.songPosition - time) * 2.5);
    }
}