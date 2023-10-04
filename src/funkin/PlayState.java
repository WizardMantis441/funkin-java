package src.funkin;

import src.backend.*;

public class PlayState extends State {
    private static Sprite spriteBetter = null;

    public void create() {
        super.create();

        // StrumLine cpuStrums = new StrumLine(window, 0.25, true);
        // StrumLine playerStrums = new StrumLine(window, 0.75, false);

        // Sprite sprite = new Sprite(100, 100, "images/strums/left.png");
        // sprite.setScale(0.7, 0.7);
        // add(sprite);
        
        spriteBetter = new Sprite(250, 250, "images/note_assets.png");
        spriteBetter.setScale(0.3, 0.3);
        //spriteBetter.addAnim("it", "left press instance 1");
        spriteBetter.addAnim("it", "");
        spriteBetter.playAnim("it");
        add(spriteBetter);
        System.out.println("i made it :)");

        // XmlParser.parse("images/note_assets.xml");
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}