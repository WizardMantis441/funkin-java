package src.funkin;

import src.backend.*;
import src.backend.rendering.Sprite;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    public void create() {
        super.create();
        System.out.println("loaded playstate");

        cpuStrums = new StrumLine(0.25, true);
        playerStrums = new StrumLine(0.75, false);

        // Sprite sprite = new Sprite(300, 0, "images/note_assets.png");
        // sprite.setScale(0.5, 0.5);
        // sprite.addAnim("it", "down confirm instance 1");
        // sprite.playAnim("it");
        // add(sprite);

        System.out.println("made it :)");
    }

    public void update(double elapsed) {
        super.update(elapsed);

        playerStrums.left.playAnim("static");
        playerStrums.up.playAnim("static");
        playerStrums.down.playAnim("static");
        playerStrums.right.playAnim("static");

        if (Keys.pressed_D()) {
            System.out.println("D");
            playerStrums.left.playAnim("press");
        }
        if (Keys.pressed_F()) {
            System.out.println("F");
            playerStrums.down.playAnim("press");
        }
        if (Keys.pressed_J()) {
            System.out.println("J");
            playerStrums.up.playAnim("press");
        }
        if (Keys.pressed_K()) {
            System.out.println("K");
            playerStrums.right.playAnim("press");
        }
    }
}