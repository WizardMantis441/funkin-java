// quick helper tool bc it makes things easier :)

package com.wizard.backend;

public class Paths {
    public static String asset(String name) {
        return "src/main/java/assets/" + name;
    }

    public static String image(String name) {
        return asset("images/" + name + ".png");
    }

    public static String songInst(String song) {
        return asset("songs/" + song + "/Inst.ogg");
    }

    public static String songVoices(String song) {
        return asset("songs/" + song + "/Voices.ogg");
    }

    public static String songChart(String song) {
        return asset("songs/" + song + "/chart.json");
    }
}
