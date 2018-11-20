package ru.javawebinar.basejava;

import java.io.File;
import java.util.Arrays;

public class MainFile {
    private static StringBuilder prefix = new StringBuilder();

    public static void main(String[] args) {
        File dir = new File("./src");
        listTree(dir);
        listTreeDir(dir);
    }

    private static void listTree(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            Arrays.sort(list, MainFile::compare);
            for (File file : list) {
                if (file.isDirectory()) {
                    System.out.println(prefix + file.getName());
                    prefix.append(" ");
                    listTree(file);
                } else {
                    System.out.println(prefix + file.getName());
                }
            }
        }

        int length = prefix.length();
        if (length > 0) {
            prefix.delete(length - 1, length);
        }
    }

    private static int compare(File o1, File o2) {
        if (o1.isDirectory()) {
            return o2.isDirectory() ? o1.compareTo(o2) : -1;
        } else if (o2.isDirectory()) {
            return 1;
        } else {
            return o1.compareTo(o2);
        }
    }

    private static void listTreeDir(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            Arrays.sort(list);
            for (File file : list) {
                if (file.isDirectory()) {
                    System.out.println(prefix + file.getName());
                    prefix.append(" ");
                    listTreeDir(file);
                }
            }

            int length = prefix.length();
            if (length > 0) {
                prefix.delete(length - 1, length);
            }
        }
    }
}
