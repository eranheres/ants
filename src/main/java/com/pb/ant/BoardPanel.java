package com.pb.ant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by eranh on 2/24/16.
 */
public class BoardPanel extends JPanel implements ActionListener {
    private final int B_WIDTH = 300;
    private final int B_HEIGHT = 300;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 29;
    private final int DELAY = 500;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private int generation = 0;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    private Game  game;

    public BoardPanel(Game game) {
        this.game = game;
        int widthInPix = this.game.getWorld().getWorldWidth() * DOT_SIZE;
        int heightInPix = this.game.getWorld().getWorldHeight() * DOT_SIZE;
        //addKeyListener(new TAdapter());
        //setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(widthInPix, heightInPix));
        loadImages();
        initGame();

    }

    private void initGame() {

        dots = 3;

        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void loadImages() {

        ImageIcon iid = new ImageIcon("dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("apple.png");
        apple = iia.getImage();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void drawFood(Graphics g, int x, int y) {
        int radius = Double.valueOf(DOT_SIZE*(3.0/4.0)).byteValue();
        g.drawOval(x*DOT_SIZE+1, y*DOT_SIZE+1, radius, radius);
    }

    private void drawStep(Graphics g, int x, int y) {
        g.drawRect(x*DOT_SIZE, y*DOT_SIZE, DOT_SIZE-1, DOT_SIZE-1);
    }

    private void drawFoods(Graphics g) {
        game.getWorld().getFoodsLoc().forEach((k)->drawFood(g, k.getX(), k.getY()));
    }

    private void drawBestAnt(Graphics g) {
        Ant bestAnt = game.getBestAnt();
        bestAnt.getHistory().stream().forEach((k)->drawStep(g, k.getX(), k.getY()));
        int places = (bestAnt.getHistory().stream().collect(Collectors.toSet()).size());
        if (places <= 3)
            return;
    }

    private void doDrawing(Graphics g) {


        if (generation != game.getGeneration()) {
            generation = game.getGeneration();
            drawFoods(g);
            drawBestAnt(g);
            //g.drawImage(apple, 0, 0, this);
            //g.drawImage(ball, 10 , 10, null);

            Toolkit.getDefaultToolkit().sync();

        } else {

        }
    }
}
