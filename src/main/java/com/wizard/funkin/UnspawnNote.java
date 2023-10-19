package com.wizard.funkin;

public class UnspawnNote {
    public double hitTime;
    public int direction;
    public double length;
    public String type = "Default"; // for future shit???  ? ?

    // java doesn't have optional paramaters and i'm so mad
    // so i have to make 2 of the same function, one of them not having the thing

    public UnspawnNote(double hitTime, int direction, double length) {
        this.hitTime = hitTime;
        this.direction = direction;
        this.length = length;
        this.type = "Default";
    }

    public UnspawnNote(double hitTime, int direction, double length, String type) {
        this.hitTime = hitTime;
        this.direction = direction;
        this.length = length;
        this.type = type;
    }
}