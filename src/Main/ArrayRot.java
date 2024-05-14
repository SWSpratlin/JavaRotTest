package Main;

import processing.core.PApplet;
import processing.core.PImage;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

public class ArrayRot {
    /**
     * Global class access for PImage methods. Mostly for access of the pixels array. Called in the constructor.
     * Use this to call the TRANSPARENT image ON TOP OF THE GRAPHIC IMAGE.
     */
    private PImage img;

    /**
     * Quick access variable to the img.pixels.length value
     */
    int max;

    /**
     * Variable to determine the threshold at which the updater triggers.
     */
    int oThresh = 15;

    /**
     * PApplet access for width/height fields. Called in the constructor as "this" unless otherwise specified.
     */
    private PApplet s;

    /**
     * Updater array the same size at the pixel array. Any index marked "true" will be stepped to the next
     * percent of opacity.
     */
    private List<Boolean> updater;

    /**
     * Int array to allow for quick access of the hexArray contents. Each index of the hex array is corresponding to a hex
     * code for opacity, and instead of searching the hexArray every time for the matching color, the pXp array will be updated
     * with each pixel's current opacity during the initial pass.
     */
    private List<Integer> pXp;

    /**
     * Static array that stores the hex codes for opacity.
     */
    private List<Integer> hexArray;

    /**
     * Variable to determine how big of a step the opacity will take. The higher the number, the faster the growth rate.
     */
    int step = 1;
    public int counter = 0;

    /**
     * Constructor. Called AFTER "loadPixels()" since this needs access to the pixels array
     *
     * @param sketch Usually "this" unless stated otherwise
     * @param image  Whatever image is ABOVE the graphic. Same image as the walker.
     */
    public ArrayRot(PApplet sketch, PImage image) {
        s = sketch;
        img = image;
        max = img.pixels.length;

        updater = new ArrayList<Boolean>(max);
        for (int i = 0; i < max; i++) {
            updater.add(false);
        }

        pXp = new ArrayList<Integer>(max);
        for (int i = 0; i < max; i++) {
            pXp.add(0);
        }

        hexArray = new ArrayList<Integer>();
        hexArray.add(0x00000000);
        hexArray.add(0x03000000);
        hexArray.add(0x05000000);
        hexArray.add(0x08000000);
        hexArray.add(0x0A000000);
        hexArray.add(0x0D000000);
        hexArray.add(0x0F000000);
        hexArray.add(0x12000000);
        hexArray.add(0x14000000);
        hexArray.add(0x17000000);
        hexArray.add(0x1A000000);
        hexArray.add(0x1C000000);
        hexArray.add(0x1F000000);
        hexArray.add(0x21000000);
        hexArray.add(0x24000000);
        hexArray.add(0x26000000);
        hexArray.add(0x29000000);
        hexArray.add(0x2B000000);
        hexArray.add(0x2E000000);
        hexArray.add(0x30000000);
        hexArray.add(0x33000000);
        hexArray.add(0x36000000);
        hexArray.add(0x38000000);
        hexArray.add(0x3B000000);
        hexArray.add(0x3D000000);
        hexArray.add(0x40000000);
        hexArray.add(0x42000000);
        hexArray.add(0x45000000);
        hexArray.add(0x47000000);
        hexArray.add(0x4A000000);
        hexArray.add(0x4D000000);
        hexArray.add(0x4F000000);
        hexArray.add(0x52000000);
        hexArray.add(0x54000000);
        hexArray.add(0x57000000);
        hexArray.add(0x59000000);
        hexArray.add(0x5C000000);
        hexArray.add(0x5E000000);
        hexArray.add(0x61000000);
        hexArray.add(0x63000000);
        hexArray.add(0x66000000);
        hexArray.add(0x69000000);
        hexArray.add(0x6B000000);
        hexArray.add(0x6E000000);
        hexArray.add(0x70000000);
        hexArray.add(0x73000000);
        hexArray.add(0x75000000);
        hexArray.add(0x78000000);
        hexArray.add(0x7A000000);
        hexArray.add(0x7D000000);
        hexArray.add(0x80000000);
        hexArray.add(0x82000000);
        hexArray.add(0x85000000);
        hexArray.add(0x87000000);
        hexArray.add(0x8A000000);
        hexArray.add(0x8C000000);
        hexArray.add(0x8F000000);
        hexArray.add(0x91000000);
        hexArray.add(0x94000000);
        hexArray.add(0x96000000);
        hexArray.add(0x99000000);
        hexArray.add(0x9C000000);
        hexArray.add(0x9E000000);
        hexArray.add(0xA1000000);
        hexArray.add(0xA3000000);
        hexArray.add(0xA6000000);
        hexArray.add(0xA8000000);
        hexArray.add(0xAB000000);
        hexArray.add(0xAD000000);
        hexArray.add(0xB0000000);
        hexArray.add(0xB3000000);
        hexArray.add(0xB5000000);
        hexArray.add(0xB8000000);
        hexArray.add(0xBA000000);
        hexArray.add(0xBD000000);
        hexArray.add(0xBF000000);
        hexArray.add(0xC2000000);
        hexArray.add(0xC4000000);
        hexArray.add(0xC7000000);
        hexArray.add(0xC9000000);
        hexArray.add(0xCC000000);
        hexArray.add(0xCF000000);
        hexArray.add(0xD1000000);
        hexArray.add(0xD4000000);
        hexArray.add(0xD6000000);
        hexArray.add(0xD9000000);
        hexArray.add(0xDB000000);
        hexArray.add(0xDE000000);
        hexArray.add(0xE0000000);
        hexArray.add(0xE3000000);
        hexArray.add(0xE6000000);
        hexArray.add(0xE8000000);
        hexArray.add(0xEB000000);
        hexArray.add(0xED000000);
        hexArray.add(0xF0000000);
        hexArray.add(0xF2000000);
        hexArray.add(0xF5000000);
        hexArray.add(0xF7000000);
        hexArray.add(0xFA000000);
        hexArray.add(0xFC000000);
        hexArray.add(0xFF000000);
        hexArray = Collections.unmodifiableList(hexArray);


        System.out.println("Updater: " + updater.size() + " pXp: " + pXp.size() + " hexArray: " + hexArray.size());
    }

    /**
     * External method, scans the PImage pixel array. Checks each individual entry of the pixel array and does 2 things.
     * 1) Updates the current opacity percentage for each pixel in the pXp array by finding its matching color int in the hexArray.
     * 2) Marks any pixel that is above a certain threshold (index of the hexArray) for updating ("true" in the updater array).
     */
    private void scan() {
        int color;
        // loop through th entire pixels array
        for (int i = 0; i < max; i++) {
            color = img.pixels[i];
            // Comb through the hex array and look for matches. Update the pXp array with each match.
            pXp.set(i, hexArray.indexOf(img.pixels[i]));
            if (pXp.get(i) > oThresh)  this.updateSet(i);
        }
    }

    /**
     * Marks a set of pixels for updating originating at Index and growing out in a circular pattern
     *
     * @param index the central index to base the rest of the updates on.
     */
    private void updateSet(int index) {
        int width = s.width;
        updater.set(index, true);
        if (index - 1 > 0) updater.set(index - 1, true);
        if (index - 2 > 0) updater.set(index - 2, true);
        if (index - width > 0) updater.set(index - width, true);
        if ((index - width - 1) > 0) updater.set(index - width - 1, true);
        if ((index - (width * 2)) > 0) updater.set(index - (width * 2), true);
        if ((index - width + 1) > 0) updater.set(index - width + 1, true);
        if ((index - (width * 2) - 1) > 0) updater.set(index - (width * 2) - 1, true);
        if ((index - (width * 2) + 1) > 0) updater.set(index - (width * 2) + 1, true);
        if (index + 1 < max) updater.set(index + 1, true);
        if (index + 2 < max) updater.set(index + 2, true);
        if (index + width < max) updater.set(index + width, true);
        if (index + width - 1 < max) updater.set(index + width - 1, true);
        if (index + width + 1 < max) updater.set(index + width + 1, true);
        if (index + (width * 2) < max) updater.set(index + (width * 2), true);
        if ((index + (width * 2) - 1) < max) updater.set(index + (width * 2) - 1, true);
        if ((index + (width * 2) + 1) < max) updater.set(index + (width * 2) + 1, true);
    }


    private Integer colorStep(Integer current) {
        int step = current + 1;
        if (step < hexArray.size()) {
            return hexArray.get(step);
        } else return null;
    }

    /**
     * EXTERNAL METHOD
     * Iterates through the updater array. Any index marked for updating gets the current percentage pulled from
     */
    public void grow() {
        scan();
        for (int i = 0; i < max; i++) {
            if (updater.get(i)) {
                if (colorStep(pXp.get(i)) != null) {
                    img.pixels[i] = colorStep(pXp.get(i));
                    updater.set(i, false);
                }
            }
        }
    }
}
