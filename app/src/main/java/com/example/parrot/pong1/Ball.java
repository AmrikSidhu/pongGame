package com.example.parrot.pong1;

public class Ball {
        public int ballXpos;
        public int ballypos;
        public  int ballWidth;

    public Ball() {
    }

    public Ball(int ballXpos, int ballypos, int ballWidth) {
        this.ballXpos = ballXpos;
        this.ballypos = ballypos;
        this.ballWidth = ballWidth;
    }

    public int getBallXpos() {
        return ballXpos;
    }

    public void setBallXpos(int ballXpos) {
        this.ballXpos = ballXpos;
    }

    public int getBallypos() {
        return ballypos;
    }

    public void setBallypos(int ballypos) {
        this.ballypos = ballypos;
    }

    public int getBallWidth() {
        return ballWidth;
    }

    public void setBallWidth(int ballWidth) {
        this.ballWidth = ballWidth;
    }
}
