import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewRot {

    /**
     * PApplet for "width" and "height" references (could change to
     * a class variable but for now this works.
     */
    PApplet sketch;
    /**
     * Class access for the PImage. Necessary for access of the width/height fields.
     */
    PImage img;

    // Counter for debugging.
    int counter;
    //Max variable for array length purposes
    final int max;

    /**
     * pxP is the array to hold the percentage values for each pixel. Updated using
     * a combination of the csvSearch() and scan() methods
     */
    int[] pxP;
    /**
     * Updater is the array that marks each eligible pixel for a step in Opacity.
     * Updated in the colorStep() method, executed and reset in the grow() method.
     */
    boolean[] updater;

    /**
     * Constructor.
     *
     * @param s Usually "This" unless two different sketches in the same doc.
     * @param w PImage to be referenced. Necessary for width/height access used
     */
    public NewRot(PApplet s, PImage w) {
        this.sketch = s;
        this.img = w;

        // Initialize arrays, and fill them with their default values
        pxP = new int[w.pixels.length];
        updater = new boolean[w.pixels.length];
        max = w.pixels.length;

        Arrays.fill(pxP, 0);
        Arrays.fill(updater, false);

        // Initialize the CSV File.
        String tableName = "hexPerc.csv";
        File table = new File(tableName);
    }

    /**
     * Used internally as the search method for the CSV.
     */
    private int csvSearch(int hex) {
        // P stands for Percent. will be assigned a value from the CSV
        int p = 0;
        return p;
    }

    /**
     * Used internally INSIDE a for loop to write values to each index of the pxP array.
     * This method will contain all actions for the scanning and updating of the pxP array
     * within a single loop of a PImage
     */
    private void scan() {

    }


}
