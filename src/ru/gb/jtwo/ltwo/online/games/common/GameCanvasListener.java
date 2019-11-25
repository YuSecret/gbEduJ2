package ru.gb.jtwo.ltwo.online.games.common;

import java.awt.*;

public interface GameCanvasListener {
    void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime);
}
