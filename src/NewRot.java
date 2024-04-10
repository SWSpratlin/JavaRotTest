import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewRot {

    /**
     * PApplet for "width" and "height" references (could change to a class variable but for now this works.
     */
    PApplet sketch;
    /**
     * Class access for the PImage. Necessary for access of the width/height fields.
     */
    PImage img;

    /**
     * Counter for debugging.
     */

    int counter;
    /**
     * Max variable for array length purposes
     */
    final int max;

    /**
     * pxP is the array to hold the percentage values for each pixel. Updated using a combination of the csvSearch() and
     * scan() methods
     */
    int[] pxP;
    /**
     * Updater is the array that marks each eligible pixel for a step in Opacity. Updated in the colorStep() method,
     * executed and reset in the grow() method.
     */
    boolean[] updater;

    /**
     * File to load CSV
     */
    File table;
    /**
     * Scanner to read CSV.
     */
    Scanner scanner;

    /**
     * Constructor.
     *
     * @param s Usually "This" unless two different sketches in the same doc.
     * @param w PImage to be referenced. Necessary for width/height access used
     */
    public NewRot(PApplet s, PImage w) throws FileNotFoundException {
        this.sketch = s;
        this.img = w;

        // Initialize arrays, and fill them with their default values
        pxP = new int[w.pixels.length];
        updater = new boolean[w.pixels.length];
        max = w.pixels.length;

        Arrays.fill(pxP, 0);
        Arrays.fill(updater, false);

        // Initialize the CSV File.
        String tableLoc = "data/hexPerc.csv";
        table = new File(tableLoc);
        if (table.exists()) {
            scanner = new Scanner(table);
        } else {
            throw new FileNotFoundException("File Not Found. Maybe the path is wrong?");
        }
        scanner.useDelimiter("[,\\n]");
    }

    /**
     * Used internally INSIDE a for loop to write values to each index of the pxP array. This method will contain all
     * actions for the scanning and updating of the pxP array within a single loop of a PImage. Bust be called BEFORE
     * colorCheck to ensure the pxP array is updated.
     * Returns the percentage of the color being referenced.
     *
     * @param index usually "i" or whatever variable is being looped
     * @param c     the color to be matched. Usually "img.pixels[i]"
     */
    private int scan(int c, int index) {
        while (scanner.hasNext()) {
            int num = Integer.decode(scanner.next());
            if (num == c) {
                return scanner.nextInt(10);
            } else {
                scanner.next("[,\\n]");
            }
        }
        return 0;
    }

    /**
     * Used internally to search the pxP array and mark surrounding pixels for updating.
     *
     * @param index usually "i". Whatever variable is iterated through in a for loop
     */
    private void colorCheck(int index) {
        int threshold = 20;

        if (pxP[index] > threshold) {
            updater[index] = true;
            if (index + 1 < max) {
                updater[index + 1] = true;
            }
            if (index + 2 < max) {
                updater[index + 2] = true;
            }
            if (index - 1 > 0) {
                updater[index - 1] = true;
            }
            if (index - 2 > 0) {
                updater[index - 2] = true;
            }
            if (index + img.width < max) {
                updater[index + img.width] = true;
            }
            if (index + img.width + 1 < max) {
                updater[index + img.width + 1] = true;
            }
            if (index + img.width - 1 < max) {
                updater[index + img.width - 1] = true;
            }
            if (index + (img.width * 2) < max) {
                updater[index + (img.width * 2)] = true;
            }
            if (index - img.width > 0) {
                updater[index - img.width] = true;
            }
            if (index - img.width - 1 > 0) {
                updater[index - img.width - 1] = true;
            }
            if (index - img.width + 1 > 0) {
                updater[index - img.width + 1] = true;
            }
            if (index - (img.width * 2) > 0) {
                updater[index - (img.width * 2)] = true;
            }
        }
    }

    /**
     * Used EXTERNALLY to call the growth of the Rot.
     *
     * @param sketch usually "this" but refers to the sketch it will be called in
     * @param image  the image that the rot will be growing on
     */
    public void grow(PApplet sketch, PImage image) {

    }

}
