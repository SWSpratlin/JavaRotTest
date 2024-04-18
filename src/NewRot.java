import processing.core.PApplet;
import processing.core.PImage;

import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    Scanner sc;

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
            sc = new Scanner(table);
        } else {
            throw new FileNotFoundException("File Not Found. Maybe the path is wrong?");
        }
        scanner.useDelimiter("[,\\n]");
    }

    //TODO: Try removing the pxP array? One less array to comb.

    /**
     * Intakes an Int (color) and spits out the associated percentage.
     * References the "hexPerc.csv" table, each hex is paired with its OPacity percentage
     *
     * @param c the color to be matched. Usually "img.pixels[i]"
     */
    private int scan(int c) {
        int hexNum;
        int decNum;

        //If the scanner has the next input, write the next input to the "data" string
        if (scanner.hasNext()) {
            String data = scanner.next();

            //if the data string starts with "0x", decode it to the Hex variable
            //if it doesn't, increment the scanner
            if (data.startsWith("0x")) {
                hexNum = Integer.decode(data);

                // Check if the data written matches the requested data
                if (hexNum == c) {

                    //if it does, get the next value (the percentage) and return the parsed value
                    data = scanner.next();
                    decNum = Integer.parseInt(data, 10);
                    return decNum;
                }
            } else data = scanner.next(); //Increment the scanner if it doesn't match.
        }
        //return 0 if there are no matches (shouldn't apply)
        return 0;
    }

    /**
     * Used internally to search the pxP array and mark surrounding pixels for updating.
     *
     * @param index usually "i". Whatever variable is iterated through in a for loop
     */
    private void colorCheck(int index) {
        int threshold = 20;

        if (scan(img.pixels[index]) > threshold) {
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
     * Intakes the percentage, and increments to the next hex in the progression. Steps one percent in opacity.
     * Uses a new scanner object so it doesn't interfere with the other scanner, therefore throws a FileNotFound exception
     *
     * @param p the current percentage of the pixel being referenced. probably (scan(img.pixels[index])
     * @return the next Hex Code in the progression
     */
    private int colorStep(int p) {
        // Set up new scanner
        sc.useDelimiter("[,\\n]");

        //when the scanner is fed a number, have it search the table for the correct value. This shouldn't be too heavy as it
        //only needs to search anything that is marked on the updater
        while (sc.hasNext()) {
            String data = sc.next();

            //if the data is a hex code, move on
            if (data.startsWith("0x")) {
                data = sc.next();
            } else {
                //if it is not, parse it and compare. Then return the NEXT value, which will be the next hex code.
                if (Integer.parseInt(data, 10) == p) {
                    data = sc.next();
                    return Integer.decode(data);
                } else data = sc.next(); //if it DOESN'T match, increment the scanner
            }
        }
        return 0;
    }

    /**
     * Used EXTERNALLY to call the growth of the Rot.
     *
     * @param sketch usually "this" but refers to the sketch it will be called in
     * @param image  the image that the rot will be growing on
     */
    public void grow(PApplet sketch, PImage image) {
        for (int i = 0; i < max; i++){
            colorCheck(i);
        }
        for (int i = 0; i < max; i++){
            if (updater[i]){
            image.pixels[i] = colorStep(scan(img.pixels[i]));
            }
        }
    }
}
