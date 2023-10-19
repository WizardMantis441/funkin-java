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

	public StrumLine(double position, boolean cpu, int keyAmount) {
		this.cpu = cpu;

		strums = new Strum[keyAmount];
		for (int i = 0; i < keyAmount; i++)
			Game.state.add(strums[i] = new Strum(this, i, (int) (1280 * position) + ((i - (keyAmount/2)) * 112), 100));

	}
   
   public void makeNote(int id, double t) {
      Note n = new Note(strums[(int) id], t);
      notes.add(n);
   }
   
   public void update(double elapsed) { // idk if this is ran lol
      // super.update(elapsed); // help
      
      for (int i = 0; i < notes.size(); i++) {
         Note n = notes.get(i);
         if (n.strum.strumLine.cpu && n.time <= Conductor.songPosition) {
            System.out.println("GET THIS CPU LOOKIN NOTE OUTTA HERE >:(");
            n.destroy();
            notes.remove(n);
         }
         n.update(elapsed);
      }

      // literally almost the entire input system
      boolean[] inputs = {
         Keys.justPressed_D(),
         Keys.justPressed_F(),
         Keys.justPressed_J(),
         Keys.justPressed_K()
      };

      for (int keyID = 0; keyID < inputs.length; keyID++) {
         if (!inputs[keyID])
            continue;

         int keyID_again = keyID; // workaround for java being actually an idiot
         boolean hasHitNote = false;

         System.out.println("erm ghost tap much?");
         List<Note> possibleNotes = notes.stream()
            .filter(n -> n != null)
            .filter(n -> !n.strum.strumLine.cpu)
            .filter(n -> n.strum.id == keyID_again)
            .filter(n -> Math.abs(Conductor.songPosition - n.time) < 100) // 166 is the fnf one but i'm not doing ratings, leaving that for later
            .collect(Collectors.toList());

         if (possibleNotes.size() > 0) {
            hasHitNote = true;
            System.out.println("<> note hit!!!");
            Collections.sort(possibleNotes, Comparator.comparingDouble(n -> n.time));
            
            Note n = possibleNotes.get(0);
            n.destroy();
            notes.remove(n);
         }

         strums[keyID].playAnim(hasHitNote ? "confirm" : "press");
      }
      
      // TODO: here i need a function or if condition to check when a key is released
      // it would look something like this
      /*
       * if (Keys.justReleased_D) {
       *    strums[0].playAnim("static", true);
       * }
       */

   }

   public void draw() {
      for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            n.draw(Game.g);
        }
   }
}