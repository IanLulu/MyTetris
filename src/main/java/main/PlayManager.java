package main;

import tetromino.Block;
import tetromino.Mino_Bar;
import tetromino.Mino_L1;
import tetromino.Mino_L2;
import tetromino.Mino_Square;
import tetromino.Mino_T;
import tetromino.Mino_Z1;
import tetromino.Mino_Z2;
import tetromino.Tetromino;

import java.awt.*; // FUCK YOU INTELLIJ FUCK FUCK FUCK
import java.util.ArrayList;
import java.util.Random;

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
    Tetromino nextMino;
    final int NEXT_MINO_X;
    final int NEXT_MINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();


    // Others
    public static int dropInterval = 60; // tetromino drops in every 60 frames or every second (game is set to 60 fps {@see GamePanel#FPS FPS})

    // Effects
    boolean effectsCounterOn;
    int effectsCounter;
    ArrayList<Integer> effectY = new ArrayList<>();

    // Game over
    boolean gameOver;

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

        // where next 'mino spawn ( ? )
        NEXT_MINO_X = right_x + 175;
        NEXT_MINO_Y = top_y + 500;

        // set the starting tetromino
//        currentMino = new Mino_L1();
        currentMino = pickMino();
        currentMino.setXY(MINO_START_X, MINO_START_Y);

        // create next tetromino
        nextMino = pickMino();
        nextMino.setXY(NEXT_MINO_X, NEXT_MINO_Y);

    }

    private Tetromino pickMino() {

        // Pick a random tetromino
        Tetromino mino = null;
        int i = new Random().nextInt(7);

//        switch (i) {
//            case 0: mino = new Mino_L1(); break;
//            case 1: mino = new Mino_L2(); break;
//            case 2: mino = new Mino_Z1(); break;
//            case 3: mino = new Mino_Z2(); break;
//            case 4: mino = new Mino_T(); break;
//            case 5: mino = new Mino_Bar(); break;
//            case 6: mino = new Mino_Square(); break;
//        }
        // switch expression:
        mino = switch (i) {
            case 0 -> new Mino_L1();
            case 1 -> new Mino_L2();
            case 2 -> new Mino_Z1();
            case 3 -> new Mino_Z2();
            case 4 -> new Mino_T();
            case 5 -> new Mino_Bar();
            case 6 -> new Mino_Square();
            default -> mino;
        };

        return mino;

    }

    public void update() {

        // Check if the current tetromino is active
        if (!currentMino.active) { // simplified currentMino.active == false
            // if the 'mino is not active, put it into the staticBlocks array
            staticBlocks.add(currentMino.b[0]);
            staticBlocks.add(currentMino.b[1]);
            staticBlocks.add(currentMino.b[2]);
            staticBlocks.add(currentMino.b[3]);

            // check if the game is over
            if (currentMino.b[0].x == MINO_START_X && currentMino.b[0].y == MINO_START_Y) {
                // this means the currentMino immediately collided a block and couldn't move at all
                // so it's x & y are the same with the nextMino's
                gameOver = true; // Stop game when blocks have reached the spawn area (GAME OVER)
            }

            currentMino.deactivating = false;

            // replace the current tetromino with the next 'mino
            currentMino = nextMino;
            currentMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXT_MINO_X, NEXT_MINO_Y);

            // when a tetromino block becomes inactive, check if line(s) can be deleted
            checkDelete();
        }
        else
            currentMino.update();

    }

    private void checkDelete() {

        int x = left_x;
        int y = top_y;
        int blockCount = 0;

        while (x < right_x && y < bottom_y) {
            for (int i = 0; i < staticBlocks.size(); i++) {
                if (staticBlocks.get(i).x == x && staticBlocks.get(i).y == y)
                    blockCount++; // increase the count if there is a static block
            }

            x += Block.SIZE;

            if (x == right_x) {
                // if the blockCount equals 12, that means the current y line is all filled with blocks
                // so we can delete the blocks in the line
                if (blockCount == 12) {

                    effectsCounterOn = true;
                    effectY.add(y);

                    for (int i = staticBlocks.size() - 1; i > -1; i--) {
                        // remove all the blocks in current y line
                        if (staticBlocks.get(i).y == y)
                            staticBlocks.remove(i);
                    }

                    // a line has been deleted so need to slide down blocks that are above it
                    for (Block staticBlock : staticBlocks) { // "enhanced for loop" iterates through staticBlocks array
                        // if a block is above the current y, move it down by the block size
                        if (staticBlock.y < y)
                            staticBlock.y += Block.SIZE;
                    }
                }

                blockCount = 0;
                x = left_x;
                y += Block.SIZE;
            }
        }
    }

    public void draw(Graphics2D g2) {

        // Draw main play area frame
        g2.setColor(new Color(8, 11, 41)); // darker navyish color
        g2.fillRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);
        // border
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(4f)); // 4f = 4 pixels (?)
        g2.drawRect(left_x - 4, top_y - 4, WIDTH + 8, HEIGHT + 8);

        // Draw next tetromino frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        /* welp
        // edit: adding color
           g2.setColor(new Color(0, 0, 0, 100)); // alpha channel max value is 255
           that didn't work */
        g2.setColor(new Color(255, 255, 255, 128));
        g2.fillRect(x, y, 200, 200);
        g2.drawRect(x, y, 200, 200);
//        g2.setFont(new Font("Helvetica Monospaced", Font.PLAIN, 30));
        g2.setFont(new Font("Silver", Font.PLAIN, 64)); // changed to new font from: https://poppyworks.itch.io/silver
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString("NEXT", x + 64, y + 48);

        // Draw the current tetromino
        if (currentMino != null) {
            currentMino.draw(g2);
        }

        // Draw the next tetromino
        nextMino.draw(g2);

        // Draw staticBlocks (inactive tetrominos)
        for (int i = 0; i < staticBlocks.size(); i++)
            staticBlocks.get(i).draw(g2);

        // Draw effects
        if (effectsCounterOn) {
            effectsCounter++;

            g2.setColor(Color.white);
            for (int i = 0; i < effectY.size(); i++)
                g2.fillRect(left_x, effectY.get(i), WIDTH, Block.SIZE);

            if (effectsCounter == 10) { // 10 frames
                effectsCounterOn = false;
                effectsCounter = 0;
                effectY.clear();
            }
        }

        // Draw pause screen
        g2.setColor(new Color(204, 204, 255)); // periwinkle rgb value
        /* ^^^ source: https://stackoverflow.com/questions/42855224/how-to-add-rgb-values-into-setcolor-in-java */
        g2.setFont(g2.getFont().deriveFont(128f));
        if (KeyHandler.pausePressed && !gameOver) { // {@see GamePanel#update()
            x = left_x + 70;
            y = top_y + 320;
            g2.drawString("PAUSED", x, y);
//            g2.drawString("PAUSED\n01001101\nteste", x, y);
        }
        else if (gameOver) { // Draw game over screen
            x = left_x + 6;
            y = top_y + 320;
            g2.drawString("GAME OVER", x, y);
        }

        // Draw game title
        x = 64;
        y = top_y + 320;
        g2.setColor(Color.white);
        g2.setFont(new Font("Helvetica Monospaced", Font.ITALIC, 64));
        g2.drawString("MyTetris", x, y);

        // Draw description
        y = top_y + 360;
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 16f));
        g2.drawString("A simple recreation of the tetris game", x, y);

    }

}
