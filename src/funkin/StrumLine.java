package src.funkin;

import src.backend.*;

public class StrumLine {
	public boolean cpu;

	public Strum left;
	public Strum down;
	public Strum up;
	public Strum right;
	public Strum[] strums;

	public StrumLine(double position, boolean cpu, int keyAmount) {
		this.cpu = cpu;

		strums = new Strum[keyAmount];
		for(int i = 0; i < keyAmount; i++)
			Game.state.add(strums[i] = new Strum(this, i, (int) (1280 * position) + ((i - (keyAmount/2)) * 112), 100));

	}
}