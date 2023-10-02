package src.backend;

public class Animation {
    public String animPrefix;
    public int fps = 24;
    public boolean loop = false;
    public int[] indices = {};

    public Frame[] frames;
    
    public Animation(String animationPrefix) {
        animPrefix = animationPrefix;
    }

    // https://docs.oracle.com/javase/tutorial/2d/advanced/clipping.html
	// <SubTexture name="up press instance 10003" x="166" y="858" width="154" height="150" frameX="-0" frameY="-1" frameWidth="154" frameHeight="151"/>
}