package ru.gb.jtwo.lone.online.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainCircles extends JFrame {

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
    private Sprite[] sprites = new Sprite[1];
    private int spritesCount;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        GameCanvas canvas = new GameCanvas(this);
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int x = e.getX();
                    int y = e.getY();
                    addSprite(new Ball(x, y));
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
        if (spritesCount > 1) {
            spritesCount--;
        }
    }

    private void addSprite(Sprite sprite) {
        if (spritesCount == sprites.length) {
            Sprite[] newSprites = new Sprite[sprites.length * 2];
            System.arraycopy(sprites, 0, newSprites, 0, sprites.length);
            sprites = newSprites;
        }
        sprites[spritesCount++] = sprite;
    }

    private void initApplication() {
        addSprite(new Background());
        addSprite(new Ball());
    }

    public void onDrawFrame(GameCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(GameCanvas canvas, float deltaTime) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].update(canvas, deltaTime);
        }
    }

    private void render(GameCanvas canvas, Graphics g) {
        for (int i = 0; i < spritesCount; i++) {
            sprites[i].render(canvas, g);
        }
    }
}
