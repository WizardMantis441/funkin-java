package src.backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.SAXException;

public class Animation {
    public String animPrefix;
    public int fps = 24;
    public boolean loop = false;
    public int[] indices = {};

    public List<Frame> frames = new ArrayList<Frame>();
    private int curFrame = 0;
    
    public Animation(String imagePath, String animationPrefix) throws NumberFormatException, SAXException, IOException {
        animPrefix = animationPrefix;

        // temp frame test
        int x = Integer.parseInt(XmlParser.parse(imagePath, "x"));
        int y = Integer.parseInt(XmlParser.parse(imagePath, "y"));
        int width = Integer.parseInt(XmlParser.parse(imagePath, "width"));
        int height = Integer.parseInt(XmlParser.parse(imagePath, "height"));
        int frameX = Integer.parseInt(XmlParser.parse(imagePath, "frameX"));
        int frameY = Integer.parseInt(XmlParser.parse(imagePath, "frameY"));
        int frameWidth = Integer.parseInt(XmlParser.parse(imagePath, "frameWidth"));
        int frameHeight = Integer.parseInt(XmlParser.parse(imagePath, "frameHeight"));

        Frame tempFrame = new Frame(x, y, width, height, frameX, frameY, frameWidth, frameHeight);
        
        frames.add(tempFrame);
        
    }

    // https://docs.oracle.com/javase/tutorial/2d/advanced/clipping.html
	// <SubTexture name="up press instance 10003" x="166" y="858" width="154" height="150" frameX="-0" frameY="-1" frameWidth="154" frameHeight="151"/>
}