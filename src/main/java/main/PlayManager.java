package main;


import tetromino.Block;
import tetromino.Mino_L1;
import tetromino.Tetromino;

import java.awt.*; // FUCK YOU INTELLIJ FUCK FUCK FUCK

/*
    Draws the play area
    Manages tetrominoes
    Handles gameplay actions (deleting lines, adding scores, etc.)
 */
public class PlayManager {

    // Main play area
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    // Tetromino
    Tetromino currentMino;
    // store starting x & y
    final int MINO_START_X;
    final int MINO_START_Y;

    // Others
    public static int dropInterval = 60; // tetromino drops in every 60 frames or every second

    // constructor 4 class
    public PlayManager() {

        // Main play area frame
        left_x = (GamePanel.WIDTH / 2) - (WIDTH / 2); // 1280/2 (center of the window) - 360/2 = 460
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        // tetrominoes spawn near the center top of the play area
        MINO_START_X = left_x + (WIDTH / 2) - Block.SIZE;
        MINO_START_Y = top_y + Block.SIZE;

        // set the starting tetromino
        currentMino = new Mino_L1();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

    }

    public void update() {

        currentMino.update();

    }

    public void draw(Graphics2D g2) {

        // Draw main play area frame
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f)); // 4f = 4 pixels (?)
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        // Draw next tetromino frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2.drawRect(x, y, 200, 200);
        g2.setFont(new Font("Helvetica Monospaced", Font.PLAIN, 30));
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 60, y + 60);

        // Draw the current tetromino
        if (currentMino != null) {
            currentMino.draw(g2);
        }

    }

}
