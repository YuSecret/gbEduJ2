package ru.gb.jtwo.lsix.online;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main(String[] args) {
        try (Socket client = new Socket("127.0.0.1", 8189)) {
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());
            Scanner sc = new Scanner(System.in);
            while (true) {
                String msg = sc.next();
                out.writeUTF(msg);
                String response = in.readUTF();
                System.out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
