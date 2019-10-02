package com.example.parrot.pong1;


import android.content.Context;

public class GameEngineUtils {

GameEngine gameEngine;
Context context;
public  boolean CheckIfStartingScoreIsZero(int score)
{
    gameEngine = new GameEngine(this.context);
    int startingScore = gameEngine.score;
    if(score == startingScore)
    {
        return true;
    }
    return  false;
}

    public  boolean CheckIfStartingLives(int lives)
    {
        gameEngine = new GameEngine(this.context);
        int gameInitialLives = gameEngine.lives;
        if(lives == gameInitialLives)
        {
            return true;
        }
        return  false;
    }
    public  boolean isRacketXPositionIsExact(int racketXPosition)
    {
        gameEngine = new GameEngine(this.context);
        int racketXPositionGet = gameEngine.racket.getRacketXPos();
        if(racketXPosition == racketXPositionGet)
        {
            return true;
        }
        return  false;
    }

    public  boolean isRacketYPositionIsExact(int racketYPosition)
    {
        gameEngine = new GameEngine(this.context);
        int racketYPositionGet = gameEngine.racket.getRacketYPos();
        if(racketYPosition == racketYPositionGet)
        {
            return true;
        }
        return  false;
    }

}
