package ru.gb.jtwo.ltwo.online;

public interface HumanActions {
    void talk();
    default void look() {
        System.out.println("with eyes");
    }
}
