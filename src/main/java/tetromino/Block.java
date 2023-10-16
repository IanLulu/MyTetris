package tetromino;

import java.awt.*;

public class Block extends Rectangle {

    public int x, y;
    public static final int SIZE = 30; // 30 x 30 block
    public Color c;

    public Block(Color c) {

        this.c = c;

    }

    // generates minos' color & size
    public void draw(Graphics2D g2) {
        g2.setColor(c);
        g2.fillRect(x, y, SIZE, SIZE);
    }

}