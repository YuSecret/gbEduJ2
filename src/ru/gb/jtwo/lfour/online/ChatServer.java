package ru.gb.jtwo.lfour.online;

public class ChatServer {
    public void start(int port) {
        System.out.printf("Server starts at port: %d\n", port);
    }

    public void stop() {
        System.out.println("Server stopped");
    }
}
