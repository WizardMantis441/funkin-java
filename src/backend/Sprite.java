package src.backend;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class Sprite extends JPanel {
    private double x;
    private double y;

    private BufferedImage image;
    private Map<String, Animation> anims; // note: if no anims, assume it's not a spritesheet

    private double scaleX = 1.0;
    private double scaleY = 1.0;
    
    private String curAnim = null;
    private int curFrame = 0;

    public Sprite(double x, double y, String path) {
        this.x = x;
        this.y = y;

        loadImage(path);
        anims = new HashMap<>();
    }

    public void update(double elapsed) {
        // penis
    }

    private void loadImage(String path) {
        try {
            image = ImageIO.read(new File(path));
            this.setBounds((int) (x), (int) (y), image.getWidth(), image.getHeight());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addAnim(String animName, Animation anim) {
        anims.put(animName, anim);
    }

    public void playAnim(String animName) {
        if (anims.containsKey(animName)) // look at me being so cautious :)
            curAnim = animName;
    }

    public void setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        
        this.setBounds((int) (x), (int) (y), (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY));
        this.render();
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

        // if (anims.isEmpty()) {
            // g.drawImage(image, 0, 0, width, height, null);
        // } else {
            // int xPos = anims.get(curAnim)._x;
            // int yPos = anims.get(curAnim)._x;

            g.setClip(0, 0, width / 2, height / 2);
            g.drawImage(image, 0, 0, width, height, null);
        // }
    }

    public void render() {
        repaint();
    }
}