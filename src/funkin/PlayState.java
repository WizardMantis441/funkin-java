package src.funkin;

import src.backend.*;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    Note test;

    public void create() {
        super.create();

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);

        Conductor.songSpeed = 0.5;
        test = new Note(playerStrums.strums[3], 1500);
        add(test);
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}