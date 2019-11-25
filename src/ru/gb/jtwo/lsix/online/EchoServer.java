package ru.gb.jtwo.lsix.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8189)) {
            System.out.println("Echo server is running");
            Socket s = server.accept();
            System.out.println("Accepted");
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            while (true) {
                String str = in.readUTF();
                out.writeUTF("Echo: " + str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
