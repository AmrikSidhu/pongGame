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
    Random rand = new Random();
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

    int score = 0;
    int lives = 5;
    int racketLengh =300;

    Ball ball;
    Racket racket;

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

ball = new Ball(this.screenWidth/2,this.screenHeight/2,40);

racket = new Racket(400,1500);
        this.printScreenInfo();

        // @TODO: Add your sprites to this section
        // This is optional. Use it to:
        //  - setup or configure your sprites
        //  - set the initial position of your sprites
//        this.ballXPosition = this.screenWidth / 2;
//        this.ballYPosition = this.screenHeight /2;

//        this.racketXPosition = 400;
//        this.racketYPosition =1500;
        // @TODO: Any other game setup stuff goes here


    }

    public GameEngine(Context context) {
        super(context);
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

//creating functions for oops
    public void ballMoveUp()
    {
        ball.setBallypos(ball.getBallypos()-20);
        if(ball.getBallypos() <=0)
        {
            directionBallIsMoving ="down";
        }
    }
    public void ballMoveDown()
    {
        ball.setBallypos(ball.getBallypos()+20);
        if(ball.getBallypos()>this.screenHeight){
            directionBallIsMoving = "up";

        }

    }

    public void racketMoveRight()
    {
        if(racket.getRacketXPos() < screenWidth-racketLengh){
            racket.setRacketXPos(racket.getRacketXPos()+10);
        }
    }
    public void racketMoveLeft()
    {
        if(racket.getRacketXPos() > 0){
            racket.setRacketXPos(racket.getRacketXPos()-10);

        }
    }
    public  void  ballHitsTheRacket()
    {
        directionBallIsMoving ="up";
        this.score = this.score+50;

    }

    public void ballHittBottom()
    {

        ball.setBallXpos(this.screenWidth/2);
        ball.setBallypos(0);
        personTapped = "";
        racket.setRacketXPos(400);
        racket.setRacketYPos(1500);

        directionBallIsMoving ="down";
        score =score-35;
    }
    public void scoreNegative()
    {
        gameIsRunning =false;
        gameThread.interrupt();
    }

    // ********************************************************************************** 5
    // 1. Tell Android the (x,y) positions of your sprites
    public void updatePositions() {

        if(directionBallIsMoving.contentEquals("down")){

            ballMoveDown();
        }
        if(directionBallIsMoving.contentEquals("up"))
        {

            ballMoveUp();
       }

        if (personTapped.contentEquals("right")){

            racketMoveRight();
}


        else if (personTapped.contentEquals("left")){

            racketMoveLeft();

        }
        if (ball.getBallypos() >= racket.getRacketYPos()-40 && ball.getBallXpos() > racket.getRacketXPos() && ball.getBallXpos() < racket.getRacketXPos()+racketLengh) {

            // ball is touching racket
            Log.d(TAG, "Ball touched with racket");
          this.score = this.score +2;
            ballHitsTheRacket();
        }
        if(ball.getBallypos() >= screenHeight-30)
        {
            ballHittBottom();
        }
        if(score < 0)
        {
           scoreNegative();
        }

        // DEBUG - by outputing current positiong
        Log.d(TAG, "XPos: " + ball.getBallXpos());
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
            //canvas.drawColor(this.score);
            this.canvas.drawColor(Color.argb(255,0,0,255));
            paintbrush.setColor(Color.WHITE);

            //@TODO: Draw the sprites (rectangle, circle, etc)

            // 1. Draw the ball
            this.canvas.drawRect(
                    ball.getBallXpos(),
                    ball.getBallypos(),
                    ball.getBallXpos() + 50,
                    ball.getBallypos() + 50,
                    paintbrush);
            // this.canvas.drawRect(left, top, right, bottom, paintbrush);
paintbrush.setColor(Color.YELLOW);
this.canvas.drawRect(racket.getRacketXPos(),
        racket.getRacketYPos(),
        racket.getRacketXPos()+racketLengh,
        racket.getRacketYPos()+40,
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