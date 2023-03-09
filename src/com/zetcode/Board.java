package com.zetcode;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    // private final int RAND_POS = 29;
    private final int DELAY = 140;

    private int dots;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    public LogicaDelJuego logicaTablero;

    public Board() {
        logicaTablero = new LogicaDelJuego(); 
        initBoard();
    }
    
    private void initBoard() {
        addKeyListener(new TAdapter());
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
    }

    private void loadImages() {
        ImageIcon id = new ImageIcon("src/resources/dot.png");
        ball = id.getImage();

        ImageIcon a = new ImageIcon("src/resources/apple.png");
        apple = a.getImage();

        ImageIcon h = new ImageIcon("src/resources/head.png");
        head = h.getImage();
    }

    private void initGame() {
        dots = 3;
        for (int z = 0; z < dots; z++) {
            logicaTablero.x[z] = 50 - z * 10;
            logicaTablero.y[z] = 50;
        }
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }
    
    private void doDrawing(Graphics g) {
        if (logicaTablero.inGame) {
            System.out.println("Apple x: "+logicaTablero.apple_x+" Apple y: "+logicaTablero.apple_y);
            g.drawImage(apple, logicaTablero.apple_x, logicaTablero.apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    System.out.println("Snke size: "+ dots);
                    System.out.println("Gusano punto x:"+logicaTablero.x[z]+"Gusano punto y: "+logicaTablero.y[z]);
                    g.drawImage(head, logicaTablero.x[z], logicaTablero.y[z], this);
                } else {
                    g.drawImage(ball, logicaTablero.x[z], logicaTablero.y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {
            gameOver(g);
        }        
    }

    private void gameOver(Graphics g) {
        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            inGame = logicaTablero.realizarMovimiento(leftDirection, rightDirection, upDirection, downDirection);
            dots = logicaTablero.dots;
            // checkApple();
            // checkCollision();
            // move();
        }else{
            timer.stop();
        }

        repaint();
    }

    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
        }
    }
}
