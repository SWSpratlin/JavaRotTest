import processing.core.PApplet;
import processing.core.PImage;
import processing.data.IntList;


public class Rot {
    private PApplet sketch;
    //moving from Method to Class variable since it needs to be initialized then continually changed.
    int darkPixel = -1;
    int refPix = -1;

    IntList darkPixels;
    int[] updater;

    //counter for debugging
    int counter = 0;

    public Rot(PApplet sketch, PImage walkImg) {

        this.sketch = sketch;

        darkPixels = new IntList();
        updater = new int[walkImg.pixels.length];
        counter = 1;

    }

    //method to check the individual color of each entry in the pixel array
    public void colorCheck(PApplet sketch, PImage rotImg) {

        this.sketch = sketch;

        for (int i = 0; i < rotImg.pixels.length; i++) {
            try{
                if (rotImg.pixels[i] == 0xFF000000) {
                    updater[i] = 1;
                    counter++;
                    if (rotImg.pixels[i - (sketch.width * 2)] >= 0) {
                        updater[i - sketch.width - 1] = 1;
                        updater[i - sketch.width] = 1;
                        updater[i - sketch.width + 1] = 1;
                        updater[i - 1] = 1;
                        updater[i - (sketch.width * 2)] = 1;
                        updater[i - 2] = 1;
                        counter += 6;
                    }
                    if ((i + (sketch.width * 2)) < rotImg.pixels.length) {
                        updater[i + 1] = 1;
                        updater[i + sketch.width - 1] = 1;
                        updater[i + sketch.width] = 1;
                        updater[i + sketch.width + 1] = 1;
                        updater[i + 2] = 1;
                        updater[i + (sketch.width * 2)] = 1;
                        counter += 6;
                    }
                }
            } catch(Exception e) {
                System.out.println("Incrementer= " + i);
                i = 0;
            }

        }
    }

    /**
     * Large method that intakes an Alpha Hex code and returns the NEXT step in transparency
     * Steps in increments of 1%
     * Make sure the hex format is "0x..." with a 2 digit hex at the start, and six numbers after
     */
    public int colorStep(int c) {

        //Large If/Else statement to determine the next logical step.
        if (c == 0x00000000) {
            return 0x03000000; // return 1% opacity
        } else if (c == 0x03000000) {
            return 0x05000000; // return 2% opacity
        } else if (c == 0x05000000) {
            return 0x08000000; // return 3% opacity
        } else if (c == 0x08000000) {
            return 0x0A000000; // return 4% opactiy
        } else if (c == 0x0A000000) {
            return 0x0D000000; // return 5% opacity
        } else if (c == 0x0D000000) {
            return 0x0F000000; // return 6% opacity
        } else if (c == 0x0F000000) {
            return 0x12000000; // return 7% opacity
        } else if (c == 0x12000000) {
            return 0x14000000; // return 8% opacity
        } else if (c == 0x14000000) {
            return 0x17000000; // return 9% opacity
        } else if (c == 0x17000000) {
            return 0x1A000000; // return 10% opacity
        } else if (c == 0x1A000000) {
            return 0x1C000000; // return 11% opacity
        } else if (c == 0x1C000000) {
            return 0x1F000000; // return 12% opacity
        } else if (c == 0x1F000000) {
            return 0x21000000; // return 13% opactiy
        } else if (c == 0x21000000) {
            return 0x24000000; // return 14% opacity
        } else if (c == 0x24000000) {
            return 0x26000000; // return 15% opacity
        } else if (c == 0x26000000) {
            return 0x29000000; // return 16% opacity
        } else if (c == 0x29000000) {
            return 0x2B000000; // return 17% opacity
        } else if (c == 0x2B000000) {
            return 0x2E000000; // return 18% opacity
        } else if (c == 0x2E000000) {
            return 0x30000000; // return 19% opacity
        } else if (c == 0x30000000) {
            return 0x33000000; // return 20% opacity
        } else if (c == 0x33000000) {
            return 0x36000000; // return 21% opacity
        } else if (c == 0x36000000) {
            return 0x38000000; // return 22% opactiy
        } else if (c == 0x38000000) {
            return 0x3B000000; // return 23% opacity
        } else if (c == 0x3B000000) {
            return 0x3D000000; // return 24% opacity
        } else if (c == 0x3D000000) {
            return 0x40000000; // return 25% opacity
        } else if (c == 0x40000000) {
            return 0x42000000; // return 26% opacity
        } else if (c == 0x42000000) {
            return 0x45000000; // return 27% opacity
        } else if (c == 0x45000000) {
            return 0x47000000; // return 28% opacity
        } else if (c == 0x47000000) {
            return 0x4A000000; // return 29% opacity
        } else if (c == 0x4A000000) {
            return 0x4D000000; // return 30% opacity
        } else if (c == 0x4D000000) {
            return 0x4F000000; // return 31% opactiy
        } else if (c == 0x4F000000) {
            return 0x52000000; // return 32% opacity
        } else if (c == 0x52000000) {
            return 0x54000000; // return 33% opacity
        } else if (c == 0x54000000) {
            return 0x57000000; // return 34% opacity
        } else if (c == 0x57000000) {
            return 0x59000000; // return 35% opacity
        } else if (c == 0x59000000) {
            return 0x5C000000; // return 36% opacity
        } else if (c == 0x5C000000) {
            return 0x5E000000; // return 37% opacity
        } else if (c == 0x5E000000) {
            return 0x61000000; // return 38% opacity
        } else if (c == 0x61000000) {
            return 0x63000000; // return 39% opacity
        } else if (c == 0x63000000) {
            return 0x66000000; // return 40% opactiy
        } else if (c == 0x66000000) {
            return 0x69000000; // return 41% opacity
        } else if (c == 0x69000000) {
            return 0x6B000000; // return 42% opacity
        } else if (c == 0x6B000000) {
            return 0x6E000000; // return 43% opacity
        } else if (c == 0x6E000000) {
            return 0x70000000; // return 44% opacity
        } else if (c == 0x70000000) {
            return 0x73000000; // return 45% opacity
        } else if (c == 0x73000000) {
            return 0x75000000; // return 46% opacity
        } else if (c == 0x75000000) {
            return 0x78000000; // return 47% opacity
        } else if (c == 0x78000000) {
            return 0x7A000000; // return 48% opacity
        } else if (c == 0x7A000000) {
            return 0x7D000000; // return 49%  opactiy
        } else if (c == 0x7D000000) {
            return 0x80000000; // return 50% opacity
        } else if (c == 0x80000000) {
            return 0x82000000; // return 51% opacity
        } else if (c == 0x82000000) {
            return 0x85000000; // return 52% opacity
        } else if (c == 0x85000000) {
            return 0x87000000; // return 53% opacity
        } else if (c == 0x87000000) {
            return 0x8A000000; // return 54% opacity
        } else if (c == 0x8A000000) {
            return 0x8C000000; // return 55% opacity
        } else if (c == 0x8C000000) {
            return 0x8F000000; // return 56% opacity
        } else if (c == 0x8F000000) {
            return 0x91000000; // return 57% opacity
        } else if (c == 0x91000000) {
            return 0x94000000; // return 58% opactiy
        } else if (c == 0x94000000) {
            return 0x96000000; // return 59% opacity
        } else if (c == 0x96000000) {
            return 0x99000000; // return 60% opacity
        } else if (c == 0x99000000) {
            return 0x9C000000; // return 61% opacity
        } else if (c == 0x9C000000) {
            return 0x9E000000; // return 62% opacity
        } else if (c == 0x9E000000) {
            return 0xA1000000; // return 63% opacity
        } else if (c == 0xA1000000) {
            return 0xA3000000; // return 64% opacity
        } else if (c == 0xA3000000) {
            return 0xA6000000; // return 65% opacity
        } else if (c == 0xA6000000) {
            return 0xA8000000; // return 66% opacity
        } else if (c == 0xA8000000) {
            return 0xAB000000; // return 67% opactiy
        } else if (c == 0xAB000000) {
            return 0xAD000000; // return 68% opacity
        } else if (c == 0xAD000000) {
            return 0xB0000000; // return 69% opacity
        } else if (c == 0xB0000000) {
            return 0xB3000000; // return 70% opacity
        } else if (c == 0xB3000000) {
            return 0xB5000000; // return 71% opacity
        } else if (c == 0xB5000000) {
            return 0xB8000000; // return 72% opacity
        } else if (c == 0xB8000000) {
            return 0xBA000000; // return 73% opacity
        } else if (c == 0xBA000000) {
            return 0xBD000000; // return 74% opacity
        } else if (c == 0xBD000000) {
            return 0xBF000000; // return 75% opacity
        } else if (c == 0xBF000000) {
            return 0xC2000000; // return 76% opactiy
        } else if (c == 0xC2000000) {
            return 0xC4000000; // return 77% opacity
        } else if (c == 0xC4000000) {
            return 0xC7000000; // return 78% opacity
        } else if (c == 0xC7000000) {
            return 0xC9000000; // return 79% opacity
        } else if (c == 0xC9000000) {
            return 0xCC000000; // return 80% opacity
        } else if (c == 0xCC000000) {
            return 0xCF000000; // return 81% opacity
        } else if (c == 0xCF000000) {
            return 0xD1000000; // return 82% opacity
        } else if (c == 0xD1000000) {
            return 0xD4000000; // return 83% opacity
        } else if (c == 0xD4000000) {
            return 0xD6000000; // return 84% opacity
        } else if (c == 0xD6000000) {
            return 0xD9000000; // return 85% opactiy
        } else if (c == 0xD9000000) {
            return 0xDB000000; // return 86% opacity
        } else if (c == 0xDB000000) {
            return 0xDE000000; // return 87% opacity
        } else if (c == 0xDE000000) {
            return 0xE0000000; // return 88% opacity
        } else if (c == 0xE0000000) {
            return 0xE3000000; // return 89% opacity
        } else if (c == 0xE3000000) {
            return 0xE6000000; // return 90% opacity
        } else if (c == 0xE6000000) {
            return 0xE8000000; // return 91% opacity
        } else if (c == 0xE8000000) {
            return 0xEB000000;  // return 92% opacity
        } else if (c == 0xEB000000) {
            return 0xED000000; // return 93% opacity
        } else if (c == 0xED000000) {
            return 0xF0000000; // return 94% opactiy
        } else if (c == 0xF0000000) {
            return 0xF2000000; // return 95% opacity
        } else if (c == 0xF2000000) {
            return 0xF5000000; // return 96% opacity
        } else if (c == 0xF5000000) {
            return 0xF7000000; // return 97% opacity
        } else if (c == 0xF7000000) {
            return 0xFA000000; // return 98% opacity
        } else if (c == 0xFA000000) {
            return 0xFC000000; // return 99% opacity
        } else if (c == 0xFC000000) {
            return 0xFF000000; // return 100% opacity
        } else if (c == 0xFF000000) {
            return 0xFF000000; //return black if its already black
        } else {
            return - 1;// returning -1, put in a TRY/CATCH
        }

    }

    /**
     * Grow function. Assigns each pixel that has a "True" value to the next step in
     * transparency. Needs adjusting still. Simple Method.
     */
    public void grow(PImage img) {
        for (int i = 0; i < updater.length; i++) {
            if (updater[i] == 1) {
                img.pixels[i] = colorStep(img.pixels[i]);
                updater[i] = 0;
                counter--;
            } else {
                updater[i] = 0;
            }
        }
    }
}
