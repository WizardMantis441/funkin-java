package com.wizard.funkin;

import java.awt.Graphics;
import com.wizard.backend.*;
// import org.json.simple.parser.*;
import com.wizard.backend.rendering.Sprite;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    public void create() {
       super.create();
       System.out.println("playstate loaded");

       cpuStrums = new StrumLine(0.25, true, 4);
       playerStrums = new StrumLine(0.75, false, 4);
        
        cpuStrums.makeNote(0, 1500.0);
    //    testNote = new Note(cpuStrums.strums[0], 1500);
       
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