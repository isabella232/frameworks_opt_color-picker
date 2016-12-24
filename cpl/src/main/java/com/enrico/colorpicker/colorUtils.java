package com.enrico.colorpicker;

import android.content.res.Resources;
import android.graphics.Color;

import java.util.Random;

class colorUtils {

    //get hex value from color
    static String getColorValues(int color) {

        return String.format("%06x", (0xFFFFFF & color));

    }

    //get red
    static String getR(int hex) {

        return String.valueOf(Color.red(hex));

    }

    //get green
    static String getG(int hex) {

        return String.valueOf(Color.green(hex));

    }

    //get blue
    static String getB(int hex) {

        return String.valueOf(Color.blue(hex));

    }

    //random colors
    static int randomColor(Resources resources) {

        int[] colors = resources.getIntArray(R.array.colors);
        return colors[new Random().nextInt(colors.length)];

    }
}