package ru.gb.jtwo.ltwo.online;

import java.awt.image.ImagingOpException;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class MainClass {

    /*
 1. Есть строка вида: "10 3 1 2\n2 3 F 2\n5 6 7 1\n300 3 1 0";
 (другими словами матрица 4x4)
	 10 3 1 2
	 2 3 2 2
	 5 6 7 1
	 300 3 1 0
 Написать метод, на вход которого подаётся такая строка, метод должен
 преобразовать строку в двумерный массив типа String[][];

 2. Преобразовать все элементы массива в числа типа int, просуммировать,
 поделить полученную сумму на 2, и вернуть результат;

 3. Ваши методы должны бросить исключения в случаях:
	    Если размер матрицы, полученной из строки, не равен 4x4;
	    Если в одной из ячеек полученной матрицы не число;
	    (например символ или слово)

 4. В методе main необходимо вызвать полученные методы,
 обработать возможные исключения и вывести результат расчета.
    * */

    private static class Man implements HumanActions {
        @Override
        public void talk() {

        }

        @Override
        public void look() {

        }
    }
    private static class Cow implements BullActions {
        @Override
        public void voice() {

        }

        @Override
        public void look() {

        }

        @Override
        public void fight() {

        }
    }
    private static interface MouseListener {
        void mouseDown();
        void mouseUp();
    }
    private static interface KeyBoardListener {
        void keyPressed(String str);
    }
    private static void method1(MouseListener listener) {
        listener.mouseUp();
    }
    private static void method2(KeyBoardListener listener) {
        listener.keyPressed("Hello");
    }
    private static float method3(int a, int b) {
        return a / b;
    }
    private static class Stream implements Closeable {
        private void read() throws IOException {
            //throw new IOException();
        }
        public void close() throws IOException {
            throw new IOException();
        }
    }
    public static void main(String[] args) {
        try (Stream s = new Stream()) {
            s.read();
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
        System.out.println("Main");
    }
    private static void anonymous() {
        method2((str) -> {

        });

        method2(new KeyBoardListener() {
            @Override
            public void keyPressed(String str) {

            }
        });


        class LocalClass implements MouseListener {
            @Override
            public void mouseDown() {

            }
            @Override
            public void mouseUp() {

            }
        }

        LocalClass lc = new LocalClass();

        method1(lc);
        method1(new LocalClass());
        method1(new MouseListener() {
            @Override
            public void mouseDown() {

            }

            @Override
            public void mouseUp() {

            }
        });
    }
    private static void interfaces() {
        BullActions[] ba = { new Minotaur(), new Cow() };
        Object[] objects = {new Minotaur(), new Cow()};
        for (int i = 0; i < objects.length; i++) {
            if (objects[i] instanceof Cow)
                ((Cow) objects[i]).fight();
            if (objects[i] instanceof Minotaur)
                ((Minotaur) objects[i]).fight();
        }

        HumanActions[] ha = { new Minotaur(), new Man() };
    }
}
