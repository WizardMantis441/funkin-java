import java.io.IOException;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JFrame;
import java.awt.Color;

import org.xml.sax.SAXException;

import src.backend.*;
import src.funkin.PlayState;

import java.awt.image.BufferStrategy;

public class Main {
   public static void main(String[] args) throws SAXException, IOException, InterruptedException {
      new GameCanvas();
   }
}

class Window
{
   public Window (int width, int height, String title, GameCanvas game) {
      game.setPreferredSize(new Dimension (width, height));
      game.setMaximumSize(new Dimension (width, height));
      game.setMinimumSize(new Dimension (width, height));

      JFrame frame = new JFrame (title);
      frame.add (game);
      frame.pack ();
      frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
      frame.setResizable (false);
      frame.setLocationRelativeTo (null);
      frame.setVisible (true);

      Game.window = frame;

      Game.state = new PlayState();
      Game.state.create();
   }
}

class GameCanvas extends Canvas implements Runnable {
   public boolean running = true;
   private Thread thread;

   public GameCanvas() {
      /*Window window = */new Window (1280, 720, "Friday Night Funkin' - Java Edition", this);

      thread = new Thread (this);
      thread.start ();
   }
   
   @Override
   public void run() {
      long lastTime = System.nanoTime();
   
      double delta = 0;
      
      while (running) {
         long now = System.nanoTime();
         delta += (now - lastTime) / 1000000.0;
         lastTime = now;
         boolean shouldRender = true;

         while (delta >= 1f/60f) {
            update(1f/60f);
            delta -= 1f/60f;
            shouldRender = true;
         }

         if(shouldRender) {
            draw();
         }
      }
   }

   public void update(double elapsed) {
      if (Game.state == null) return; // l bozo
      Game.state.update(elapsed);
   }

   public void draw() {

      BufferStrategy bufferstrategy = getBufferStrategy ();

      if (bufferstrategy == null) {
         createBufferStrategy (3);
         return;
      }

      Graphics g = bufferstrategy.getDrawGraphics();

      g.setColor (Color.white);
      g.fillRect (0, 0, getWidth (), getHeight ());

      if (Game.state != null) {
         Game.state.draw(g);
      }

      g.dispose ();
      bufferstrategy.show ();
   }

   public static long lastTime = 0;
}