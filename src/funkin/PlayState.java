package src.funkin;

import src.backend.*;

public class PlayState extends State {
    public void create() {
        super.create();

        // StrumLine cpuStrums = new StrumLine(window, 0.25, true);
        // StrumLine playerStrums = new StrumLine(window, 0.75, false);

        // Sprite sprite = new Sprite(100, 100, "images/strums/left.png");
        // sprite.setScale(0.7, 0.7);
        // add(sprite);
        
        Sprite spriteBetter = new Sprite(100, 100, "images/note_assets.png");
        spriteBetter.setScale(0.7, 0.7);
        // spriteBetter.addAnim("it", new Animation("arrow static instance 2"));
        // spriteBetter.playAnim("it");
        add(spriteBetter);

        // XmlParser.parse("images/note_assets.xml");
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}