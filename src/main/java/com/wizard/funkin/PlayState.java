package com.wizard.funkin;

import com.wizard.backend.*;

// import libs.jackson-jr-2-16.jr-objects.src.main.java.com.fasterxml.jackson.jr.ob.JSON;

public class PlayState extends State {
    StrumLine cpuStrums;
    StrumLine playerStrums;

    Note test0;
    // Note test4;
    // Note test5;
    // Note test6;
    // Note test7;

    public void create() {
        super.create();

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);
        
        // JSON chart = new JSONReader or something

        // Conductor.songSpeed = 1;
        
       test0 = new Note(cpuStrums.strums[0], 1500);
       System.out.println("woah new note");
       
       // test1 = new Note(cpuStrums.strums[1], 1500);
       // test2 = new Note(cpuStrums.strums[2], 1500);
       // test3 = new Note(cpuStrums.strums[3], 1500);
       // test4 = new Note(playerStrums.strums[0], 1500);
       // test5 = new Note(playerStrums.strums[1], 1500);
       // test6 = new Note(playerStrums.strums[2], 1500);
       // test7 = new Note(playerStrums.strums[3], 1500);
    }

    public void update(double elapsed) {
        super.update(elapsed);
    }
}