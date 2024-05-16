package main;

import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PGraphics;

import java.util.Arrays;


//TODO: Import CSV Reader
//TODO: Write Bleed method for use in Rot.grow()

public class RotTest extends PApplet {
    ArrayRot rot;
    Walker reclaim;
    Walker reclaim2;
    Walker reclaim3;
    Walker reclaim4;
    public Walker walker;
    public PImage walkedImage;
    public PImage bgImage;
    public int d = 5; //Frame delay. Increase to slow down rot growth
    PGraphics graphics;

    public void settings() {
        size(1920, 1080);
        this.g = graphics;
        fullScreen(2);
        System.out.println("Total = " + (this.width * this.height));

        // Setting up the Images class objects
        bgImage = loadImage("BGImage.png");
        bgImage.resize(width, height);
        walkedImage = createImage(this.width, this.height, ARGB);
        walkedImage.loadPixels();
        rot = new ArrayRot(this, walkedImage);
        walker = new Walker(this, (int) random(width), (int) random(height));
        reclaim = new Walker(this, (int) random(width), (int) random(height));
        reclaim2 = new Walker(this, (int) random(width), (int) random(height));
        reclaim3 = new Walker(this, (int) random(width), (int) random(height));
        reclaim4 = new Walker(this, (int) random(width), (int) random(height));

        //set the walked image to Transparent
        Arrays.fill(walkedImage.pixels, 0x00000000);

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
        reclaim3.bigStep(walkedImage, 0x00000000);
        reclaim4.bigStep(walkedImage, 0x00000000);

        /*
        Check the color and Grow the rot.
        */
        if (frameCount % d == 0) {
            rot.grow();
        }

        // Update the walked Image pixels once per loop.
        walkedImage.updatePixels();

        fill(0);
        text(frameRate, 10, 10);

        // Get the Java runtime
        Runtime runtime = Runtime.getRuntime();

        if (frameCount % 600 == 0){
            long memory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println("Used memory is bytes: " + memory);
            System.out.println("Used memory is megabytes: "
                    + bytesToMegabytes(memory));
        }
    }

    public void keyPressed() {
        // Big step for the reclaiming walkers
        reclaim.bigStep(walkedImage, 0x00000000);
        reclaim2.bigStep(walkedImage, 0x00000000);
        walkedImage.updatePixels();

    }

    private static final long MEGABYTE = 1024L * 1024L;

    public static long bytesToMegabytes(long bytes) {
        return bytes / MEGABYTE;
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