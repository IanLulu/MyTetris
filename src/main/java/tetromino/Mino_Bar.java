package tetromino;

import java.awt.*;

public class Mino_Bar extends Tetromino {

    // constructor
    public Mino_Bar() {
        create(Color.cyan);
    }

    public void setXY(int x, int y) {
        // (welp no cyan block. Again, different emoji)
        // 🌐🌐🌐🌐
        // b[1], b[0], b[2], b[3]
        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x - Block.SIZE;
        b[1].y = b[0].y;
        b[2].x = b[0].x + Block.SIZE;
        b[2].y = b[0].y;
        b[3].x = b[0].x + Block.SIZE * 2;
        b[3].y = b[0].y;
    }

    public void getDirection1() {
        // this
        // 🌐🌐🌐🌐 orientation
        //
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x - Block.SIZE;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x + Block.SIZE;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x + Block.SIZE * 2;
        tempB[3].y = b[0].y;

        updateXY(1);
    }
    public void getDirection2() {
        // 🌐 [1] this
        // 🌐 b[0] orientation
        // 🌐 [2]
        // 🌐 [3]
        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y - Block.SIZE;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y + Block.SIZE;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y + Block.SIZE * 2;

        updateXY(2);
    }
    // Bar tetromino only has 2 orientations so for directions 3 & 4, can just call the previous 2 functions
    public void getDirection3() {
        getDirection1();
    }
    public void getDirection4() {
        getDirection2();
    }

}
