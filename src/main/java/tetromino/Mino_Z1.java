package tetromino;

import java.awt.*;

public class Mino_Z1 extends Tetromino {

    // constructor
    public Mino_Z1() {
        create(Color.red);
    }

    public void setXY(int x, int y) {
        // 🟥🟥 b[2], b[3]
        // ⬛️🟥🟥 b[0], b[1]
        //
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x + Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x;
        b[2].y = b[0].y - Block.SIZE;
        b[3].x = b[0].x - Block.SIZE;
        b[3].y = b[0].y - Block.SIZE;
    }

    public void getDirection1() {
        // 🟥🟥 this
        // ⬛🟥🟥 orientation
        //
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x + Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y - Block.SIZE;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y - Block.SIZE;

        updateXY(1);
    }
    public void getDirection2() {
        // ⬛🟥 this
        // 🟥🟥 orientation
        // 🟥
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x - Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x - Block.SIZE;
        tempB[3].y = b[0].y + Block.SIZE;

        updateXY(2);
    }
    // similar to the bar tetromino, this piece only has two orientations when rotated
    public void getDirection3() {
        getDirection1();
    }
    public void getDirection4() {
        getDirection2();
    }

}
