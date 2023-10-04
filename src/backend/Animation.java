package src.backend;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    public String imagePath;
    public String animPrefix;
    public float fps = 24;

    public List<Frame> frames = new ArrayList<Frame>();
    private float _frameTimer = 0;
    private int numFrames;
    public int curFrame = 0;
    
    public boolean finished = false;
    public boolean paused = false;
    public boolean looped = false;
    
    public Animation(String imagePath, String animationPrefix, List<Frame> frames) {
        this.imagePath = imagePath;
        this.animPrefix = animationPrefix;
        this.frames = frames;

        this.numFrames = frames.size();
    }

    public void play() {
        curFrame = 0;
        _frameTimer = 0;
        paused = false;
        finished = false;
    }

    public void update(double elapsed) {
        Float delay = getDelay();
        //System.out.println(delay);
        //System.out.println(finished);
        //System.out.println(paused);
        //System.out.println(_frameTimer);
        //System.out.println(elapsed);
        //System.out.println(curFrame);
		if (delay == 0 || finished || paused)
			return;

		_frameTimer += elapsed;

		while (_frameTimer > delay && !finished) {
			_frameTimer -= delay;
            if (looped && curFrame == numFrames - 1)
                curFrame = 0;
            else {
                curFrame++;
                if(curFrame == numFrames) {
                    curFrame--;
                    finished = true;
                }
            }
		}
	}

    public float getDelay() {
        return 1 / fps;
    }
}