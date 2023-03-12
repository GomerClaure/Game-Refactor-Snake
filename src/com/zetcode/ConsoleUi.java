package com.zetcode;

import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.concurrent.Delayed;

import javax.swing.Timer;

public class ConsoleUi {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    // private final int RAND_POS = 29;
    private final long DELAY_S = 1;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    // private Image ball;
    // private Image apple;
    // private Image head;
    public LogicaDelJuego logicaTablero;

    public ConsoleUi(){
        logicaTablero = new LogicaDelJuego(3); 
        initConsole();
    }

    public void initConsole(){
        while(logicaTablero.isInGame()){
            // TimeUnit.SECONDS.sleep();
            imprimirJuego();
            inGame = logicaTablero.realizarMovimiento(leftDirection, rightDirection, upDirection, downDirection);
        }
        
    }

    public void imprimirJuego(){
        String [] tablero = new String[]
        {"________________________________",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "|******************************|",
        "--------------------------------",
        };
        HashMap<String,int[]> bodySnake = logicaTablero.getBodySnakePos();
        if (logicaTablero.isInGame()) {
            //Colocar manzana
            int manzanaX = logicaTablero.getApplePosX()/10+1;
            int manzanaY = logicaTablero.getApplePosY()/10+1;
            StringBuffer filaManzana = new StringBuffer(tablero[manzanaY]);
            filaManzana.delete(1, 2);//Borro un espacio de la fila para insertar el caracter de la manzana
            filaManzana.insert(manzanaX, "M");
            System.out.println("ManzanaX: "+manzanaX+"  ManzanaY:"+ manzanaY);
            tablero[manzanaY] = filaManzana.toString();
            //Colocar gusano
            int [] posCuerpoX = bodySnake.get("x");
            int [] posCuerpoY = bodySnake.get("y");
            for (int z = 0; z < logicaTablero.getSnakeSize(); z++) {
                
                if (z == 0) {
                    // System.out.println("X: "+bodySnake.get("x")[z]+" Y: "+bodySnake.get("y")[z]);
                    StringBuffer filaCabeza = new StringBuffer(tablero[posCuerpoY[z]/10+1]);
                    filaCabeza.delete(1, 2);//Borro un espacio de la fila para insertar el caracter de la manzana
                    filaCabeza.insert(posCuerpoX[z]/10+1, "0");
                    tablero[posCuerpoY[z]/10+1] = filaCabeza.toString();
                } else {
                    StringBuffer filaCuerpo = new StringBuffer(tablero[posCuerpoY[z]/10+1]);
                    filaCuerpo.delete(1, 2);//Borro un espacio de la fila para insertar el caracter de la manzana
                    filaCuerpo.insert(posCuerpoX[z]/10+1, "o");
                    tablero[posCuerpoY[z]/10+1] = filaCuerpo.toString();
                }
            }
             
            for (int i = 0; i <tablero.length; i++) {
                System.out.println(tablero[i]);  
                
            }
        }
        
    }
    
}
