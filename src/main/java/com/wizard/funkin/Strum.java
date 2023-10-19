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
      addAnim("confirm", strums[id] + " confirm instance 1", 24, false);
      addAnim("press", strums[id] + " press instance 1", 24, false);
      playAnim("static");
   }

   public void update(double elapsed) {
      super.update(elapsed);

      // btw this is temporary just reminding myself
      // boolean pressed = false;
      // switch (id) {
      //    case 0: pressed = Keys.pressed_D(); break;
      //    case 1: pressed = Keys.pressed_F(); break;
      //    case 2: pressed = Keys.pressed_J(); break;
      //    case 3: pressed = Keys.pressed_K(); break;
      // }

      // playAnim((pressed && !strumLine.cpu) ? "press" : "static");
   }
}