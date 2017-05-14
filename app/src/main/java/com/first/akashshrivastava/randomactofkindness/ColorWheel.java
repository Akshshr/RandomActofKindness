package com.first.akashshrivastava.randomactofkindness;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by akashshrivastava on 15-03-18.
 */
public final class ColorWheel {
    String[] allTheColor =
            {"#F44336",
            "#E91E63",
            "#093145",
            "#9C27B0",
            "#673AB7",
            "#0277BD",
            "#0097A7",
            "#B2EBF2",
            "#26A69A",
            "#4CAF50",
            "#B6B6B6",
           "#FFCDD2",
            "#9CCC65",
            "#CDDC39",
            "#FFC107",
            "#FF9800",
            "#F4511E",
            "#5D4037",
            "#757575",
            "#607D8B",
            "#2c3e50",
            "#2ecc71",
            "#16a085",
            "#1abc9c",
            "#34495e",
            "#95a5a6",
            "#e74c3c"};

        public int getColor(){

        String color = "";
            Random randomGenerator = new Random();
            int randomNumber = randomGenerator.nextInt(allTheColor.length);

            color = allTheColor[randomNumber];

            int colorAsInt = Color.parseColor(color);
            return colorAsInt;


        }


}
