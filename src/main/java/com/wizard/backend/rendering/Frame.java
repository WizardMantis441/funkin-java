package com.wizard.backend.rendering;

public class Frame {
    public String name;
    public int x;
    public int y;
    public int width;
    public int height;
    public int frameX;
    public int frameY;
    public int frameWidth;
    public int frameHeight;

    public Frame(String name, int x, int y, int width, int height, int frameX, int frameY, int frameWidth, int frameHeight) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.frameX = frameX;
        this.frameY = frameY;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
    }
}
