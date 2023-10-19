package com.wizard.backend.rendering;

import java.util.ArrayList;
import java.util.List;

public class Animation {
    public String imagePath;
    public String animPrefix;
    public String animName;
    public float fps = 24;
    
    public double offsetX = 0.0;
    public double offsetY = 0.0;

    public List<Frame> frames = new ArrayList<Frame>();
    private float _frameTimer = 0;
    public int curFrame = 0;

    public boolean finished = false;
    public boolean paused = false;
    public boolean looped = false;

    public Animation(String imagePath, String animationPrefix, List<Frame> frames, String animName) {
        this.imagePath = imagePath;
        this.animPrefix = animationPrefix;
        this.frames = frames;
        this.animName = animName;
    }

    public void play() {
        curFrame = 0;
        _frameTimer = 0;
        paused = false;
        finished = false;
    }

    public void update(double elapsed) {
        Float delay = getDelay();

		if (delay == 0 || finished || paused)
			return;

		_frameTimer += elapsed;

		while (_frameTimer > delay && !finished) {
			_frameTimer -= delay;
            if (looped && curFrame == frames.size() - 1)
                curFrame = 0;
            else {
                curFrame++;
                if (curFrame == frames.size()) {
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