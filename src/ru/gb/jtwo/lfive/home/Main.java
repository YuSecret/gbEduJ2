package ru.gb.jtwo.lfive.home;

import java.util.Arrays;

public class Main {
    private static final int SIZE = 10_000_000;
    private static final int HALF_SIZE = SIZE / 2;
    private static final float[] arrOne = new float[SIZE];
    private static final float[] arrTwo = new float[SIZE];

    public static void main(String[] args) {

        for (int i = 0; i < arrOne.length; i++)
            arrOne[i] = 1f;

        Arrays.fill(arrTwo, 1f);

        long beginTime = System.nanoTime();
        calculateArray(arrOne);
        long deltaTime = System.nanoTime() - beginTime;
        System.out.println("One thread time: " + deltaTime * 1e-9f);

        beginTime = System.nanoTime();
        calculateArrayTwoThreads(arrTwo);
        deltaTime = System.nanoTime() - beginTime;
        System.out.println("Two threads time: " + deltaTime * 1e-9f);

        if (Arrays.equals(arrOne, arrTwo)) {
            System.out.println("Arrays are equal");
        } else {
            System.out.println("Arrays are not equal");
        }
    }

    private static void calculateArray(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] *
                    Math.sin(0.2f + i / 5) *
                    Math.cos(0.2f + i / 5) *
                    Math.cos(0.4f + i / 2));
        }
    }

    private static void calculateArrayTwoThreads(float[] arr) {
        final float[] a1 = new float[HALF_SIZE];
        final float[] a2 = new float[HALF_SIZE];

        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);

        CalcThread threadOne = new CalcThread(a1, 0);
        CalcThread threadTwo = new CalcThread(a2, HALF_SIZE);

        try {
            threadOne.join();
            threadTwo.join();
// стартуем первый поток и пока нас не отпустит джоин - не стартанём второй
//            new CalcThread(a1, 0).join();
//            new CalcThread(a2, HALF_SIZE).join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
    }

}
