package src.funkin;

import src.backend.*;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    public void create() {
        super.create();
        // welcome to the underground
        // how was the fall
        System.out.println("loaded playstate\u0007");

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);

        // Note testNote = new Note(200, 200, cpuStrums.left, 100);

        // Sprite sprite = new Sprite(300, 0, "images/note_assets.png");
        // sprite.setScale(0.5, 0.5);
        // sprite.addAnim("it", "down confirm instance 1", 24, true);
        // sprite.playAnim("it");
        // add(sprite);

        System.out.println("made it :)");
    }

    public void update(double elapsed) {
        super.update(elapsed);

        /*
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
        */
    }
}