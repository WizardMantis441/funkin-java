package src.funkin;

import javax.swing.*;

import src.backend.*;

public class StrumLine {
    public Strum left;
    public Strum down;
    public Strum up;
    public Strum right;

    public StrumLine(double position, boolean cpu) {
        left = new Strum(0, (int) (1280 * position) - 224, 100);
        Game.window.add(left);

        down = new Strum(1, (int) (1280 * position) - 112, 100);
        Game.window.add(down);
        
        up = new Strum(2, (int) (1280 * position), 100);
        Game.window.add(up);
        
        right = new Strum(3, (int) (1280 * position) + 112, 100);
        Game.window.add(right);

        left.x = 50 + 0 * 300;
        left.y = 100 + 200 * position;
        
        down.x = 50 + 1 * 300;
        down.y = 100 + 200 * position;

        up.x = 50 + 2 * 300;
        up.y = 100 + 200 * position;

        right.x = 50 + 3 * 300;
        right.y = 100 + 200 * position;
    }
}