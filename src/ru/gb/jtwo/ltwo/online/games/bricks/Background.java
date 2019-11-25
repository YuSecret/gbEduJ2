package ru.gb.jtwo.ltwo.online.games.bricks;

import ru.gb.jtwo.ltwo.online.games.common.GameCanvas;
import ru.gb.jtwo.ltwo.online.games.common.GameObject;

import java.awt.*;

public class Background implements GameObject {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;

    @Override
    public void update(GameCanvas gameCanvas, float deltaTime) {
        time += deltaTime;
        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time));
        color = new Color(red, green, blue);
    }

    @Override
    public void render(GameCanvas gameCanvas, Graphics g) {
        gameCanvas.setBackground(color);
    }
}
