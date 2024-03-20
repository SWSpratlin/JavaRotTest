import processing.core.PApplet;
import processing.core.PImage;

public class RotTest extends PApplet {
    PImage bgImg;

    public void settings() {
        size(500, 500);
    }

    public void draw() {
        background(150);
        ellipse(mouseX, mouseY, 50, 50);
    }

    public void mousePressed(){
        fill((int)random(255), (int)random(255), (int)random(255));
        ellipse(mouseX, mouseY, 50,50);
        background((int)random(255));
    }

    public static void main(String[] args) {
        String[] processingArgs = {"RotTest"};
        RotTest rotTest = new RotTest();
        PApplet.runSketch(processingArgs, rotTest);
    }
}