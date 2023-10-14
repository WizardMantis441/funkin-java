package com.wizard.funkin;

import com.wizard.backend.Game;

import java.util.ArrayList;
import java.util.List;

// import com.wizard.backend.*;

public class StrumLine {
	public boolean cpu;
	public Strum[] strums;
   
   public List<Note> notes = new ArrayList<Note>();

	public StrumLine(double position, boolean cpu, int keyAmount) {
		this.cpu = cpu;

		strums = new Strum[keyAmount];
		for(int i = 0; i < keyAmount; i++)
			Game.state.add(strums[i] = new Strum(this, i, (int) (1280 * position) + ((i - (keyAmount/2)) * 112), 100));

	}
   
   // public void addNote(Note note) {
   //    notes.add(note);
   // }
   
   public void update(double elapsed) { // idk if this is ran lol
      // super.update(elapsed);
      
        for (int i = 0; i < notes.size(); i++) {
            Note n = notes.get(i);
            n.update(elapsed);
        }
   }
}