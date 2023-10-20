package tetromino;

import java.awt.*;

public class Mino_Square extends Tetromino {

    // constructor
    public Mino_Square() {
        create(Color.yellow);
    }

    public void setXY(int x, int y) {
        // 🟨🟨 b[2], b[3]
        // 🟨🟨 b[1], b[0]
        //
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x;
        b[1].y = b[0].y + Block.SIZE;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE;
        b[3].y = b[0].y + Block.SIZE;
    }

    // When you rotate this square tetromino, it will appear the same so only 1 orientation. No need to implement the getDirection functions for this piece
    public void getDirection1() {}
    public void getDirection2() {}
    public void getDirection3() {}
    public void getDirection4() {}

}
