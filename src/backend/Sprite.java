package src.backend;

import javax.swing.*;

import org.xml.sax.SAXException;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.imageio.ImageIO;

public class Sprite extends JPanel {
    private double x;
    private double y;

    private BufferedImage image;
    private String imagePath;

    private Map<String, Animation> anims; // note: if no anims, assume it's not a spritesheet

    private double scaleX = 1.0;
    private double scaleY = 1.0;
    
    private Animation curAnim = null;

    private Frame frame;

    public Sprite(double x, double y, String path) {
        this.x = x;
        this.y = y;
        this.imagePath = path;

        loadImage(imagePath);
        anims = new HashMap<>();
    }

    public void update(double elapsed) {
        // penis
        this.repaint();
        // if (curAnim != null) {
        //     curAnim.update(elapsed);
        //     frame = curAnim.frames.get(curAnim.curFrame);
        // }
    }

    private void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
            this.setLocation((int) x, (int) y);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnim(String animName, String animPrefix) {
        ArrayList<Frame> frames = new ArrayList<Frame>();

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder db = dbf.newDocumentBuilder(); // my hatred for xml grows.
            File file = new File(this.imagePath.replace(".png", ".xml"));
            Document xml = db.parse(file);
            xml.getDocumentElement().normalize();
            NodeList list = xml.getElementsByTagName("SubTexture");
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;

                    if (!e.getAttribute("name").startsWith(animPrefix)) {
                        continue;
                    }

                    Frame frame = new Frame(
                        Integer.parseInt(e.getAttribute("x")),
                        Integer.parseInt(e.getAttribute("y")),
                        Integer.parseInt(e.getAttribute("width")),
                        Integer.parseInt(e.getAttribute("height")),
                        Integer.parseInt(e.getAttribute("frameX")),
                        Integer.parseInt(e.getAttribute("frameY")),
                        Integer.parseInt(e.getAttribute("frameWidth")),
                        Integer.parseInt(e.getAttribute("frameHeight"))
                    );

                    frames.add(frame);
                }
            }
        } catch(NumberFormatException | SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }

        // TODO: sorting

        Animation newAnim = new Animation(this.imagePath, animPrefix, frames);
        newAnim.fps = 24;
        newAnim.looped = true;

        if (frame == null) {
            frame = frames.get(0);
        }

        anims.put(animName, newAnim);
    }

    public void playAnim(String animName) {
        if (!anims.containsKey(animName)) 
            return; 
        
        curAnim = anims.get(animName);
        curAnim.play();
        
    }

    public void setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
    }

    public BufferedImage getImage() { return image; }

    public double getX(double x) { return this.x; }
    public double getY(double y) { return this.y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int width = (int) (image.getWidth() * scaleX);
        int height = (int) (image.getHeight() * scaleY);

        g.drawImage(image, 0, 0, width, height, null);

        /*
        if (frame == null) {
            g.drawImage(image, 0, 0, width, height, null);
        } else {
            System.out.println(frame);
            // g.setClip(0, 0, (int) (frame.width * scaleX), (int) (frame.height * scaleY));
            //g.drawImage(image, (int) (x - frame.x), (int) (y - frame.y), width, height, null);
            g.drawImage(image, 0, 0, width, height, null);
        }
        */
        
    }

    public void draw(Graphics g) {
        int width = (int) (image.getWidth() * scaleX);
        int height = (int) (image.getHeight() * scaleY);

        g.drawImage(image, 0, 0, width, height, null);
        
        /*
        if (frame == null) {
            g.drawImage(image, 0, 0, width, height, null);
        } else {
            //<SubTexture name="right confirm instance 10000" x="230" y="470" width="217" height="219" frameX="-3" frameY="-4" frameWidth="228" frameHeight="231"/>
            // frame = new Frame(230, 470, 217, 219, -3, -4, 228, 231);
            // Area outside = new Area(new Rectangle2D.Double(x + frame.x, y + frame.y, frame.width * scaleX, frame.height * scaleY));
            //System.out.println(frame);
            //g.setClip(frame.x, frame.y, (int) (frame.width * scaleX), (int) (frame.height * scaleY));
            //g.setClip((int)x, (int)y, (int) (229 * scaleX), (int) (229 * scaleY));
            //g.setClip((int)(x), (int) (y), (int) (frame.width * scaleX), (int) (frame.height * scaleY));
            // g.setClip(outside);
            //g.drawImage(image, (int) (x - frame.x), (int) (y - frame.y), width, height, null);
            //g.drawImage(image, (int)(x - frame.x), (int)(y - frame.y), width, height, null);
            g.drawImage(image, 0, 0, width, height, null);
        }
        */
        
    }
}