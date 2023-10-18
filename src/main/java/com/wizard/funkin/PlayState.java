package com.wizard.funkin;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.wizard.backend.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PlayState extends State {
    String song = "tutorial";

    StrumLine cpuStrums;
    StrumLine playerStrums;

    public void create() {
        super.create();
        System.out.println("playstate loaded");

        cpuStrums = new StrumLine(0.25, true, 4);
        playerStrums = new StrumLine(0.75, false, 4);

        JSONObject chart = null;

        try {
            chart = (JSONObject) new JSONParser().parse(new FileReader(Paths.songChart(song)));
        } catch (FileNotFoundException e) { // bro java stinks what IS THISSS
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JSONArray strumLines = (JSONArray) chart.get("strumLines");
        for (int i = 0; i < strumLines.size(); i++) {
            JSONObject strumLine = (JSONObject) strumLines.get(i);

            JSONArray notes = (JSONArray) strumLine.get("notes");
            for (int n = 0; n < notes.size(); n++) {
                System.out.println("new note");

                JSONObject note = (JSONObject) notes.get(n);

                Long longID = (long) note.get("id");
                int newID = longID.intValue();
                
                double newTime = 0.0;
                Object timeValue = note.get("time");
                if (timeValue instanceof Long) { // stupid way of making sure that the types are correct to cast properly
                    Long longTime = (Long) timeValue;
                    newTime = longTime.doubleValue();
                } else if (timeValue instanceof Double) {
                    Double doubleTime = (Double) timeValue;
                    newTime = doubleTime;
                } else {
                    System.err.println("tf you got in the json??? how?????");
                }
                
                if (i == 0)
                    cpuStrums.makeNote(newID, newTime);
                else if (i == 1)
                    playerStrums.makeNote(newID, newTime);
            }
        }

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