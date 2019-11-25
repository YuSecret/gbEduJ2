package ru.gb.jtwo.lthree.online;

import java.util.Objects;

public class Shape extends Object implements Comparable {
    private int height;
    private int width;

    public Shape(int height, int width) {
        this.height = height;
        this.width = width;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        return String.format("Shape(%d, %d)", height, width);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Shape)) return false;
        Shape s = (Shape) obj;
        return width == s.width && height == s.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(height, width);
    }

    private int square() {
        return width * height;
    }

    @Override
    public int compareTo(Object o) {
        return square() - ((Shape) o).square();
    }
}
