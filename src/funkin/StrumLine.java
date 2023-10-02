package src.funkin;

import javax.swing.*;

import src.backend.*;

public class StrumLine {
    public StrumLine(JFrame window, double position, boolean cpu) {
        Strum left = new Strum(window, 0, (int) (1280 * position) - 224, 100);
        Strum down = new Strum(window, 1, (int) (1280 * position) - 112, 100);
        Strum up = new Strum(window, 2, (int) (1280 * position), 100);
        Strum right = new Strum(window, 3, (int) (1280 * position) + 112, 100);
    }
}