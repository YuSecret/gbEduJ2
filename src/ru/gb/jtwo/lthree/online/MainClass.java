package ru.gb.jtwo.lthree.online;

import java.util.*;

public class MainClass {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("January", 1);
        map.put("February", 3456);
        map.put("March", 3);
        map.put("April", 3456);
        map.put("May", 5);
        map.put("June", 6);
        System.out.println(map);
        System.out.println(map.get("January"));
        System.out.println(map.get("July"));
        System.out.println(map.containsKey("March"));
        System.out.println(map.containsValue(3456));

        Set<String> s = map.keySet();
        for ( String k : s ) {
            System.out.print(map.get(k) + " ");
        }

        Set<Map.Entry<String, Integer>> set = map.entrySet();
        Iterator<Map.Entry<String, Integer>> i = set.iterator();
        while (i.hasNext()) {
            Map.Entry<String, Integer> entry = i.next();
            System.out.println(entry.getKey() + entry.getValue());
        }
    }

    private static void treeSetExample() {
        Shape s0 = new Shape(1, 1);
        Shape s1 = new Shape(2, 2);
        Shape s2 = new Shape(3, 3);
        Shape s3 = new Shape(1, 1);

        TreeSet<Shape> treeSet = new TreeSet<>();
        treeSet.add(s0);
        treeSet.add(s1);
        treeSet.add(s2);
        treeSet.add(s3);
        System.out.println(treeSet);

        Iterator<Shape> i = treeSet.iterator();
    }

    private static void hashSetExample() {
        Shape s0 = new Shape(1, 1);
        Shape s1 = new Shape(2, 2);
        Shape s2 = new Shape(3, 3);
        Shape s3 = new Shape(1, 1);
        LinkedHashSet<Shape> set = new LinkedHashSet<>();
        set.add(s1);
        set.add(s3);
        set.add(s0);
        set.add(s2);
        System.out.println(set);

        HashSet<String> set1 = new HashSet<>();
        set1.add("January");
        set1.add("February");
        set1.add("March");
        System.out.println(set1);
        LinkedHashSet<String> set2 = new LinkedHashSet<>();
        set2.add("January");
        set2.add("February");
        set2.add("March");
        System.out.println(set2);
    }

    private static void linkedListExample() {
        LinkedList<Shape> list = new LinkedList<>();
        Shape s0 = new Shape(1, 1);
        Shape s1 = new Shape(2, 2);
        Shape s2 = new Shape(3, 3);
        Shape s3 = new Shape(1, 1);
        list.add(s0);

        System.out.println(Integer.toHexString(s0.hashCode()));
        System.out.println(Integer.toHexString(s3.hashCode()));
    }

    private static void badForeachSample() {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Hello");
        strings.add("World");
        for (String s : strings) {
            System.out.println(s);
        }
    }

    private static void arrayListsExample() {
        ArrayList<Shape> list = new ArrayList<>();
        Shape s0 = new Shape(1, 1);
        Shape s1 = new Shape(2, 2);
        Shape s2 = new Shape(3, 3);
        Shape s3 = new Shape(1, 1);
        list.add(s0);
        list.add(s1);
        list.add(s2);
//        list.add(s3);
        System.out.println(list.toString());

        System.out.println(list.contains(s3));

//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }

//        for (Shape shape : list) {
//            System.out.println(shape);
//        }
//
//        Iterator<Shape> i = list.iterator();
//        while (i.hasNext()) {
//            Shape s = i.next();
//            System.out.println(s);
//        }
    }
}
