package com.example.parrot.pong1;

public class Racket {
    public int racketXPos;
    public int racketYPos;
    public Racket() {
    }

    public Racket(int racketXPos, int racketYPos) {
        this.racketXPos = racketXPos;
        this.racketYPos = racketYPos;

    }

    public int getRacketXPos() {
        return racketXPos;
    }

    public void setRacketXPos(int racketXPos) {
        this.racketXPos = racketXPos;
    }

    public int getRacketYPos() {
        return racketYPos;
    }

    public void setRacketYPos(int racketYPos) {
        this.racketYPos = racketYPos;
    }

}

