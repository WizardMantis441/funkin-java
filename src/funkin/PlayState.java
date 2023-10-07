package src.funkin;

import src.backend.*;
import src.backend.rendering.Sprite;

public class PlayState extends State {
    public void create() {
        super.create();
        System.out.println("loaded playstate");

        // StrumLine cpuStrums = new StrumLine(window, 0.25, true);
        // StrumLine playerStrums = new StrumLine(window, 0.75, false);

        Sprite sprite = new Sprite(300, 0, "images/note_assets.png");
        sprite.setScale(0.5, 0.5);
        sprite.addAnim("it", "down confirm instance 1");
        sprite.playAnim("it");
        add(sprite);

        System.out.println("made it :)");
    }

    public void update(double elapsed) {
        super.update(elapsed);

        //System.out.println("> D" + Keys.dPressed);
        //System.out.println("> F" + Keys.fPressed);
        //System.out.println("> J" + Keys.jPressed);
        //System.out.println("> K" + Keys.kPressed);
        if(Keys.justPressed_D()) {
            System.out.println("D");
        }
        if(Keys.justPressed_F()) {
            System.out.println("F");
        }
        if(Keys.justPressed_J()) {
            System.out.println("J");
        }
        if(Keys.justPressed_K()) {
            System.out.println("K");
        }
    }
}