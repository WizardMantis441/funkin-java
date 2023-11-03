// quick helper tool bc it makes things easier :)

package com.wizard.backend;

import com.wizard.backend.Cache;
import java.awt.image.BufferedImage;


public class Paths {
	// cache (doesn't recall asset)

    public static String asset(String name) {
        return "main/java/assets/" + name;
        // return "assets/" + name;
    }

    public static String image(String name) {
        return asset("images/" + name + ".png");
    }


    public static String songInst(String song) {
        return asset("songs/" + song + "/Inst.wav");
    }

    public static String songVoices(String song) {
        return asset("songs/" + song + "/Voices.wav");
    }

    public static String songChart(String song) {
        return asset("songs/" + song + "/chart.json");
    }
}
