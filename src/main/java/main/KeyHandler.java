package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public static boolean upPressed, downPressed, leftPressed, rightPressed, pausePressed;

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        // can use WASD or arrows to control
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = true;
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = true;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = true;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = true;
        // can use space bar or esc key to pause
        if (code == KeyEvent.VK_SPACE || code == KeyEvent.VK_ESCAPE) {
            if (pausePressed) // (pausePressed == true) depending on condition, if pause was pressed - resume, otherwise pause
                pausePressed = false;
            else
                pausePressed = true;
        }
        /*
         * to implement in the future: hard drop key
         * if (code == KeyEvent.VK_SPACE) // instead of pausing, space bar is used for hard drop
         *      spacePressed = true;
         */

    }

    @Override
    public void keyReleased(KeyEvent e) { }

}
