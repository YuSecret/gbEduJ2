package ru.gb.jtwo.lone.online;

public class Main {

    private static class Animal {
        public Animal(String name) {
            this.name = name;
        }

        String name;
        void voice() {
            System.out.println(name + " sounds");
        }
    }
    private static class Cat extends Animal {
        public Cat(String name) {
            super(name);
        }
    }
    private static class Bird extends Animal {
        public Bird(String name) {
            super(name);
        }
        public void speak() {
            System.out.println("pollywannacracker");
        }
    }
    public static void main(String[] args) {
        int i = 10;
        int b = 100;
        MyClass myClass = new MyClass();
        System.out.println(myClass.i);
        myClass.method1();
    }

    private static void animalMethod() {
        int i = 10;
        byte b = (byte) i;

        Animal[] arr = {
                new Cat("Barsik"),
                new Bird("Tweetie")
        };
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] instanceof Bird) {
                arr[j].voice();
                ((Bird) arr[j]).speak();
            }
        }
    }
}
