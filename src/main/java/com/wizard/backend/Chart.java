package com.wizard.backend;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.wizard.funkin.PlayState;

public class Chart {
    public static void parse(String song) {
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
                } else
                    System.err.println("tf you got in the json??? how?????");

                double newLength = 0.0;
                Object lengthValue = note.get("sLen");
                if (lengthValue instanceof Long) { // stupid way of making sure that the types are correct to cast properly
                    Long longTime = (Long) lengthValue;
                    newLength = longTime.doubleValue();
                } else if (lengthValue instanceof Double) {
                    Double doubleLen = (Double) lengthValue;
                    newLength = doubleLen;
                } else
                    System.err.println("tf you got in the json??? how?????");
                
                if (i == 0)
                    PlayState.cpuStrums.makeNote(newTime, newID, newLength, "Default");
                else if (i == 1)
                    PlayState.playerStrums.makeNote(newTime, newID, newLength, "Default");
            }
        }

        Collections.sort(PlayState.cpuStrums.unspawnedNotes, Comparator.comparingDouble(n -> n.hitTime));
        Collections.sort(PlayState.playerStrums.unspawnedNotes, Comparator.comparingDouble(n -> n.hitTime));
    }
}
