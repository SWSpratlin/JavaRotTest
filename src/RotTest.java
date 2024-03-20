import processing.core.PApplet;
import processing.core.PImage;


public class RotTest extends PApplet {
    Rot rot;
    Walker reclaim;
    Walker reclaim2;
    public PImage walkedImage;
    public PImage bgImage;

    public void settings() {
        size(500, 500);

        bgImage = loadImage("BGImage.png");
        walkedImage = createImage(this.width, this.height, ARGB);
        walkedImage.loadPixels();
        rot = new Rot(this, walkedImage);

        //set the walked image to black
        for (int i = 0; i < width; i++) {
            walkedImage.pixels[i + (width * (height / 2))] = 0xFF000000;
        }
        walkedImage.updatePixels();
    }
    public void draw() {
        image(bgImage, 0, 0);
        image(walkedImage, 0, 0);

        rot.colorCheck(this, walkedImage);
        rot.grow(walkedImage);


        walkedImage.updatePixels();

        fill(0);
        textSize(50);
        text(rot.counter, 10, 60);
    }

    public void keyPressed() {
        reclaim.bigStep(walkedImage,0x00000000);
        reclaim2.bigStep(walkedImage,0x00000000);
        walkedImage.updatePixels();

    }

    public static void main(String[] args) {
        String[] processingArgs = {"RotTest"};
        RotTest rotTest = new RotTest();
        PApplet.runSketch(processingArgs, rotTest);
    }
}