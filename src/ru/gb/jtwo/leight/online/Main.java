package ru.gb.jtwo.leight.online;

public class Main {
    public static void main(String[] args) {
        try {
            Class.forName("ru.gb.jtwo.leight.online.MyClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        try {
//            Class.forName("org.sqlite.JDBC");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
        // connection = DriverManager.getConnection("jdbc:sqlite:chat.db");
        // statement = connection.createStatement();
    }
}
