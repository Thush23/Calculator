package com.example.gittrial;

import java.io.Serializable;

public class Bus implements Serializable {
    private String eyecolor;
    private String haircolor;

    public Bus(String eyecolor, String haircolor) {
        this.eyecolor = eyecolor;
        this.haircolor = haircolor;
    }

    public String getEyecolor() {
        return eyecolor;
    }

    public String getHaircolor() {
        return haircolor;
    }
}
