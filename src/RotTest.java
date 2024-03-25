import processing.core.PApplet;
import processing.core.PImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//TODO: Create CSV for Hex Values
//TODO: Import CSV Reader
//TODO: Write Bleed method for use in Rot.grow()

public class RotTest extends PApplet {
    Rot rot;
    Walker reclaim;
    Walker reclaim2;
    Walker walker;
    public PImage walkedImage;
    public PImage bgImage;

    public void settings() {
        size(500,500);

        // Setting up the Images class objects
        bgImage = loadImage("BGImage.png");
        walkedImage = createImage(this.width, this.height, ARGB);
        walkedImage.loadPixels();
        rot = new Rot(this, walkedImage);
        walker = new Walker(this, (int) random(width), (int) random(height));
        reclaim = new Walker(this, (int) random(width), (int) random(height));
        reclaim2 = new Walker(this, (int) random(width), (int) random(height));

        //set the walked image to Transparent
        for (int i = 0; i < walkedImage.pixels.length; i++) {
            walkedImage.pixels[i] = 0x00000000;
        }

        //Update the Top image pixel array
        walkedImage.updatePixels();
    }

    public void draw() {
        // Draw both images.
        image(bgImage, 0, 0);
        image(walkedImage, 0, 0);

        // Step and display the black walker.
        walker.step(walkedImage);
        walker.displayWalker(walkedImage, 0xFF000000);

        /*
        Check the color and Grow the rot.
        TODO: Roll Color check into rot.grow()
        */
        rot.colorCheck(this, walkedImage);
        rot.grow(walkedImage);

        // Update the walked Image pixels once per loop.
        walkedImage.updatePixels();

        // Debug text
        fill(0);
        textSize(50);
        text(rot.counter, 10, 60);
    }

    public void keyPressed() {
        // Big step for the reclaiming walkers
        reclaim.bigStep(walkedImage, 0x00000000);
        reclaim2.bigStep(walkedImage, 0x00000000);
        walkedImage.updatePixels();

    }

    /*
    Run The Sketch. Hopefully this will be replaced soon.
    TODO: Figure out how to ditch PApplet and use Frame instead.
     */
    public static void main(String[] args) {
        String[] processingArgs = {"RotTest"};
        RotTest rotTest = new RotTest();
        PApplet.runSketch(processingArgs, rotTest);
    }
}