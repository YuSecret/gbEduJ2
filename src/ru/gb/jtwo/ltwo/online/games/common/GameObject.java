package ru.gb.jtwo.ltwo.online.games.common;

import java.awt.*;

public interface GameObject {
    void update(GameCanvas canvas, float deltaTime);
    void render(GameCanvas canvas, Graphics g);
}
