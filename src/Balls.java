//Cheese class 
//Bounces

import java.awt.*;

public class Balls {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.

    public double xpos;                //the x position
    public double ypos;                //the y position
    public int width;
    public int height;
    public double dx;                    //the speed of the hero in the x direction
    public double dy;                    //the speed of the hero in the y direction
    public Image pic;
    public Rectangle rec;
    public int delay;
    public boolean isOnScreen;



    // METHOD DEFINITION SECTION

    //This is a constructor that takes 3 parameters.  This allows us to specify the object's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Balls(int pXpos, int pYpos) {

        xpos = pXpos;
        ypos = pYpos;
        width = 50;
        height = 50;
        dx = 5;
        dy = -5;
        rec = new Rectangle((int)xpos, (int)ypos, width, height);


    } // constructor


    public Balls(double pXpos, double pYpos, double dxSpeed, double dySpeed, Image picParameter) {

        xpos = pXpos;
        ypos = pYpos;
        width = 20;
        height = 20;
        dx = dxSpeed;
        dy = dySpeed;
        isOnScreen = false;
        pic = picParameter;
        rec = new Rectangle((int)xpos, (int)ypos, width, height);


    } // constructor


    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy


    public void move() {


        xpos =  xpos + dx;
        ypos =  ypos + dy;

        if (xpos > 1000 - width || xpos < 0) {
            dx = -dx;
        }

        if (ypos < 0) {
            dy = -dy;
        }


        //always put this after you've done all the changing of the xpos and ypos values
        rec = new Rectangle((int)xpos, (int)ypos, width - 15, height );

    }

//    public void move2() {
//        xpos = xpos + dx;
//        ypos = ypos + dy;
//
//        {
//            {
//                xpos = xpos + dx;
//                ypos = ypos + dy;
//
//                if (xpos > 1000 - width || xpos < 0) {
//                    dx = -dx;
//                }
//
//                if (ypos < 0 || ypos + height > 650) {
//                    dy = -dy;
//                }
//
//                rec = new Rectangle(xpos, ypos, width, height);
//
//            }
//        }
//    }
}


//end of the Cheese object class  definition

