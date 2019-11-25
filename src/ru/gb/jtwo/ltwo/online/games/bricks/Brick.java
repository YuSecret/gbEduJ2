package ru.gb.jtwo.ltwo.online.games.bricks;

import ru.gb.jtwo.ltwo.online.games.common.GameCanvas;
import ru.gb.jtwo.ltwo.online.games.common.Sprite;

import java.awt.*;

public class Brick extends Sprite {
    private float vx = (float)( 150 + (Math.random() * 200f));
    private float vy = (float)( 150 + (Math.random() * 200f));
    private final Color color = new Color(
            (int)(Math.random() * 255),
            (int)(Math.random() * 255),
            (int)(Math.random() * 255)
    );
    Brick() {
        halfHeight = 20 + (float) (Math.random() * 50f);
        halfWidth = halfHeight;
    }

    Brick(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    @Override
    public void update(GameCanvas canvas, float deltaTime) {
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
    public void render(GameCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillRect((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }
}
