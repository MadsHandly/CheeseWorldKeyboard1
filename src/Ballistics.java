//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.*;

/***
 * Step 0 for keyboard control - Import
 */
import java.awt.event.*;

/***
 * Step 1 for keyboard control - implements KeyListener
 */
public class Ballistics implements Runnable, KeyListener, MouseListener {

    //Variable Definition Section

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 660;

    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    //Declare the variables needed for images
    public Image BallsPic;
    public Image BlockPic;


    //Declare the character objects

    public Balls balls[];
    public Block blocks[];

    public int xStart = 400;
    public int yStart=630;
    public int xClick,yClick, a, b;
    public double c;
    public int Counter;

    // Main method definition
    // This is the code that runs first and automatically
    public static void main(String[] args) {
        Ballistics myApp = new Ballistics();   //creates a new instance of the game
        new Thread(myApp).start();               //creates a threads & starts up the code in the run( ) method
    }

    // Constructor Method - setup portion of the program
    // Initialize your variables and construct your program objects here.
    public Ballistics() {

        setUpGraphics();

        /***
         * Step 2 for keyboard control - addKeyListener(this) to the canvas
         */
        canvas.addKeyListener(this);

        //load images
        BallsPic = Toolkit.getDefaultToolkit().getImage("BouncyBall.png");
        BlockPic = Toolkit.getDefaultToolkit().getImage("block.png");

        //create (construct) the objects needed for the game

        blocks = new Block[9];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(  (111* i)-8, 0, 0, 0, BlockPic, 100);
        }
        balls = new Balls[50];
        for (int i = 0; i < balls.length; i++) {
            balls[i] = new Balls(xStart, yStart, 0, 0, BallsPic);
           // balls[i].delay = 50+10*i;//0 + 5 * i;
        }
        //650 + (i * 9)


    } // CheeseWorld()


//*******************************************************************************
//User Method Section

    // main thread
    // this is the code that plays the game after you set things up
    public void moveThings() {
        for (int i = 0; i < balls.length; i++) {
            balls[i].move();
//            if(balls[i].ypos>=650){
//                balls[i].xpos=xStart;
//            }
            //stopping ball at  bottom of the screen
            if (balls[i].dy > 0 && balls[i].ypos > (630)){
                balls[i].dy = 0;
                balls[i].dx = 0;
                xStart=balls[i].xpos;
                yStart=balls[i].ypos;
                System.out.println("xStarting Point: " + xStart);
                System.out.println("yStarting Point: " + yStart);
            }

        }


    }

    public void collisions() {

        for (int r = 0; r <blocks.length; r++) {
            for (int i = 0; i < balls.length; i++) {
                if (balls[i].rec.intersects(blocks[r].rec)) {
                    balls[i].dy = Math.abs(balls[i].dy);

                    blocks[r].counter = blocks[r].counter - 1;
                }
            }
        }
    }




    public void checkIntersections() {

    }

    public void run() {
        while (true) {
            moveThings();           //move all the game objects
            checkIntersections();   // check character crashes
            render();               // paint the graphics
            pause(20);         // sleep for 20 ms
            collisions();
        }
    }

    //paints things on the screen using bufferStrategy
    public void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draw characters to the screen


       for (int i = 0; i < balls.length; i++) {

           if (balls[i].delay == 0) {
               g.drawImage(balls[i].pic, balls[i].xpos, balls[i].ypos, balls[i].width, balls[i].height, null);
               g.drawRect(balls[i].rec.x, balls[i].rec.y, balls[i].rec.width, balls[i].rec.height);
           }
//           else{
//               balls[i].delay--;
//           }
       }
        g.setColor(Color.CYAN); //sets the color of the pen
        g.setFont(new Font("TimesRoman", Font.BOLD, 15)); //sets the font of the text
       for(int i = 0; i < blocks.length; i++){
           g.drawImage(blocks[i].pic, blocks[i].xpos, blocks[i].ypos, blocks[i].width, blocks[i].height, null);
           g.drawString(String.valueOf(blocks[i].counter), blocks[i].xpos+(blocks[i].width/2), blocks[i].ypos+ (2*blocks[i].height/4));

       }


        // takes the counter, converts it to a string and prints it at Block1 location



        g.dispose();
        bufferStrategy.show();
    }

    /***
     * Step 3 for keyboard control - add required methods
     * You need to have all 3 even if you aren't going to use them all
     */
    public void keyPressed(KeyEvent event) {
        //This method will do something whenever any key is pressed down.
        //Put if( ) statements here
        char key = event.getKeyChar();     //gets the character of the key pressed
        int keyCode = event.getKeyCode();  //gets the keyCode (an integer) of the key pressed
        System.out.println("Key Pressed: " + key + "  Code: " + keyCode);

    }//keyPressed()

    public void keyReleased(KeyEvent event) {
        char key = event.getKeyChar();
        int keyCode = event.getKeyCode();
//        This method will do something when a key is released
//        if (keyCode == 68) { // d
//            user.right = false;
//        }
//        if (keyCode == 65) { // a
//            user.left = false;
//        }
//        if (keyCode == 83) { // s
//            user.down = false;
//        }
//        if (keyCode == 87) { // w
//            user.up = false;
        }


    //keyReleased()

    public void keyTyped(KeyEvent event) {
        // handles a press of a character key (any key that can be printed but not keys like SHIFT)
        // we won't be using this method, but it still needs to be in your program
    }//keyTyped()


    //Graphics setup method
    public void setUpGraphics() {
        frame = new JFrame("CheeseWorld");   //Create the program window or frame.  Names it.

        panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
        panel.setLayout(null);   //set the layout

        // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
        // and trap input events (Mouse and Keyboard events)
        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);
        canvas.addMouseListener(this);

        panel.add(canvas);  // adds the canvas to the panel.

        // frame operations
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
        frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
        frame.setResizable(false);   //makes it so the frame cannot be resized
        frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!

        // sets up things so the screen displays images nicely.
        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();
        System.out.println("DONE graphic setup");

    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time) {
        //sleep
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {

        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {


    }

    @Override
    public void mousePressed(MouseEvent e) {
//       System.out.println("X: "+e.getX());
//        System.out.println("Y: "+e.getY());
//        xClick=e.getX();
//        yClick=e.getY();
//
//        a =  (yStart-yClick);
//        b =(xStart-xClick);
//        c = Math.sqrt((a*a) + (b*b));
//        System.out.println("c"+ c);
//        System.out.println("b"+ b );
//        System.out.println("a"+ a );
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("X: "+e.getX());
        System.out.println("Y: "+e.getY());
        xClick=e.getX();
        yClick=e.getY();

        a = (yStart-yClick);
        b = (xStart-xClick);
        c = Math.sqrt((a*a) + (b*b));
        System.out.println("c"+ c);
        System.out.println("b"+ b );
        System.out.println("a"+ a );

        for (int i = 0; i < balls.length; i++) {
            balls[i].dy = -(a * (3/c));
            balls[i].dx = -(b * (3/c));
        }//-(a*(3/c))

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}//class

// Make variable for how many shots
// Starting number of block is ^+1

