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
         n.update(elapsed);
      }

      // literally almost the entire input system
      if (Keys.justPressed_D()) {
         boolean hasHitNote = false;

         System.out.println("hey");
         List<Note> possibleNotes = notes.stream()
            .filter(n -> n != null)
            .filter(n -> !n.strum.strumLine.cpu)
            .filter(n -> n.strum.id == 0)
            .filter(n -> Math.abs(Conductor.songPosition - n.time) < 250)
            .collect(Collectors.toList());

         if (possibleNotes.size() > 0) {
            hasHitNote = true;
            System.out.println("heyo!!");
            Collections.sort(possibleNotes, Comparator.comparingDouble(note -> note.time));
            Game.window.remove(possibleNotes.get(0));
            possibleNotes.remove(0);
         }

         strums[0].playAnim(hasHitNote ? "confirm" : "press");
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