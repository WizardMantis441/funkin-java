package src.funkin;

import src.backend.rendering.Sprite;

public class Strum extends Sprite {
   final static String[] strums = {"left", "down", "up", "right"};

   private int id = 0;

   public Strum(int id, int x, int y) {
      super(x, y, "images/note_assets.png");
      this.id = id;

      setScale(0.7, 0.7);
      addAnim("static", "arrow static instance " + (id+1)); // no wait this fails
      addAnim("confirm", strums[id] + " confirm instance 1");
      addAnim("press", strums[id] + " press instance 1");
      playAnim("static");
   }

   public void update(double elapsed) {
      
   }
}