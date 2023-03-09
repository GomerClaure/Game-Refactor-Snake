package com.zetcode;

import java.util.HashMap;

public class LogicaDelJuego {
    private final int B_WIDTH;
    private final int B_HEIGHT;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;
    private boolean inGame = true;

    public LogicaDelJuego(int dots){
        this.dots = dots;
        B_WIDTH = 300;
        B_HEIGHT = 300;
        initGame();
    }
    public boolean realizarMovimiento(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection){
        checkApple();
        checkCollision();
        move(leftDirection, rightDirection, upDirection, downDirection);
        return inGame;
    }
    private void initGame() {
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }
        
        locateApple();
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();
        }
    }

    private void move(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {
        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        // if (!inGame) {
        //     timer.stop();
        // }
    }

    private void locateApple() {
        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    public HashMap<String,int[]> getBodySnakePos(){
        HashMap<String,int[]> nuevo = new HashMap<String,int[]>();
        nuevo.put("x", x);
        nuevo.put("y", y);
        return nuevo;
    }
    public int getApplePosX(){
        return apple_x;
    }
    public int getApplePosY(){
        return apple_y;
    }
    public boolean isInGame(){
        return inGame;
    }
    public int getSnakeSize(){
        return dots;
    }
    // public int[] getApplePos(){
    //     return new int[]{apple_x, apple_y};
    // }
}
