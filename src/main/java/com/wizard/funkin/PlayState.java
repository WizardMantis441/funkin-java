package com.wizard.funkin;

import com.wizard.backend.*;
import org.json.simple.parser.*;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    Note testNote;

    public void create() {
       super.create();
       System.out.println("playstate loaded");

       cpuStrums = new StrumLine(0.25, true, 4);
       playerStrums = new StrumLine(0.75, false, 4);
        
       testNote = new Note(cpuStrums.strums[0], 1500);
       
       System.out.println("playstate finished");
    }

    public void update(double elapsed) {
        super.update(elapsed);
       // System.out.println(Conductor.songPosition);
       testNote.update(elapsed);
    }
}