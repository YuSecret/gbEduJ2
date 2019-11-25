package ru.gb.jtwo.lfive.online.threads;

public class Main {

    private static int a = 0;
    private static int b = 0;
    private static int c = 0;

    private static final Object monitor = new Object();

    private synchronized static void incAllVars() {
        for (int i = 0; i < 1_000_000; i++) {
            a = a + 1;
            b = b + 1;
            c = c + 1;
        }
        String vars = String.format("a=%d, b=%d, c=%d", a, b, c);
        System.out.println(vars);
    }
    private synchronized static void decAllVars() {

    }

    public static void main(String[] args) {
        Runnable r = () -> incAllVars();
        new Thread(r, "Thread #1").start();
        new Thread(r, "Thread #2").start();
        new Thread(r, "Thread #3").start();
        decAllVars();

        System.out.println("Main finished");
    }

    private static void threadCreateExample() {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, "Thread-10101").start();

        Thread t1 = new Thread(r);
        t1.start();
    }

    private static void joinExample() {
        MyThread t1 = new MyThread("Thread number one!");
        MyThread t2 = new MyThread("Thread number two!");
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
