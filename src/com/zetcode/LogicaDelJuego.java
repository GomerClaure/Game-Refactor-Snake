package com.zetcode;

import java.util.HashMap;

public class LogicaDelJuego {
    private final int ANCHO_TABLERO;
    private final int ALTO_TABLERO;
    private final int MAX_SNAKE_SIZE = 90;
    private final int RAND_POS = 29;

    private final int x[] = new int[MAX_SNAKE_SIZE];
    private final int y[] = new int[MAX_SNAKE_SIZE];

    private int snakeSize;

    private int apple_x;
    private int apple_y;
    private boolean inGame = true;

    public LogicaDelJuego(int dots){
        this.snakeSize = dots;
        ANCHO_TABLERO = 30;
        ALTO_TABLERO = 30;
        initGame();
    }
    public boolean realizarMovimiento(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection){
        checkApple();
        checkCollision();
        move(leftDirection, rightDirection, upDirection, downDirection);
        return inGame;
    }
    private void initGame() {
        for (int z = 0; z < snakeSize; z++) {
            x[z] = 5 - z ;
            y[z] = 5;
        }
        
        locateApple();
    }

    private void checkApple() {
        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            snakeSize++;
            locateApple();
        }
    }

    private void move(boolean leftDirection, boolean rightDirection, boolean upDirection, boolean downDirection) {
        for (int z = snakeSize; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= 1;
        }

        if (rightDirection) {
            x[0] += 1;
        }

        if (upDirection) {
            y[0] -= 1;
        }

        if (downDirection) {
            y[0] += 1;
        }
    }

    private void checkCollision() {
        for (int z = snakeSize; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= ALTO_TABLERO) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= ANCHO_TABLERO) {
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
        apple_x = r;

        r = (int) (Math.random() * RAND_POS);
        apple_y = r;
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
        return snakeSize;
    }
    // public int[] getApplePos(){
    //     return new int[]{apple_x, apple_y};
    // }
}
