package com.pb.ant;

import javax.swing.*;

/**
 * Created by eranh on 2/24/16.
 */
public class WorldFrame extends JFrame {
    public WorldFrame(Game game) {
        add(new BoardPanel(game));
        setResizable(false);
        pack();

        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
