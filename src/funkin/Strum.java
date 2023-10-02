package src.funkin;

import javax.swing.JFrame;

import src.backend.Sprite;

public class Strum {
   final static String[] strums = {"left", "down", "up", "right"};

   private int id = 0;

   public Strum(JFrame window, int identification, int x, int y) {
      this.id = identification;

      Sprite strum = new Sprite(x, y, "images/strums/" + strums[id] + ".png");
      strum.setScale(0.7, 0.7);
      window.add(strum);
   }
}