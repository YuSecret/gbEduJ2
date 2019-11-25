package ru.gb.jtwo.lone.online.circles;

import java.awt.*;

public class Ball extends Sprite {
    private float vx = (float)( 150 + (Math.random() * 200f));
    private float vy = (float)( 150 + (Math.random() * 200f));
    private final Color color = new Color(
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );
    Ball() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
    }

    Ball(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    void update(GameCanvas canvas, float deltaTime) {
        x += vx * deltaTime;
        y += vy * deltaTime;
        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vx = -vx;
        }
        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vx = -vx;
        }
        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vy = -vy;
        }
        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vy = -vy;
        }
    }

    @Override
    void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
