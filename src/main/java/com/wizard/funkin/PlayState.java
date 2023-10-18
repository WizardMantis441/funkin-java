package com.wizard.funkin;

import java.awt.Graphics;
import com.wizard.backend.*;

public class PlayState extends State {
    String song = "tutorial";

    public static StrumLine cpuStrums;
    public static StrumLine playerStrums;

    public void create() {
        super.create();
        System.out.println("playstate loaded");

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);

        Chart.parse(song);

        System.out.println("playstate finished");
    }

    public void update(double elapsed) {
        super.update(elapsed);
        cpuStrums.update(elapsed);
        playerStrums.update(elapsed);
    }

    public void draw(Graphics g) {
        super.draw(g);
        cpuStrums.draw();
        playerStrums.draw();
    }
}