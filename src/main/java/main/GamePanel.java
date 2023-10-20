package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable {

    /* screen size */
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;
    final int FPS = 60;
    Thread gameThread;
    PlayManager pm;

    public GamePanel() {

        // Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
//        this.setBackground(new Color(38, 50, 181)); // rgb value for a darkish blue color I picked
        this.setLayout(null);
        // Implement KeyListener
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true); // will only get key input when the window is "focused" (i.e. current window is the game)

        pm = new PlayManager();

    }

    public void launchGame() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        // Game Loop - update and draw the screen @ 60 times per second
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint(); // calls paintComponent method
                delta--;
            }
        }

    }

    // update info @ each refresh
    private void update() {

        // Only draw on-screen information when game is not paused
        if (KeyHandler.pausePressed == false)
            pm.update();

    }

    // draws graphics onto window per second
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g; // convert parameter to Graphics 2D (casting type I think)
        pm.draw(g2);

    }

}
