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
    private String imagePath;

    private Map<String, Animation> anims; // note: if no anims, assume it's not a spritesheet

    private double scaleX = 1.0;
    private double scaleY = 1.0;
    
    private String curAnim = null;

    public Sprite(double x, double y, String path) {
        this.x = x;
        this.y = y;
        this.imagePath = path;

        loadImage(imagePath);
        anims = new HashMap<>();
    }

    public void update(double elapsed) {
        // penis
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
        Animation newAnim = new Animation(this.imagePath, animPrefix);
        newAnim.fps = 24;
        newAnim.loop = false;

        anims.put(animName, newAnim);
    }

    public void playAnim(String animName) {
        if (anims.containsKey(animName)) // look at me being so cautious :)
            curAnim = animName;
        else
            System.out.println(animName + " IS NOT AN ANIMATION YOU TWAT!!!");
    }

    public void setScale(double scaleX, double scaleY) {
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        
        this.setBounds((int) (x), (int) (y), (int) (image.getWidth() * scaleX), (int) (image.getHeight() * scaleY));
        this.draw();
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

        if (curAnim == null) {
            g.drawImage(image, 0, 0, width, height, null);
        } else {
            // x = x
            // y = y
            // width = x + frameX
            // height = y + frameY

            g.setClip(0, 0, width / 2, height / 2);
            g.drawImage(image, 0, 0, width, height, null);
        }

        // if (anims.isEmpty()) {
            // g.drawImage(image, 0, 0, width, height, null);
        // } else {
            // int xPos = anims.get(curAnim)._x;
            // int yPos = anims.get(curAnim)._x;

        // }
    }

    public void draw() {
        repaint();
    }
}