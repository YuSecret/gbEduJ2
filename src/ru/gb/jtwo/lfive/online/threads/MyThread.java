package ru.gb.jtwo.lfive.online.threads;

public class MyThread extends Thread {

    MyThread(String name) {
        super(name);
        start();
    }

    @Override
    public void run() {
        for (long i = 0; i < 10000000000L; i++) {
            long a = i * 123;
        }
        System.out.println(getName() + " done!");
        //finally
    }
}
