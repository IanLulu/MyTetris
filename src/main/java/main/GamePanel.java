package main;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Color;

public class GamePanel extends JPanel{

    /* screen size */
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    public GamePanel() {

        // Panel Settings
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.black);
        this.setLayout(null);
    }

}
