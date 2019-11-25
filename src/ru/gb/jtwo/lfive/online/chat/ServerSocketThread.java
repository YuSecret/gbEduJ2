package ru.gb.jtwo.lfive.online.chat;

public class ServerSocketThread extends Thread {

    private final int port;

    ServerSocketThread(String name, int port) {
        super(name);
        this.port = port;
        start();
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println("SST is working");
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                //e.printStackTrace();
                interrupt();
                break;
            }
        }
    }
}
