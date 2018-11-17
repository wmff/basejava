package ru.javawebinar.basejava;

import java.io.File;
import java.util.Arrays;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("./src");
        listDir(dir);
    }

    private static void listDir(File dir) {
        File[] list = dir.listFiles();
        if (list != null) {
            Arrays.sort(list);
            for (File file : list) {
                if (file.isDirectory()) {
                    listDir(file);
                } else {
                    System.out.println(file.getName());
                }
            }
        }
    }
}
