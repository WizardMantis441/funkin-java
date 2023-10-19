package com.wizard.funkin;

import com.wizard.backend.Paths;
import com.wizard.backend.Keys;
import com.wizard.backend.rendering.Sprite;

public class Strum extends Sprite {
   final static String[] strums = {"left", "down", "up", "right"};

   public int id = 0;
   public String key;

   public StrumLine strumLine;

   public Strum(StrumLine strumLine, int id, int x, int y) {
      super(x, y, Paths.image("note_assets"));
      this.id = id;
      this.strumLine = strumLine;

      setScale(0.7, 0.7);
      addAnim("static", "arrow static instance " + (id+1), 24, true);
      addAnim("press", strums[id] + " press instance 1", 24, false);
      addAnim("confirm", strums[id] + " confirm instance 1", 24, false);
      playAnim("static");
   }

   public void playAnim(String animName, boolean force) {
      super.playAnim(animName, force);

      switch (getAnimName()) {
         case "press":
            setOffset(2.0, 2.0);
            break;

         case "confirm":
            setOffset(-25.0, -25.0);
            break;

         default:
            setOffset(0.0, 0.0);
            break;
      }
   }
}