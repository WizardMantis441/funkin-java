package com.wizard.funkin;

import com.wizard.backend.Conductor;
import com.wizard.backend.Game;
import com.wizard.backend.Keys;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

// import com.wizard.backend.*;

public class StrumLine {
	public boolean cpu;
	public Strum[] strums;
   
   public List<Note> notes = new ArrayList<Note>();
   public List<UnspawnNote> unspawnedNotes = new ArrayList<UnspawnNote>();

	public StrumLine(double position, boolean cpu, int keyAmount) {
		this.cpu = cpu;

		strums = new Strum[keyAmount];
		for (int i = 0; i < keyAmount; i++)
			Game.state.add(strums[i] = new Strum(this, i, (int) (1280 * position) + ((i - (keyAmount/2)) * 112), 100));

	}
   
   public void makeNote(double hitTime, int direction, double length, String type) {
      unspawnedNotes.add(new UnspawnNote(hitTime, direction, length, type));
   }
   
   public void update(double elapsed) {
      while (!unspawnedNotes.isEmpty() && unspawnedNotes.get(0).hitTime < Conductor.songPosition + 1500) {
         UnspawnNote data = unspawnedNotes.get(0);

         Note note = new Note(strums[data.direction], data.hitTime);
         notes.add(note);

         unspawnedNotes.remove(data);
      }

      for (int i = 0; i < notes.size(); i++) {
         Note n = notes.get(i);
         if (n.strum.strumLine.cpu && n.time <= Conductor.songPosition) {
            n.destroy();
            notes.remove(n);
         }
         if (n.time < Conductor.songPosition - 500) {
            // we do missing logic here too
            n.destroy();
            notes.remove(n);
         }
         n.update(elapsed);
      }

      // don't do input if we're cpu
      if (cpu) return;

      // literally almost the entire input system
      boolean[] pInputs = {
         Keys.justPressed("D"),
         Keys.justPressed("F"),
         Keys.justPressed("J"),
         Keys.justPressed("K")
      };
      boolean[] rInputs = {
         Keys.justReleased("D"),
         Keys.justReleased("F"),
         Keys.justReleased("J"),
         Keys.justReleased("K")
      };

      for (int keyID = 0; keyID < pInputs.length; keyID++) {
         if (!pInputs[keyID])
            continue;

         int keyID_again = keyID; // workaround for java being actually an idiot
         boolean hasHitNote = false;

         List<Note> possibleNotes = notes.stream()
            .filter(n -> n != null)
            .filter(n -> !n.strum.strumLine.cpu)
            .filter(n -> n.strum.id == keyID_again)
            .filter(n -> Math.abs(Conductor.songPosition - n.time) < 100) // 166 is the fnf one but i'm not doing ratings, leaving that for later
            .collect(Collectors.toList());

         if (possibleNotes.size() > 0) {
            hasHitNote = true;
            Collections.sort(possibleNotes, Comparator.comparingDouble(n -> n.time));
            
            Note n = possibleNotes.get(0);
            n.destroy();
            notes.remove(n);
         }

         strums[keyID].playAnim(hasHitNote ? "confirm" : "press", true);
      }
      
      for (int keyID = 0; keyID < rInputs.length; keyID++) {
         if (!rInputs[keyID])
            continue;

         strums[keyID].playAnim("static");
      }
   }

   public void draw() {
      for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            n.draw(Game.g);
        }
   }
}