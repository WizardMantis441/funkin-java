package com.wizard.backend.rendering;

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
import java.util.Comparator;

import com.wizard.backend.Cache;
import com.wizard.backend.Game;
import com.wizard.backend.rendering.interfaces.Drawable;

public class Sprite extends JPanel implements Drawable {
	public double x;
	public double y;

	public double offsetX = 0.0;
	public double offsetY = 0.0;

	public double scaleX = 1.0;
	public double scaleY = 1.0;

	private BufferedImage image;
	private String imagePath;

	// Animations
	private Map<String, Animation> anims; // note: if no anims, assume it's not a spritesheet
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
		//this.repaint();
		//System.out.println("update");
		if (curAnim != null) {
			curAnim.update(elapsed);
			frame = curAnim.frames.get(curAnim.curFrame);
		}
	}

	private void loadImage(String path) {
		image = Cache.getImage(path);
	}

	public void addAnim(String animName, String animPrefix, int fps, boolean looped) {
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
						e.getAttribute("name"),
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

		Animation newAnim = new Animation(this.imagePath, animPrefix, frames, animName);
		newAnim.fps = fps;
		newAnim.looped = looped;

		if (frame == null) {
			frame = frames.get(0);
		}

		frames.sort(new FrameSortingComparator());
		anims.put(animName, newAnim);
	}

	public void playAnim(String animName) {
		playAnim(animName, false);
	}

	public void playAnim(String animName, boolean force) {
		if (!anims.containsKey(animName)) {
			System.out.println("animation doesnt exist: " + animName);
			return;
		}
		if(curAnim != null && (animName == curAnim.animName) && !force) {
			return;
		}

		curAnim = anims.get(animName);
		curAnim.play();
	}

	public String getAnimName() {
		if(curAnim == null)
			return null;
		return curAnim.animName;
	}

	public void setScale(double sx, double sy) {
		this.scaleX = sx;
		this.scaleY = sy;
	}

	public void setOffset(double ox, double oy) {
		this.offsetX = ox;
		this.offsetY = oy;
	}
   
   public void setAnimOffset(String anim, double ox, double oy) {
      anims.get(anim).offsetX = ox;
      anims.get(anim).offsetY = oy;
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
	}

	public void draw(Graphics g) {
		int width = (int) (image.getWidth() * scaleX);
		int height = (int) (image.getHeight() * scaleY);

		if (frame == null) {
			g.drawImage(image, (int)(x + offsetX), (int)(y + offsetY), width, height, null);
		} else {
			double xx = (x + offsetX + curAnim.offsetX) - frame.x * scaleX;
			double yy = (y + offsetY + curAnim.offsetY) - frame.y * scaleY;
			xx -= frame.frameX * scaleX;
			yy -= frame.frameY * scaleY;

			g.setClip((int)(xx + frame.x * scaleX), (int) (yy + frame.y * scaleY), (int) (frame.width * scaleX), (int) (frame.height * scaleY));
			g.drawImage(image, (int)(xx), (int)(yy), width, height, null);
		}
	}

	public void destroy() {
		Game.window.remove(this); // good idea wiz, 10/10 gol den balloon stars // thanks sword cude 10/10 response thx homie // welcome to fucking // dont say that what if my teacher sees omg!!!! // oh fuck you're so fucking right i would never fucking swear in my whole fucking life shit bro // OKAY BUDDY YOURE DONE
		if (this.image != null) image.flush();
		if (anims != null) anims.clear();
		image = null;
		anims = null;
		frame = null;
		curAnim = null;
	}
}

class FrameSortingComparator implements Comparator<Frame> {
    @Override
    public int compare(Frame i1, Frame i2) {
        return i1.name.compareTo(i2.name);
    }
}