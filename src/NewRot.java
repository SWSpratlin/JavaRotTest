import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewRot {

    // PApplet for "width" and "height" references (could change to
    // a class variable but for now this works.
    PApplet sketch;

    // Counter for debugging.
    int counter;
    //Max variable for array length purposes
    final int max;

    // pxP is the array to hold the percentage values for each pixel
    // updater is to mark which pixels need updating.
    int[] pxP;
    boolean[] updater;

    public NewRot(PApplet s, PImage w){
        this.sketch = s;

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

    private void scan(PImage img){

    }
}
