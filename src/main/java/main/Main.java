package main;

import javax.swing.JFrame;

public class Main {

    public static void main (String[] args) {

        JFrame window = new JFrame("MyTetris"); // window title
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // discard window properly
        window.setResizable(false); // fixed window size
//        window.setResizable(true);

        // Add GamePanel to the window
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack(); // the size of the GamePanel becomes the size of the window. Basically, the window adapts to the panel size

        window.setLocationRelativeTo(null); // no default location on screen
        window.setVisible(true); // make it possible to view the window
    }

}
