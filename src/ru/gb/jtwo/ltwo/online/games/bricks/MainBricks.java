package ru.gb.jtwo.ltwo.online.games.bricks;

import ru.gb.jtwo.ltwo.online.games.common.GameCanvas;
import ru.gb.jtwo.ltwo.online.games.common.GameCanvasListener;
import ru.gb.jtwo.ltwo.online.games.common.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainBricks extends JFrame implements GameCanvasListener {

    /*
    * Полностью разобраться с кодом
	* Прочитать методичку к следующему уроку
	* Написать класс Бэкграунд, изменяющий цвет канвы в зависимости от времени
	*  * Реализовать добавление новых кружков по клику используя ТОЛЬКО массивы
	*  ** Реализовать по клику другой кнопки удаление кружков (никаких эррейЛист)
    * */

    private static final int POS_X = 600;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private GameObject[] gameObjects = new GameObject[1];
    private int gameObjectsCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainBricks();
            }
        });
    }

    private MainBricks() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Bricks");

        GameCanvas canvas = new GameCanvas(this);

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    addSprite(new Brick(x, y));
                } else if (e.getButton() == MouseEvent.BUTTON3) {
                    removeSprite();
                }
            }
        });
        add(canvas, BorderLayout.CENTER);
        initApplication();
        setVisible(true);
    }

    private void removeSprite() {
        if (gameObjectsCount > 1) {
            gameObjectsCount--;
        }
    }

    private void addSprite(GameObject gameObject) {
        if (gameObjectsCount == gameObjects.length) {
            GameObject[] newSprites = new GameObject[gameObjects.length * 2];
            System.arraycopy(gameObjects, 0, newSprites, 0, gameObjects.length);
            gameObjects = newSprites;
        }
        gameObjects[gameObjectsCount] = gameObject;
        gameObjectsCount++;
    }

    private void initApplication() {
        addSprite(new Background());
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < gameObjectsCount; i++) {
            gameObjects[i].render(canvas, g);
        }
    }
}
