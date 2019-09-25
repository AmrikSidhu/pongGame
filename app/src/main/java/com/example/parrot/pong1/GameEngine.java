package com.example.parrot.pong1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.nfc.Tag;
import android.support.constraint.solver.widgets.Rectangle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameEngine extends SurfaceView implements Runnable {

    // -----------------------------------
    // ## ANDROID DEBUG VARIABLES
    // -----------------------------------

    // Android debug variables
    final static String TAG="PONG-GAME";

    // -----------------------------------
    // ## SCREEN & DRAWING SETUP VARIABLES
    // -----------------------------------

    // screen size
    int screenHeight;
    int screenWidth;
    int i;

    // game state
    boolean gameIsRunning;

    // threading
    Thread gameThread;


    // drawing variables
    SurfaceHolder holder;
    Canvas canvas;
    Paint paintbrush;



    // -----------------------------------
    // ## GAME SPECIFIC VARIABLES
    // -----------------------------------

    // ----------------------------
    // ## SPRITES
    // ----------------------------
    int ballXPosition;      // keep track of ball -x
    int ballYPosition;      // keep track of ball -y
    int racketXPosition;
    int racketYPosition;
    int score;

    // ----------------------------
    // ## GAME STATS - number of lives, score, etc
    // ----------------------------

    // ********************************************************************************** 1
    public GameEngine(Context context, int w, int h) {
        super(context);


        this.holder = this.getHolder();
        this.paintbrush = new Paint();

        this.screenWidth = w;
        this.screenHeight = h;


        this.printScreenInfo();

        // @TODO: Add your sprites to this section
        // This is optional. Use it to:
        //  - setup or configure your sprites
        //  - set the initial position of your sprites
        this.ballXPosition = this.screenWidth / 2;
        this.ballYPosition = this.screenHeight /2;

        this.racketXPosition= 400;
        this.racketYPosition =1500;
        // @TODO: Any other game setup stuff goes here


    }

    // ------------------------------
    // HELPER FUNCTIONS
    // ------------------------------

    // This funciton prints the screen height & width to the screen.
    private void printScreenInfo() {

        Log.d(TAG, "Screen (w, h) = " + this.screenWidth + "," + this.screenHeight);

    }


    // ------------------------------
    // GAME STATE FUNCTIONS (run, stop, start)
    // ------------------------------
    // ********************************************************************************** 2
    @Override
    public void run() {
        while (gameIsRunning == true) {
            this.updatePositions();
            this.redrawSprites();
            this.setFPS();
        }
    }

    // ********************************************************************************** 3
    public void pauseGame() {
        gameIsRunning = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            // Error
        }
    }


    // ********************************************************************************** 4

    public void startGame() {
        gameIsRunning = true;
        gameThread = new Thread(this);
        gameThread.start();
    }


    // ------------------------------
    // GAME ENGINE FUNCTIONS
    // - update, draw, setFPS
    // ------------------------------


    String directionBallIsMoving = "down";
    String personTapped="";



    // ********************************************************************************** 5
    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {
        // @TODO: Update the position of the sprites
//        if (directionBallIsMoving.contentEquals("right")) {
//            this.ballXPosition = this.ballXPosition + 10;
//
//            if (this.ballXPosition > this.screenWidth) {
//                directionBallIsMoving = "left";
//            }
//
//        }
//        else if (directionBallIsMoving.contentEquals("left")) {
//            this.ballXPosition = this.ballXPosition - 10;
//
//            // make ball bounce off left wall
//            // 1. detect when you reach the left wall
//            if (this.ballXPosition <= 0) {
//                // 2. change the direction of the ball
//                directionBallIsMoving = "right";
//            }
//        }

        if(directionBallIsMoving == "down"){

                    this.ballYPosition = this.ballYPosition +20;

        if(this.ballYPosition > this.screenHeight){

            directionBallIsMoving = "up";

        }

        }
        if(directionBallIsMoving == "up")
        {
            this.ballYPosition = this.ballYPosition -20;

        if(this.ballYPosition <=0)
        {
            directionBallIsMoving ="down";
        }}

        if (personTapped.contentEquals("right")){
if(racketXPosition < screenWidth-300)
            this.racketXPosition = this.racketXPosition + 10;
        }
        else if (personTapped.contentEquals("left")){
            if(racketXPosition > 0){
            this.racketXPosition = this.racketXPosition -10;
        }




        }
       // this.racketXPosition = this.racketXPosition-10;

        //racket new position
//        if (personTapped.contentEquals("right")){
//
//                this.racketXPosition = this.racketXPosition + 10;
//
//
//
//                   }
//               else if (personTapped.contentEquals("left")){
//                       this.racketXPosition = this.racketXPosition - 10;
//                   }


//        else if (racketXPosition ==0 ) {
//            this.racketXPosition = this.racketXPosition+0;
//        }

//        if (racketXPosition <= 0) {
//            this.racketXPosition = this.racketXPosition ;
//        }


//        if(ballXPosition >=0) {
//
//            this.racketXPosition = this.racketXPosition -10;
//        }

        if (ballYPosition >= this.racketYPosition-40 && this.ballXPosition > racketXPosition && ballXPosition < racketXPosition+400) {

            // ball is touching racket
            Log.d(TAG, "Ball touched with racket");
            directionBallIsMoving ="up";
            this.score = this.score+1;
            this.score = this.score+50;
            this.score = this.score +2;
        }





        // DEBUG - by outputing current positiong
        Log.d(TAG, "XPos: " + this.ballXPosition);



//        this.ballYPosition = this.ballYPosition - 10;

//        // LEFT:
//        this.ballXPosition = this.ballXPosition - 10;
//        // RIGHT:
//        this.ballXPosition = this.ballXPosition + 10;
//        // DOWN:
//        this.ballYPosition = this.ballYPosition + 10;
//        // UP:
//        this.ballYPosition = this.ballYPosition - 10;

        // @TODO: Collision detection code

    }



    // ********************************************************************************** 6
    // 2. Tell Android to DRAW the sprites at their positions
    public void redrawSprites() {
        if (this.holder.getSurface().isValid()) {
            this.canvas = this.holder.lockCanvas();

            //----------------
            // Put all your drawing code in this section

            // configure the drawing tools
            this.canvas.drawColor(Color.argb(255,250,0,255));
            paintbrush.setColor(Color.WHITE);

            //@TODO: Draw the sprites (rectangle, circle, etc)

            // 1. Draw the ball
            this.canvas.drawRect(
                    ballXPosition,
                    ballYPosition,
                    ballXPosition + 50,
                    ballYPosition + 50,
                    paintbrush);
            // this.canvas.drawRect(left, top, right, bottom, paintbrush);
paintbrush.setColor(Color.YELLOW);
this.canvas.drawRect(this.racketXPosition,
        this.racketYPosition,
        this.racketXPosition+300,
        this.racketYPosition+30,
            paintbrush);
//canvas.drawRect(400,1550,1000,1500,paintbrush);
            paintbrush.setColor(Color.BLACK);


            //@TODO: Draw game statistics (lives, score, etc)
            paintbrush.setTextSize(60);
            //canvas.drawText("Score: 25", 20, 100, paintbrush);
            paintbrush.setColor(Color.WHITE);
            canvas.drawText("Score: "+this.score, 20, 100, paintbrush);

            //----------------
            this.holder.unlockCanvasAndPost(canvas);
        }
    }

    // Sets the frame rate of the game
    public void setFPS() {
        try {
            gameThread.sleep(50);
        }
        catch (Exception e) {

        }
    }

    // ------------------------------
    // USER INPUT FUNCTIONS
    // ------------------------------



    // ********************************************************************************** 7
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int userAction = event.getActionMasked();
        //@TODO: What should happen when person touches the screen?
        if (userAction == MotionEvent.ACTION_DOWN) {
            // user pushed down on screen
            float fingerXpos =  event.getX();
            float fingerYpos =  event.getY();
            float fingerXPosition = event.getX();
            float fingerYPosition = event.getY();
            Log.d(TAG,"position on screen is: = "+fingerXpos+ " , " + fingerYpos);


            // 2. Tell Android to DRAW the sprites at their positions
            int middleOfScreen = this.screenWidth / 2;
            if (fingerXPosition <= middleOfScreen) {
                // 3. If tap is on left, racket should go left

                                      personTapped = "left";
            }
            else if (fingerXPosition > middleOfScreen) {
                // 4. If tap is on right, racket should go right
                               personTapped = "right";
            }

        }
        else if (userAction == MotionEvent.ACTION_UP) {
            // user lifted their finger
        }
        return true;
    }
}