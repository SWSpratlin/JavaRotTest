package Main;

import processing.core.PApplet;
import processing.core.PImage;

public class Walker {
    private final PApplet main;
    int x;
    int y;
    int c;
    int nextStep;
    int stepX;
    int stepY;

    // Constructors determine where the walker STARTS
    public Walker(PApplet sketch, int startX, int startY) {
        this.main = sketch;
        x = startX;
        y = startY;
    }

    //display function (this is where the magic will happen)
    public void displayWalker(PImage walkedImage, int newColor) {

        c = newColor;

        // 0xFF000000 should be the Hex Color for black
        try {
            nextStep = (y * main.width) + x;
            walkedImage.pixels[nextStep] = c;
            if ((nextStep + main.width + 1) < walkedImage.pixels.length) {
                walkedImage.pixels[nextStep + main.width - 1] = c;
                walkedImage.pixels[nextStep + main.width] = c;
                walkedImage.pixels[nextStep + main.width + 1] = c;
            }
            if ((nextStep - main.width - 1) > 0) {
                walkedImage.pixels[nextStep - main.width - 1] = c;
                walkedImage.pixels[nextStep - main.width] = c;
                walkedImage.pixels[nextStep - main.width + 1] = c;
            }
            if ((nextStep - 1) > 0) {
                walkedImage.pixels[nextStep - 1] = c;
            }
            if ((nextStep + 1) < walkedImage.pixels.length) {
                walkedImage.pixels[nextStep + 1] = c;
            }
        } catch (Exception e) {
            System.out.println("Out Of Bounds");
            x = (int)main.random(main.width);
            y = (int)main.random(main.height);
        }
    }

    //step function
    public void step(PImage walkedImage) {

        int[] numSet = new int[]{-2, 0, 2};

        stepX = numSet[(int)(main.random(3))];
        stepY = numSet[(int)(main.random(3))];

        int newStep = ((y + stepY) * main.width) + (x + stepX);
        int counter = 0;

        try {
            while (walkedImage.pixels[newStep] == c) {

                stepX = numSet[(int)(main.random(3))];
                stepY = numSet[(int)(main.random(3))];

                newStep = ((stepY + y) * main.width) + (stepX + x);

                counter++;

                if (counter >= 8) {
                    if (stepX > 1) {
                        x++;
                    } else if (stepX < -1) {
                        x--;
                    }
                    if (stepY > 1) {
                        y++;
                    } else if (stepY < -1) {
                        y--;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("hit the edge");
            x = (int)(main.random(main.width));
            y = (int)(main.random(main.height));
        }


        x += stepX;
        y += stepY;

    }

    private void _switcherStatement() {
        int bigStepNumber = (int)(main.random(1, 20));
        switch (bigStepNumber) {
            case 1:
                stepX = 3;
                stepY = 2;
                break;
            case 2:
                stepX = 3;
                stepY = 1;
                break;
            case 3:
                stepX = 3;
                stepY = 0;
                break;
            case 4:
                stepX = 3;
                stepY = -1;
                break;
            case 5:
                stepX = 3;
                stepY = -2;
                break;
            case 6:
                stepY = 3;
                stepX = -2;
                break;
            case 7:
                stepY = 3;
                stepX = -1;
                break;
            case 8:
                stepY = 3;
                stepX = 0;
                break;
            case 9:
                stepY = 3;
                stepX = 1;
                break;
            case 10:
                stepY = 3;
                stepX = 2;
                break;
            case 11:
                stepX = -3;
                stepY = 2;
                break;
            case 12:
                stepX = -3;
                stepY = 1;
                break;
            case 13:
                stepX = -3;
                stepY = 0;
                break;
            case 14:
                stepX = -3;
                stepY = -1;
                break;
            case 15:
                stepX = -3;
                stepY = -2;
                break;
            case 16:
                stepY = -3;
                stepX = -2;
                break;
            case 17:
                stepY = -3;
                stepX = -1;
                break;
            case 18:
                stepY = -3;
                stepX = 0;
                break;
            case 19:
                stepY = -3;
                stepX = 1;
                break;
            case 20:
                stepY = -3;
                stepX = 2;
                break;
        }
    }

    public void bigStep(PImage walkedImage, int bigColor) {
        _switcherStatement();

        int newStep = ((y + stepY) * main.width) + (x + stepX);
        int counter = 0;

        try {
            while (walkedImage.pixels[newStep] == c) {

                _switcherStatement();

                newStep = ((stepY + y) * main.width) + (stepX + x);

                counter++;

                if (counter >= 20) {
                    if (stepX == 3) {
                        x++;
                    } else if (stepX == -3) {
                        x--;
                    } else if (stepY == 3) {
                        y++;
                    } else if (stepY == -3) {
                        y--;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("hit the edge");
            x = (int)(main.random(main.width));
            y = (int)(main.random(main.height));
        }

        x += stepX;
        y += stepY;

        c = bigColor;

        // 0xFF000000 should be the Hex Color for black
        try {

            nextStep = (y * main.width) + x;

            walkedImage.pixels[nextStep] = c;

            if ((nextStep + (main.width * 2) + 2) < walkedImage.pixels.length) {
                walkedImage.pixels[nextStep + (main.width * 2) - 2] = c;
                walkedImage.pixels[nextStep + (main.width * 2) - 1] = c;
                walkedImage.pixels[nextStep + (main.width * 2)] = c;
                walkedImage.pixels[nextStep + (main.width * 2) + 1] = c;
                walkedImage.pixels[nextStep + (main.width * 2) + 2] = c;
                walkedImage.pixels[nextStep + main.width - 2] = c;
                walkedImage.pixels[nextStep + main.width - 1] = c;
                walkedImage.pixels[nextStep + main.width] = c;
                walkedImage.pixels[nextStep + main.width + 1] = c;
                walkedImage.pixels[nextStep + main.width + 2] = c;
            }
            if ((nextStep - (main.width * 2) - 2) > 0) {
                walkedImage.pixels[nextStep - (main.width * 2) - 2] = c;
                walkedImage.pixels[nextStep - (main.width * 2) - 1] = c;
                walkedImage.pixels[nextStep - (main.width * 2)] = c;
                walkedImage.pixels[nextStep - (main.width * 2) + 1] = c;
                walkedImage.pixels[nextStep - (main.width * 2) + 2] = c;
                walkedImage.pixels[nextStep - main.width - 2] = c;
                walkedImage.pixels[nextStep - main.width - 1] = c;
                walkedImage.pixels[nextStep - main.width] = c;
                walkedImage.pixels[nextStep - main.width + 1] = c;
                walkedImage.pixels[nextStep - main.width + 2] = c;
            }
            if ((nextStep - 2) > 0) {
                walkedImage.pixels[nextStep - 1] = c;
                walkedImage.pixels[nextStep - 2] = c;
            }
            if ((nextStep + 2) < walkedImage.pixels.length) {
                walkedImage.pixels[nextStep + 1] = c;
                walkedImage.pixels[nextStep + 2] = c;
            }
        } catch (Exception e) {
            System.out.println("Out Of Bounds");
            x = (int)(main.random(main.width));
            y = (int)(main.random(main.height));
        }
    }

}
