import java.io.IOException;

import javax.swing.*;

import org.xml.sax.SAXException;

import src.backend.*;
import src.funkin.PlayState;

public class Main {
   public static void main(String[] args) throws SAXException, IOException {
      Game.window = new JFrame("Friday Night Funkin' - Java Edition");
      Game.window.setLayout(null);
      Game.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Game.window.setResizable(false);
      Game.window.setSize(1280, 720);
      Game.window.setVisible(true);

      Game.state = new PlayState();
      Game.state.create();
      
      while (true) {
         long curTime = System.nanoTime();
         update((curTime - lastTime) / 1000000.0);
         lastTime = curTime;
      }
   }

   public static void update(double elapsed) {
      if (Game.state == null) return; // l bozo
      Game.state.update(elapsed);
   }

   public static long lastTime = 0;
}