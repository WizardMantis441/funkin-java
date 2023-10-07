package src.funkin;

import src.backend.*;

public class PlayState extends State {
    public void create() {
        super.create();

        System.out.println("loaded playstate");

        // StrumLine cpuStrums = new StrumLine(window, 0.25, true);
        // StrumLine playerStrums = new StrumLine(window, 0.75, false);

        Sprite sprite = new Sprite(300, 0, "images/note_assets.png");
        sprite.setScale(0.5, 0.5);
        //sprite.addAnim("it", "left press instance 1");
        sprite.addAnim("it", "");
        sprite.playAnim("it");
        add(sprite);

        System.out.println("made it :)");
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}