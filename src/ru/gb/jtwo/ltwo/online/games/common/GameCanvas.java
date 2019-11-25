package ru.gb.jtwo.ltwo.online.games.common;

import ru.gb.jtwo.ltwo.online.games.balls.MainCircles;

import javax.swing.*;
import java.awt.*;

// user input, update, render
public class GameCanvas extends JPanel {

    private final GameCanvasListener gameController;
    private long lastFrameTime;
    public GameCanvas(GameCanvasListener gameController) {
        this.gameController = gameController;
        lastFrameTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        long currentTime = System.nanoTime();
        float deltaTime = (currentTime - lastFrameTime) * 0.000000001f;
        lastFrameTime = currentTime;
        gameController.onDrawFrame(this, g, deltaTime);
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repaint();
    }

    public int getLeft() { return 0; }
    public int getRight() { return getWidth() - 1; }
    public int getTop() { return 0; }
    public int getBottom() { return getHeight() - 1; }
}
