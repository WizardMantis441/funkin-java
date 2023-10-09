package src.funkin;

import src.backend.*;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    public void create() {
        super.create();
        System.out.println("loaded playstate");

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);

        

        System.out.println("made it :)");
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}