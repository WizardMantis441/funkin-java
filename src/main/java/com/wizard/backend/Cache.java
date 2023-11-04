package com.wizard.backend;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;

public class Cache {
    private static HashMap<String, BufferedImage> imageCache;

    /* public Cache() {
        imageCache = new HashMap<>();
    } */

    public static BufferedImage getImage(String path) {
		if (imageCache == null) {
			imageCache = new HashMap<>();
		}
        BufferedImage image = imageCache.get(path);

        if (image == null) {
            try {
                image = ImageIO.read(new File(path));
                imageCache.put(path, image);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return image;
    }
}